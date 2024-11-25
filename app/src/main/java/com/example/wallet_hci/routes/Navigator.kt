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
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.wallet_hci.screens.app.contacts.AddContactScreen
import com.example.wallet_hci.screens.app.transfers.TransferResultScreen
import javax.inject.Inject
import javax.inject.Singleton


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

            // Pantalla principal
            composable("home") {
                HomeView()
            }

            // Pantalla de contactos
            composable("contacts") {
                ContactScreen(
                    onBack = { navigateBack() },
                    onAddContact = { navigateTo("addContact") }, // Navega a agregar contacto
                    onContactSelected = { selectedContact ->
                        // Navega a la pantalla de transferencias con el contacto seleccionado
                        navigateTo("transfer?selectedContact=$selectedContact")
                    }
                )
            }

            // Pantalla para agregar contactos
            composable("addContact") {
                AddContactScreen(
                    onBack = { navigateBack() },
                    onAdd = { name, cvuOrAlias, email ->
                        // Aquí puedes manejar la lógica de agregar un contacto
                        println("Contacto agregado: $name, $cvuOrAlias, $email")
                        navigateBack() // Regresa a la pantalla de contactos
                    }
                )
            }

            // Pantalla de transferencias
            composable(
                route = "transfer?selectedContact={selectedContact}",
                arguments = listOf(navArgument("selectedContact") {
                    type = NavType.StringType
                    defaultValue = "" // Valor por defecto si no se seleccionó ningún contacto
                })
            ) { backStackEntry ->
                val selectedContact = backStackEntry.arguments?.getString("selectedContact")
                TransferScreen(
                    selectedContact = selectedContact,
                    onCancel = { navigateBack() },
                    onContinue = { paymentMethod, amount, description, cardId ->
                        // Define los valores necesarios para TransferResult
                        val receiverName = selectedContact ?: "Sin contacto"
                        val bankName = "Banco Ejemplo" // Cambia por el valor real si lo tienes
                        val aliasSender = "AliasPropio" // Alias del remitente
                        val aliasReceiver = "AliasReceptor" // Alias del receptor
                        val receiptId = "123456" // ID de recibo generado

                        // Construye la ruta y navega a TransferResult
                        val route = "transferResult?amount=$amount&receiverName=$receiverName&bankName=$bankName&aliasSender=$aliasSender&aliasReceiver=$aliasReceiver&receiptId=$receiptId"
                        navigateTo(route)
                    },
                    onGoToContacts = { navigateTo("contacts") }
                )
            }

            // Pantalla de resultado de la transferencia
            composable(
                route = "transfer?selectedContact={selectedContact}",
                arguments = listOf(navArgument("selectedContact") {
                    type = NavType.StringType
                    nullable = true // Permitir valores nulos
                })
            ) { backStackEntry ->
                val selectedContact = backStackEntry.arguments?.getString("selectedContact")
                println("Valor recibido en TransferScreen: $selectedContact") // Depuración

                TransferScreen(
                    selectedContact = selectedContact,
                    onCancel = { navigateBack() },
                    onContinue = { paymentMethod, amount, description, cardId ->
                        val receiverName = selectedContact ?: "Sin contacto"
                        val bankName = "Banco Ejemplo"
                        val aliasSender = "AliasPropio"
                        val aliasReceiver = "AliasReceptor"
                        val receiptId = "123456"

                        val route = "transferResult?amount=$amount&receiverName=$receiverName&bankName=$bankName&aliasSender=$aliasSender&aliasReceiver=$aliasReceiver&receiptId=$receiptId"
                        navigateTo(route)
                    },
                    onGoToContacts = { navigateTo("contacts") }
                )
            }

        }
    }
}

