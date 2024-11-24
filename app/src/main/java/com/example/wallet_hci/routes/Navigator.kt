package com.example.wallet_hci.app.routes

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import com.example.wallet_hci.app.screens.home.*
import com.example.wallet_hci.app.Activity
import com.example.wallet_hci.ui.layout.ViewModel
import com.example.wallet_hci.screens.app.contacts.ContactScreen

class Navigator {

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
            composable("home") { HomeView() }
            composable("activity") { Activity() }
            composable(route = "contacts") { ContactScreen() } // Ruta para Contacts

        }
    }
}
