package com.example.wallet_hci.app.routes

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import androidx.navigation.compose.composable
import com.example.wallet_hci.app.screens.home.*
import com.example.wallet_hci.app.Activity
import com.example.wallet_hci.data.repository.UserRepository
import com.example.wallet_hci.screens.auth.Login.LoginView

class Navigator(private val userRepository: UserRepository) {

    lateinit var navController: NavHostController

    /**
     * Navigates to a given route.
     */
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

        NavHost(navController = navController, startDestination = "login") {
            composable("login") {
                // Login screen
                LoginView(userRepository = userRepository)
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
