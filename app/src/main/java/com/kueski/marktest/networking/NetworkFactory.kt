package com.kueski.marktest.networking

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.wire.WireConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class NetworkFactory(
    private val context: Context,
    private val authenticationInterceptor: AuthenticationInterceptor
) {

    private fun getOkHttpClient(cache: Int?): OkHttpClient {
        val httpClientBuilder = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(authenticationInterceptor)
        cache?.let {
            httpClientBuilder
                .cache(Cache(File(context.cacheDir, "http-cache"), 10L * 1024L * 1024L)) // 10 MiB
                .addNetworkInterceptor(CacheInterceptor(it))
        }
        return httpClientBuilder.build()
    }

    private fun getRetrofit(cache: Int?): Retrofit {
        val converter = GsonConverterFactory.create(GsonBuilder().setLenient().create())
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
            .client(getOkHttpClient(cache))
            .addConverterFactory(converter)
            .build()
    }

    fun <T> createService(apiInterface: Class<T>, cache: Int? = null): T =
        getRetrofit(cache).create(apiInterface)
}