package io.ebrahim.coin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ebrahim.coin.repository.CoinRepository
import kotlinx.coroutines.launch

class CoinDetailsViewModel(private val repository: CoinRepository) : ViewModel() {
    private val _chartData = MutableLiveData<List<List<Any>>>()
    val chartData: LiveData<List<List<Any>>> get() = _chartData

    fun fetchChartData(coinId: String, period: String) {
        viewModelScope.launch {
            try {
                _chartData.value = repository.getChartData(coinId, period)
            } catch (ignored: Exception) {}
        }
    }
}
