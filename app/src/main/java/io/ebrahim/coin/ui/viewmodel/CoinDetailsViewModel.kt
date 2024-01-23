package io.ebrahim.coin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ebrahim.coin.repository.CoinRepository
import kotlinx.coroutines.launch

/**
 * ViewModel class for handling data related to cryptocurrency details, including chart data.
 */
class CoinDetailsViewModel(private val repository: CoinRepository) : ViewModel() {

    /**
     * LiveData for storing historical chart data of a cryptocurrency.
     */
    private val _chartData = MutableLiveData<List<List<Any>>>()
    val chartData: LiveData<List<List<Any>>> get() = _chartData

    /**
     * Fetch historical chart data for a specific cryptocurrency.
     */
    fun fetchChartData(coinId: String, period: String) {
        viewModelScope.launch {
            try {
                _chartData.value = repository.getChartData(coinId, period)
            } catch (ignored: Exception) {}
        }
    }
}
