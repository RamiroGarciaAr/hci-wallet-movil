package com.example.wallet_hci.app.routes

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import androidx.navigation.compose.composable
import com.example.wallet_hci.app.screens.home.*
import com.example.wallet_hci.app.Activity
import com.example.wallet_hci.ui.layout.ViewModel

class Navigator {
    final lateinit var navController: NavHostController; 
    fun navigateTo(route: String) { 
        navController.navigate(route = route)
    }
    
    @Composable
    fun Routes() {
        this.navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { HomeView() }
            composable("activity") { Activity() }
        }
    }
}
