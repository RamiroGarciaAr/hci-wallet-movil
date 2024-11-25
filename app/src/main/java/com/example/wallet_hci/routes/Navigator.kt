package com.example.wallet_hci.app.routes

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.compose.composable
import com.example.wallet_hci.SessionManager
import com.example.wallet_hci.app.screens.home.*
import com.example.wallet_hci.app.Activity
import com.example.wallet_hci.data.network.api.NetworkModule
import com.example.wallet_hci.data.UserRemoteDataSource
import com.example.wallet_hci.data.netowrk.api.APIUserService
import com.example.wallet_hci.data.repository.UserRepository
import com.example.wallet_hci.screens.app.contacts.ContactScreen
import com.example.wallet_hci.screens.auth.Login.LoginView
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

val NavigatorProvider = staticCompositionLocalOf<Navigator> { error("Navigator not provided") }

@Singleton
class Navigator @Inject constructor(private val sessionManager: SessionManager) {

    lateinit var navController: NavHostController

    fun navigateTo(route: String) {
        navController.navigate(route)
    }

    /**
     * Defines the navigation routes for the app.
     */
    @Composable
    fun Routes() {
        // Initialize navController in a composable-safe way
        navController = rememberNavController()
        // var sessionManager = SessionManager(this)
        // var remoteDataSource = UserRemoteDataSource(sessionManager)
        // var userRepository = UserRepository(remoteDataSource)
        NavHost(navController = navController, startDestination = "login") {
            composable("login") {
                // Login screen
                // LoginView(userRepository)
            }
            composable("home") {
                // Home screen
                HomeView()
            }
            composable("activity") {
                // Activity screen
                Activity()
            }
        }
    }
}
