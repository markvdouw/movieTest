package com.kueski.marktest.networking

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    val API_KEY =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0MjNkM2JmMzk4MGFlMjM0NzQxMDhiYmI2MmRhOGIxNCIsInN1YiI6IjU3NWI0ZDZlOTI1MTQxMjE1ZjAwMDA2OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.prm9IRWtpX2-kOB7c5XcOohooNwLqJft_9lmLUSJgs8"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val authenticatedRequest: Request = request.newBuilder()
            .header("Authorization", "Bearer $API_KEY").build()
        return chain.proceed(authenticatedRequest)
    }
}