package com.servicefinder.local.network

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter
import com.servicefinder.local.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

class ApiServiceFactory {
    private lateinit var restApiAdapter : Retrofit
    companion object{
        const val HTTP_CACHE_SIZE: Long = 10 * 1024 * 1024; // 10 MiB
        const val TIMEOUT = 30L
        private fun tryToSetupCache(builder: OkHttpClient.Builder, context: Context) {
            try {
                val cacheDir = context.cacheDir
                if (cacheDir != null) {
                    val cacheFile = File(cacheDir, "ServiceFinderHttpResponseCache")
                    val cache = Cache(cacheFile, HTTP_CACHE_SIZE)
                    builder.cache(cache)
                }
            } catch (exception: Exception) {
               //
            }
        }

        protected fun getOkHttpBuilder(context: Context): OkHttpClient.Builder {
            val okHttpClient = OkHttpClient.Builder()
            tryToSetupCache(okHttpClient, context)
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                okHttpClient.addInterceptor(logging)
            }
            okHttpClient.readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            okHttpClient.connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            return okHttpClient
        }

        fun  getInstance(hostName: String, context: Context):ApiServiceFactory{
            return ApiServiceFactory().apply {
                val builder = getOkHttpBuilder(context)
                builder.addInterceptor(MainInterceptor())
                val gson = GsonBuilder()
                    .registerTypeAdapter(Date::class.java, DateTypeAdapter())
                    .create()
                restApiAdapter = Retrofit.Builder().baseUrl(hostName)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(builder.build())
                    .build();
            }
        }
    }

    fun <T> buildApi(clazz : Class<T>): T  {
        return restApiAdapter.create(clazz);
    }
}