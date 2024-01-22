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

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var loadingMessage: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setView()

        setAction()
    }

    private fun setView(){
        progressBar = findViewById(R.id.progressBar)
        loadingMessage = findViewById(R.id.loadingMessage)
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun setAction(){
        val adapter = CoinAdapter(this, emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val viewModelFactory = MainViewModelFactory(CoinRepository((application as Core).coinApiService))
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
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
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        mainViewModel.fetchCoins()
    }
}

