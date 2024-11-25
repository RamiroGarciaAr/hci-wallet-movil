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
import com.example.wallet_hci.screens.app.Login.LoginView
import android.content.SharedPreferences
import com.example.wallet_hci.screens.app.transfers.TransferResultScreen
import javax.inject.Inject
import javax.inject.Singleton


/*
@Composable
fun Routes() {
    val navigator = this
    navController = rememberNavController()
    NavHost(navController = navController, startDestination = "transfer") {
        composable("transfer") {
            TransferScreen(
                onCancel = {
                    navigator.navigateBack() // Regresar a la pantalla anterior
                },
                onContinue = { paymentMethod, amount, description, cardId ->
                    // Navegar a la pantalla de resultados con datos simulados
                    navController.navigate(
                        "result/$amount/Damián Villablanca/Banco Galicia/Tobías Juhasz/sol.cielo.arcoiris/$description"
                    )
                }
            )
        }

        composable(
            "result/{amount}/{receiverName}/{bankName}/{aliasSender}/{aliasReceiver}/{receiptId}"
        ) { backStackEntry ->
            // Obtener parámetros de la ruta
            val amount = backStackEntry.arguments?.getString("amount") ?: "0.0"
            val receiverName = backStackEntry.arguments?.getString("receiverName") ?: "Unknown"
            val bankName = backStackEntry.arguments?.getString("bankName") ?: "Unknown"
            val aliasSender = backStackEntry.arguments?.getString("aliasSender") ?: "Unknown"
            val aliasReceiver = backStackEntry.arguments?.getString("aliasReceiver") ?: "Unknown"
            val receiptId = backStackEntry.arguments?.getString("receiptId") ?: "N/A"

            // Mostrar la pantalla de resultados con los datos pasados
            TransferResultScreen(
                amount = amount,
                receiverName = receiverName,
                bankName = bankName,
                aliasSender = aliasSender,
                aliasReceiver = aliasReceiver,
                receiptId = receiptId,
                onShare = { /* Lógica de prueba para compartir */ },
                onSaveContact = { /* Lógica de prueba para guardar contacto */ },
                onBack = { navController.popBackStack() }
            )
        }
    }
}

 */

val NavigatorProvider = staticCompositionLocalOf<Navigator> { error("Navigator not provided") }

@Singleton
class Navigator @Inject constructor(private val sessionManager: SessionManager) {

    lateinit var navController: NavHostController

    fun navigateTo(route: String) {
        navController.navigate(route)
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    fun getCurrentPage(): String {
        return navController.currentBackStackEntry?.destination?.route ?: ""
    }


    @Composable
    fun Routes() {
        val navigator = this
        navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { HomeView() }
            composable("activity") { Activity() }
            composable(route = "contacts") {/*
                ContactScreen(
                    onBack = { navigator.navigateBack() }, // Navega hacia atrás al hacer clic en el botón de retroceso
                    onAddContact = { navigator.navigateTo("add_contact") } // Cambia la ruta según la pantalla de agregar contactos
                )
                */
            }


            // Pantalla de transferencia
            composable("transfer") {
                TransferScreen(
                    onCancel = {
                        navigator.navigateBack() // Volver a la pantalla anterior
                    },
                    onContinue = { paymentMethod: String, amount: Double, description: String, cardId: String? ->
                        // Navegar a la pantalla de resultados con parámetros simulados
                        val route = "result/$amount/Damián Villablanca/Banco Galicia/Tobías Juhasz/sol.cielo.arcoiris/$description"
                        navigator.navigateTo(route)
                    }
                )
            }

            // Pantalla de resultados
            composable(
                route = "result/{amount}/{receiverName}/{bankName}/{aliasSender}/{aliasReceiver}/{description}"
            ) { backStackEntry ->
                // Extraer parámetros de la ruta
                val amount = backStackEntry.arguments?.getString("amount") ?: "0.0"
                val receiverName = backStackEntry.arguments?.getString("receiverName") ?: "Unknown"
                val bankName = backStackEntry.arguments?.getString("bankName") ?: "Unknown"
                val aliasSender = backStackEntry.arguments?.getString("aliasSender") ?: "Unknown"
                val aliasReceiver = backStackEntry.arguments?.getString("aliasReceiver") ?: "Unknown"
                val description = backStackEntry.arguments?.getString("description") ?: "N/A"

                // Mostrar la pantalla de resultados con los datos extraídos
                TransferResultScreen(
                    amount = amount,
                    receiverName = receiverName,
                    bankName = bankName,
                    aliasSender = aliasSender,
                    aliasReceiver = aliasReceiver,
                    receiptId = description, // Puedes cambiar este mapeo si "description" no es el recibo
                    onShare = { /* Lógica para compartir */ },
                    onSaveContact = { /* Lógica para guardar contacto */ },
                    onBack = { navigator.navigateBack() }
                )
            }
        }
    }
}
