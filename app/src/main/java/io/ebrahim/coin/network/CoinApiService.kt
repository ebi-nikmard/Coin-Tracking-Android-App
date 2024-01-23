package io.ebrahim.coin.network

import io.ebrahim.coin.model.CoinResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit API service interface for interacting with the cryptocurrency API.
 */
interface CoinApiService {

    /**
     * Get a list of cryptocurrencies from the API.
     */
    @Headers(
        "accept: application/json",
        "X-API-KEY: cgCqvwJMWZ13a59LzutLX5xxmyi5mGI5aISAcEmZX4A="
    )
    @GET("coins")
    suspend fun getCoins(@Query("limit") limit: Int): CoinResponse

    /**
     * Get historical chart data for a specific cryptocurrency.
     */
    @Headers(
        "accept: application/json",
        "X-API-KEY: cgCqvwJMWZ13a59LzutLX5xxmyi5mGI5aISAcEmZX4A="
    )
    @GET("coins/{coinId}/charts")
    suspend fun getChartData(@Path("coinId") coinId: String, @Query("period") period: String): List<List<Any>>
}
