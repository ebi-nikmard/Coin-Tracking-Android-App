package io.ebrahim.coin.repository

import io.ebrahim.coin.network.CoinApiService
import io.ebrahim.coin.model.CoinResponse

class CoinRepository(private val coinApiService: CoinApiService) {

    suspend fun getCoins(): CoinResponse {
        return coinApiService.getCoins(100)
    }

    suspend fun getChartData(coinId: String, period: String): List<List<Any>> {
        return coinApiService.getChartData(coinId, period)
    }
}