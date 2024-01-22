package io.ebrahim.coin.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.Request

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val url = request.url()

        Log.i("ebrahimm", "" + url)

        return chain.proceed(request)
    }
}