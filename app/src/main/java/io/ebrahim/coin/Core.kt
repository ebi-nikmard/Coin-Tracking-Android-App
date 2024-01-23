package io.ebrahim.coin

import android.app.Application
import io.ebrahim.coin.network.CoinApiService
import io.ebrahim.coin.network.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Application class responsible for initializing core components.
 */
class Core : Application() {

    /**
     * Retrofit API service instance for interacting with the cryptocurrency API.
     */
    val coinApiService: CoinApiService = Retrofit.Builder()
        .baseUrl("https://openapiv1.coinstats.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor())
            .build())
        .build().create(CoinApiService::class.java)

}
