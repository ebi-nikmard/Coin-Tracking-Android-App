package io.ebrahim.coin.repository

import io.ebrahim.coin.network.CoinApiService
import io.ebrahim.coin.model.CoinResponse

/**
 * Repository class for handling data operations related to cryptocurrencies.
 */
class CoinRepository(private val coinApiService: CoinApiService) {

    /**
     * Get a list of cryptocurrencies from the API.
     */
    suspend fun getCoins(): CoinResponse {
        return coinApiService.getCoins(100)
    }

    /**
     * Get historical chart data for a specific cryptocurrency.
     */
    suspend fun getChartData(coinId: String, period: String): List<List<Any>> {
        return coinApiService.getChartData(coinId, period)
    }
}
