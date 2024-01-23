package io.ebrahim.coin.network

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.Request

/**
 * Interceptor for logging network requests and responses.
 */
class LoggingInterceptor : Interceptor {
    /**
     * Intercept the network request and log information.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val url = request.url()
        return chain.proceed(request)
    }
}