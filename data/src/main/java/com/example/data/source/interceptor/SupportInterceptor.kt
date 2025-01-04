package com.example.data.source.interceptor

import com.example.data.utils.Constants.Companion.API_KEY_VALUE
import okhttp3.Interceptor
import okhttp3.Response


class SupportInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val requestWithApiKey =
            request.url.newBuilder()
                .addQueryParameter("appId", API_KEY_VALUE)
                .build()
        val newRequest = request.newBuilder()
            .url(requestWithApiKey)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()
        return chain.proceed(newRequest)
    }
}