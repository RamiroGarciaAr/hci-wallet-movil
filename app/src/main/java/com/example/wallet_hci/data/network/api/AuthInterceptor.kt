package com.example.wallet_hci.data.network.api

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        // Retrieve token from shared preferences or any secure storage
        val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("auth_token", null)

        // Add the token to the request if it exists
        val requestBuilder = chain.request().newBuilder()
        if (token != null) {
            requestBuilder.addHeader("Authorization", "Bearer $token") // Adjust header key as needed
        }

        return chain.proceed(requestBuilder.build())
    }
}