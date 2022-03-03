package com.servicefinder.local.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MainInterceptor: Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("X-Been","Intercepted")
        requestBuilder.url("")
        return chain.proceed(requestBuilder.build())
    }
}