package com.example.wallet_hci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier

import com.example.wallet_hci.app.routes.NavigatorProvider
import com.example.wallet_hci.app.routes.Navigator
import com.example.wallet_hci.ui.theme.WallethciTheme
import com.example.wallet_hci.ui.menu.NavBar
import com.example.wallet_hci.ui.menu.FloatingQRButton

import com.example.wallet_hci.data.UserRemoteDataSource

import com.example.wallet_hci.data.repository.UserRepositoryProvider
import com.example.wallet_hci.data.repository.UserRepository
import com.example.wallet_hci.data.network.api.NetworkModule
import com.example.wallet_hci.data.network.api.WalletApiServiceProvider
import com.example.wallet_hci.data.network.api.PaymentApiServiceProvider


class MainActivity : ComponentActivity() {
    private lateinit var navigator: Navigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val sessionManager = SessionManager(this)

        val loggingInterceptor = NetworkModule.provideHttpLoggingInterceptor()
        val okHttpClient = NetworkModule.provideOkHttpClient(loggingInterceptor, this)
        val retrofit = NetworkModule.provideRetrofit(okHttpClient)

        val userRemoteDataSource = UserRemoteDataSource(sessionManager, NetworkModule.provideUserApiService(retrofit))
        val userRepository = UserRepository(userRemoteDataSource)

        val walletRemoteDataSource = NetworkModule.provideWalletApiService(retrofit)
        val paymentRemoteDataSource = NetworkModule.providePaymentApiService(retrofit)

        navigator = Navigator(sessionManager)
        setContent {
            CompositionLocalProvider(
                NavigatorProvider provides navigator,
                SessionProvider provides sessionManager,
                UserRepositoryProvider provides userRepository,
                WalletApiServiceProvider provides walletRemoteDataSource,
                PaymentApiServiceProvider provides paymentRemoteDataSource,
                
            ){
                WallethciTheme {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            NavBar()
                        },
                        floatingActionButton = {
                            FloatingQRButton()
                        },
                    ) {_ ->  this.navigator.Routes() }
                }
            }
        }
    }
}
