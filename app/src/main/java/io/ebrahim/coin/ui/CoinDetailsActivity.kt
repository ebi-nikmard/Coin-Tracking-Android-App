package io.ebrahim.coin.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.squareup.picasso.Picasso
import io.ebrahim.coin.Core
import io.ebrahim.coin.R
import io.ebrahim.coin.repository.CoinRepository
import io.ebrahim.coin.ui.viewmodel.CoinDetailsViewModel
import io.ebrahim.coin.ui.viewmodel.CoinDetailsViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CoinDetailsActivity : AppCompatActivity() {

    private lateinit var coinId: String
    private lateinit var coinSymbol: String
    private lateinit var coinIcon: String
    private lateinit var coinTwitter: String
    private lateinit var coinWeb: String
    private lateinit var coinReddit: String
    private lateinit var coinPrice: String
    private lateinit var coinPriceBTC: String
    private lateinit var coinVolume: String
    private lateinit var priceChange1h: String
    private lateinit var marketCap: String
    private lateinit var totalSupply: String
    private lateinit var coinDetailsViewModel: CoinDetailsViewModel

    private lateinit var coinAvatarUI: ImageView
    private lateinit var coinNameUI: TextView
    private lateinit var coinSymbolUI: TextView
    private lateinit var coinPriceUI: TextView
    private lateinit var coinChangeUI: TextView
    private lateinit var coinPriceBTCValueUI: TextView
    private lateinit var coinVolumeValueUI: TextView
    private lateinit var marketCapUI: TextView
    private lateinit var totalSupplyUI: TextView
    private lateinit var coinTwitterUI: ImageView
    private lateinit var coinWebUI: ImageView
    private lateinit var coinRedditUI: ImageView
    private lateinit var lineChart: LineChart
    private lateinit var lineChartProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_details)

        setView()

        setAction()
    }

    private fun setView(){
        coinId = intent.getStringExtra("coinId") ?: ""
        coinSymbol = intent.getStringExtra("coinSymbol") ?: ""
        coinIcon = intent.getStringExtra("coinIcon") ?: ""
        coinTwitter = intent.getStringExtra("twitterUrl") ?: ""
        coinWeb = intent.getStringExtra("websiteUrl") ?: ""
        coinReddit = intent.getStringExtra("redditUrl") ?: ""
        coinPrice = intent.getStringExtra("coinPrice") ?: ""
        priceChange1h = intent.getStringExtra("priceChange1h") ?: ""
        coinPriceBTC = intent.getStringExtra("coinPriceBTC") ?: ""
        coinVolume = intent.getStringExtra("coinVolume") ?: ""
        marketCap = intent.getStringExtra("marketCap") ?: ""
        totalSupply = intent.getStringExtra("totalSupply") ?: ""

        coinAvatarUI = findViewById(R.id.coinAvatar)
        coinTwitterUI = findViewById(R.id.coinTwitter)
        coinWebUI = findViewById(R.id.coinWeb)
        coinRedditUI = findViewById(R.id.coinReddit)
        coinNameUI = findViewById(R.id.coinName)
        coinSymbolUI = findViewById(R.id.coinSymbol)
        coinPriceUI = findViewById(R.id.coinPrice)
        coinChangeUI = findViewById(R.id.coinChange)
        lineChart = findViewById(R.id.lineChart)
        lineChartProgressBar = findViewById(R.id.lineChartProgressBar)
        coinPriceBTCValueUI = findViewById(R.id.coinPriceBTCValue)
        marketCapUI = findViewById(R.id.coinMarketCapValue)
        totalSupplyUI = findViewById(R.id.coinTotalSupplyUIValue)
        coinVolumeValueUI = findViewById(R.id.coinVolumeValue)
    }

    private fun setAction(){
        Picasso.get().load(coinIcon).into(coinAvatarUI)

        coinNameUI.text = coinId
        coinSymbolUI.text = coinSymbol
        coinPriceUI.text = "$ $coinPrice"
        coinChangeUI.text = "$priceChange1h %"
        coinPriceBTCValueUI.text = coinPriceBTC
        marketCapUI.text = marketCap
        totalSupplyUI.text = totalSupply
        coinVolumeValueUI.text = coinVolume

        if (priceChange1h.toDouble() > 0) {
            coinChangeUI.setTextColor(getColor(R.color.green))
            coinPriceUI.setTextColor(getColor(R.color.green))
        }

        if (priceChange1h.toDouble() < 0) {
            coinChangeUI.setTextColor(getColor(R.color.red))
            coinPriceUI.setTextColor(getColor(R.color.red))
        }

        coinTwitterUI.setOnClickListener { openLink(coinTwitter) }

        coinWebUI.setOnClickListener { openLink(coinWeb) }

        coinRedditUI.setOnClickListener { openLink(coinReddit) }

        val repository = CoinRepository((application as Core).coinApiService)
        val viewModelFactory = CoinDetailsViewModelFactory(repository)
        coinDetailsViewModel = ViewModelProvider(this, viewModelFactory).get(CoinDetailsViewModel::class.java)

        coinDetailsViewModel.chartData.observe(this) { chartData ->
            lineChartProgressBar.visibility = View.GONE
            lineChart.visibility = View.VISIBLE
            updateChart(chartData)
        }

        coinDetailsViewModel.fetchChartData(coinId, "1w")
    }

    private fun updateChart(chartData: List<List<Any>>) {
        try {
            if (chartData.isNotEmpty()) {
                val entries: MutableList<Entry> = mutableListOf()
                for (dataPoint in chartData) {

                    val timestamp = dataPoint[0].toString().toDouble().toLong()
                    val price = dataPoint[1] as Double

                    // Add data points to the chart
                    entries.add(Entry(timestamp.toFloat(), price.toFloat()))
                }

                // Customize line data set
                val dataSet = LineDataSet(entries, "Price")
                dataSet.color = getColor(R.color.orange) // Set line color
                dataSet.setDrawCircles(false) // Disable drawing circles at data points
                dataSet.setDrawValues(false) // Disable drawing values on data points

                // Customize chart appearance
                lineChart.xAxis.textColor = getColor(R.color.green)
                lineChart.xAxis.setDrawGridLines(false)
                lineChart.axisLeft.textColor = getColor(R.color.green)
                lineChart.axisLeft.setDrawGridLines(true)
                lineChart.axisRight.textColor = getColor(R.color.green)
                lineChart.axisRight.setDrawGridLines(false)

                lineChart.description.text = ""

                // Set X-axis value formatter for better date display
                lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
                lineChart.xAxis.valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        val date = Date(value.toLong() * 1000)
                        return SimpleDateFormat("MMM dd", Locale.getDefault()).format(date)
                    }
                }

                // Inside your activity or fragment
                val priceTextView: TextView = findViewById(R.id.priceTextView)

                lineChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                    override fun onValueSelected(e: Entry?, h: Highlight?) {
                        // Display the price of the selected data point above the chart
                        val price = e?.y ?: 0.0
                        priceTextView.text = "Price: $ $price"
                        priceTextView.visibility = View.VISIBLE
                    }

                    override fun onNothingSelected() {
                        // Hide the TextView when nothing is selected (optional)
                        priceTextView.visibility = View.INVISIBLE
                    }
                })


                // Customize the chart legend
                val legend = lineChart.legend
                legend.textColor = getColor(R.color.green)

                // Create line data and set it to the chart
                val lineData = LineData(dataSet)
                lineChart.data = lineData

                // Invalidate the chart to refresh the appearance
                lineChart.invalidate()
            } else {
                Log.d("CoinDetailsActivity", "No chart data available")
            }
        } catch (e: Exception) {
            Log.e("CoinDetailsActivity", "Error updating chart", e)
        }
    }

    private fun openLink(link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(intent)
    }
}
