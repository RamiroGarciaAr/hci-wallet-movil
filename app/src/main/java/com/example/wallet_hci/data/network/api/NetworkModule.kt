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
//import retrofit2.converter.kotlinx.serialization.asConverterFactory

// Kotlin Serialization
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

// Android Context (if needed for interceptors like AuthInterceptor)
import android.content.Context
import com.example.wallet_hci.data.netowrk.api.UserApiService

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

//    @Provides
//    @Singleton
//    fun provideOkHttpClient(
//        loggingInterceptor: HttpLoggingInterceptor,
//        context: Context
//    ): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(AuthInterceptor(context))
//            .addInterceptor(loggingInterceptor)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(
//        okHttpClient: OkHttpClient
//    ): Retrofit {
//        val json = Json { ignoreUnknownKeys = true }
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
//            .client(okHttpClient)
//            .build()
//    }

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideWalletApiService(retrofit: Retrofit): WalletApiService {
//        return retrofit.create(WalletApiService::class.java)
//    }

//    @Provides
//    @Singleton
//    fun providePaymentApiService(retrofit: Retrofit): PaymentApiService {
//        return retrofit.create(PaymentApiService::class.java)
//    }
}
