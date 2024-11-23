package com.example.wallet_hci.app.routes

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import com.example.wallet_hci.app.screens.home.*
import com.example.wallet_hci.app.Activity
import com.example.wallet_hci.data.UserRemoteDataSource
import com.example.wallet_hci.data.netowrk.api.APIUserService
import com.example.wallet_hci.data.repository.UserRepository
import com.example.wallet_hci.screens.auth.Login.LoginView

class Navigator() {

    lateinit var navController: NavHostController

    fun navigateTo(route: String) {
        navController.navigate(route)
    }

    @Composable
    fun Routes() {
        // Initialize navController in a composable-safe way
        navController = rememberNavController()
//        var userRemoteDataSource = UserRemoteDataSource(sessionManager, APIUserService )
//        var userRepository = UserRepository(userRemoteDataSource)

        NavHost(navController = navController, startDestination = "home") {
            // composable("login") {
            //     // Login screen
            //     LoginView(userRepository = userRepository)
            // }
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
