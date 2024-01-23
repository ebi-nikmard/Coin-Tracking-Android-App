package io.ebrahim.coin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ebrahim.coin.model.Coin
import io.ebrahim.coin.repository.CoinRepository
import kotlinx.coroutines.launch

/**
 * ViewModel class for handling data related to the main list of cryptocurrencies.
 */
class MainViewModel(private val repository: CoinRepository) : ViewModel() {

    /**
     * LiveData for storing the list of cryptocurrencies.
     */
    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>> get() = _coins

    /**
     * LiveData for indicating the loading state of the data.
     */
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    /**
     * LiveData for storing error messages during data fetching.
     */
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    /**
     * Fetch the list of cryptocurrencies from the repository.
     */
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
