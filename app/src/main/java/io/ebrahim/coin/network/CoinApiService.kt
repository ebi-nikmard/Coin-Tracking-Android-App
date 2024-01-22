package io.ebrahim.coin.network
import io.ebrahim.coin.model.CoinResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinApiService {

    @Headers(
        "accept: application/json",
        "X-API-KEY: cgCqvwJMWZ13a59LzutLX5xxmyi5mGI5aISAcEmZX4A="
    )
    @GET("coins")
    suspend fun getCoins(@Query("limit") limit: Int): CoinResponse

    @Headers(
        "accept: application/json",
        "X-API-KEY: cgCqvwJMWZ13a59LzutLX5xxmyi5mGI5aISAcEmZX4A="
    )
    @GET("coins/{coinId}/charts")
    suspend fun getChartData(@Path("coinId") coinId: String, @Query("period") period: String): List<List<Any>>
}