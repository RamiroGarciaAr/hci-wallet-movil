package com.example.wallet_hci.data.network.api

// Hilt DI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Retrofit and OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


// Kotlin Serialization
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

// Android Context (if needed for interceptors like AuthInterceptor)
import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi

import com.example.wallet_hci.data.netowrk.api.APIUserService
import com.example.wallet_hci.data.api.APIPaymentService
import androidx.compose.runtime.staticCompositionLocalOf
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://10.0.2.2:8080/api/"

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): APIUserService {
        return retrofit.create(APIUserService::class.java)
    }

    @Provides
    @Singleton
    fun provideWalletApiService(retrofit: Retrofit): APIWalletService {
        return retrofit.create(APIWalletService::class.java)
    }

    @Provides
    @Singleton
    fun providePaymentApiService(retrofit: Retrofit): APIPaymentService {
        return retrofit.create(APIPaymentService::class.java)
    }
}

val PaymentApiServiceProvider = staticCompositionLocalOf<APIPaymentService> { error("PaymentApiService not provided") }
val UserApiServiceProvider = staticCompositionLocalOf<APIUserService> { error("UserApiService not provided") }
val WalletApiServiceProvider = staticCompositionLocalOf<APIWalletService> { error("WalletApiService not provided") }