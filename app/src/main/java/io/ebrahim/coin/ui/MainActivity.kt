package io.ebrahim.coin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.ebrahim.coin.Core
import io.ebrahim.coin.R
import io.ebrahim.coin.repository.CoinRepository
import io.ebrahim.coin.ui.adapter.CoinAdapter
import io.ebrahim.coin.ui.viewmodel.MainViewModel
import io.ebrahim.coin.ui.viewmodel.MainViewModelFactory

/**
 * Main activity displaying a list of coins fetched from the CoinStats API.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var loadingMessage: TextView
    private lateinit var recyclerView: RecyclerView

    /**
     * Called when the activity is starting.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        setView()

        // Set up UI actions and ViewModel
        setAction()
    }

    /**
     * Initializes UI components.
     */
    private fun setView(){
        progressBar = findViewById(R.id.progressBar)
        loadingMessage = findViewById(R.id.loadingMessage)
        recyclerView = findViewById(R.id.recyclerView)
    }

    /**
     * Sets up UI actions, RecyclerView adapter, and ViewModel.
     */
    private fun setAction(){
        val adapter = CoinAdapter(this, emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Create ViewModel and its factory
        val viewModelFactory = MainViewModelFactory(CoinRepository((application as Core).coinApiService))
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        // Observe LiveData changes and update UI accordingly
        mainViewModel.coins.observe(this) { coins ->
            adapter.submitList(coins)
        }

        mainViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
                loadingMessage.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
                loadingMessage.visibility = View.GONE
            }
        }

        mainViewModel.errorMessage.observe(this) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                // Display an error message using a Toast
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        // Fetch coins data from the server
        mainViewModel.fetchCoins()
    }
}