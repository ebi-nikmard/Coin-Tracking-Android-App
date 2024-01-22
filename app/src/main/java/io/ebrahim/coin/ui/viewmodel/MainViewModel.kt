package io.ebrahim.coin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ebrahim.coin.model.Coin
import io.ebrahim.coin.repository.CoinRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CoinRepository) : ViewModel() {
    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>> get() = _coins

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchCoins() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = repository.getCoins()
                _coins.value = response.result
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching data: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
