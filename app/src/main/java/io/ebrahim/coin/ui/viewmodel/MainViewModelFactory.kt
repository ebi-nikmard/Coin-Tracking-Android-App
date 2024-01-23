package io.ebrahim.coin.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.ebrahim.coin.repository.CoinRepository

/**
 * Factory class for creating instances of [MainViewModel].
 */
class MainViewModelFactory(private val repository: CoinRepository) : ViewModelProvider.Factory {

    /**
     * Create a new instance of [MainViewModel].
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
