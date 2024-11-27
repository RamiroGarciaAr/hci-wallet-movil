package com.example.wallet_hci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Text
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

import com.example.wallet_hci.app.routes.Navigator
import com.example.wallet_hci.ui.theme.WallethciTheme
import com.example.wallet_hci.ui.menu.NavBar
import com.example.wallet_hci.ui.menu.FloatingQRButton
import com.example.wallet_hci.ui.snackbars.SnackbarSuccess

import com.example.wallet_hci.data.repository.UserRepositoryProvider
import com.example.wallet_hci.data.repository.UserRepository
import com.example.wallet_hci.data.repository.WalletRepository
import com.example.wallet_hci.data.repository.WalletRepositoryProvider
import com.example.wallet_hci.data.network.api.NetworkModule
import com.example.wallet_hci.data.network.api.WalletApiServiceProvider
import com.example.wallet_hci.data.network.api.PaymentApiServiceProvider


import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.wallet_hci.data.network.UserRemoteDataSource
import com.example.wallet_hci.data.network.WalletDataSource
import com.example.wallet_hci.routes.NavigatorProvider
import com.example.wallet_hci.UiState


class MainActivity : ComponentActivity() {
    private lateinit var navigator: Navigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val sessionManager = SessionManager(this)
        val uiState = UiState()

        val loggingInterceptor = NetworkModule.provideHttpLoggingInterceptor()
        val okHttpClient = NetworkModule.provideOkHttpClient(loggingInterceptor, this)
        val retrofit = NetworkModule.provideRetrofit(okHttpClient)

        val userRemoteDataSource = UserRemoteDataSource(sessionManager, NetworkModule.provideUserApiService(retrofit))
        val userRepository = UserRepository(userRemoteDataSource)

        val walletRemoteDataSource = WalletDataSource(NetworkModule.provideWalletApiService(retrofit))
        val walletRepository = WalletRepository(walletRemoteDataSource)

        val paymentRemoteDataSource = NetworkModule.providePaymentApiService(retrofit)

        this.navigator = Navigator(sessionManager)
        setContent {
            CompositionLocalProvider(
                NavigatorProvider provides this.navigator,
                SessionProvider provides sessionManager,
                UserRepositoryProvider provides userRepository,
                WalletRepositoryProvider provides walletRepository,
                PaymentApiServiceProvider provides paymentRemoteDataSource,
                UiStateProvider provides uiState,
            ){
                WallethciTheme {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        // floatingActionButton = { FloatingQRButton(navigator) },
                        bottomBar = { if(uiState.showNavigationBar) NavBar() },
                        snackbarHost = { SnackbarHost(
                            uiState.snackbarHostState,
                        ) },
                    ) { innerPadding ->  
                        Column(modifier = Modifier.padding(innerPadding))
                        { navigator.Routes() }
                    }
                }
            }
        }
    }
}
