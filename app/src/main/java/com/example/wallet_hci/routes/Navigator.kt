package com.example.wallet_hci.app.routes

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavOptions
import androidx.navigation.Navigator as NavHostNavigator

import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Person2
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home


import androidx.navigation.compose.composable
import com.example.wallet_hci.SessionManager
import com.example.wallet_hci.app.screens.home.*
import com.example.wallet_hci.app.Activity
import com.example.wallet_hci.data.network.api.NetworkModule
import com.example.wallet_hci.data.UserRemoteDataSource
import com.example.wallet_hci.data.netowrk.api.APIUserService
import com.example.wallet_hci.data.repository.UserRepository
import com.example.wallet_hci.screens.app.contacts.ContactScreen
import com.example.wallet_hci.screens.app.Login.LoginView
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.Serializable
import com.example.wallet_hci.R


sealed interface Routes {
    @Serializable
    object Settings : Routes {
        @Composable
        override fun getName(): String {
            return stringResource(R.string.r_settings)
        }

        @Composable
        override fun getIcon(selected: Boolean): ImageVector  {
            return if (selected) Icons.Filled.Settings else Icons.Outlined.Settings
        }
    }
    @Serializable
    object Home : Routes {
        @Composable
        override fun getName(): String {
            return stringResource(R.string.r_home)
        }

        @Composable
        override fun getIcon(selected: Boolean): ImageVector  {
            return if (selected) Icons.Filled.Home else Icons.Outlined.Home
        }
    }

    @Serializable
    object Activity : Routes {
        @Composable
        override fun getName(): String {
            return stringResource(R.string.r_activities)
        }

        @Composable
        override fun getIcon(selected: Boolean): ImageVector  {
            return Icons.Filled.List
        }}

    @Serializable
    object Contacts : Routes {
        @Composable
        override fun getName(): String {
            return stringResource(R.string.r_contacts)
        }

        @Composable
        override fun getIcon(selected: Boolean): ImageVector  {
            return if (selected) Icons.Filled.Person2 else Icons.Outlined.Person2
        }
    }

    @Composable
    abstract fun getName(): String

    @Composable
    abstract fun getIcon(selected: Boolean): ImageVector
}

val NavigatorProvider = staticCompositionLocalOf<Navigator> { error("Navigator not provided") }
@Singleton
class Navigator @Inject constructor(private val sessionManager: SessionManager) {

    lateinit var navController: NavHostController

    fun navigateTo(route: String, navOptions: NavOptions? = null, navigatorExtras: NavHostNavigator.Extras? = null) {
        navController.navigate(route, navOptions, navigatorExtras)
    }

    fun navigateTo(route: Routes, navOptions: NavOptions? = null, navigatorExtras: NavHostNavigator.Extras? = null) {
        navController.navigate(route, navOptions, navigatorExtras)
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    fun getCurrentPage(): String {
        return navController.currentBackStackEntry?.destination?.route ?: ""
    }
    
    @Composable
    fun Routes() {
        navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routes.Home) {
            composable<Routes.Home> { HomeView() }
            composable<Routes.Activity> { Activity() }
            composable<Routes.Contacts> { ContactScreen() } // Ruta para Contacts
        }
    }
}
