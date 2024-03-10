package com.kueski.marktest.networking

import com.kueski.marktest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val config = BuildConfig.API_KEY_NAME
        val authenticatedRequest: Request = request.newBuilder()
            .header("Authorization", "Bearer $config").build()
        return chain.proceed(authenticatedRequest)
    }
}