package io.ebrahim.coin.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.ebrahim.coin.repository.CoinRepository

/**
 * Factory class for creating instances of [CoinDetailsViewModel].
 */
class CoinDetailsViewModelFactory(private val repository: CoinRepository) : ViewModelProvider.Factory {

    /**
     * Create a new instance of [CoinDetailsViewModel].
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoinDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CoinDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
