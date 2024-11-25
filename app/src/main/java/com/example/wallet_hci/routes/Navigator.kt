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

            //TransferResult

            composable(
                route = "transferResult?amount={amount}&receiverName={receiverName}&bankName={bankName}&aliasSender={aliasSender}&aliasReceiver={aliasReceiver}&receiptId={receiptId}",
                arguments = listOf(
                    navArgument("amount") { type = NavType.StringType; defaultValue = "0.00" },
                    navArgument("receiverName") { type = NavType.StringType; defaultValue = "Desconocido" },
                    navArgument("bankName") { type = NavType.StringType; defaultValue = "Desconocido" },
                    navArgument("aliasSender") { type = NavType.StringType; defaultValue = "AliasPropio" },
                    navArgument("aliasReceiver") { type = NavType.StringType; defaultValue = "AliasReceptor" },
                    navArgument("receiptId") { type = NavType.StringType; defaultValue = "000000" }
                )
            ) { backStackEntry ->
                val amount = backStackEntry.arguments?.getString("amount") ?: "0.00"
                val receiverName = backStackEntry.arguments?.getString("receiverName") ?: "Desconocido"
                val bankName = backStackEntry.arguments?.getString("bankName") ?: "Desconocido"
                val aliasSender = backStackEntry.arguments?.getString("aliasSender") ?: "AliasPropio"
                val aliasReceiver = backStackEntry.arguments?.getString("aliasReceiver") ?: "AliasReceptor"
                val receiptId = backStackEntry.arguments?.getString("receiptId") ?: "000000"

                TransferResultScreen(
                    amount = amount,
                    receiverName = receiverName,
                    bankName = bankName,
                    aliasSender = aliasSender,
                    aliasReceiver = aliasReceiver,
                    receiptId = receiptId,
                    onBack = { navigateTo("home") } // Volver a la pantalla principal
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
            // Pantalla de transferencias
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
                        // Asegúrate de que todos los valores sean válidos antes de navegar
                        val receiverName = selectedContact ?: "Sin contacto"
                        val bankName = "Banco Ejemplo"
                        val aliasSender = "AliasPropio"
                        val aliasReceiver = "AliasReceptor"
                        val receiptId = "123456"

                        val route = "transferResult?amount=$amount&receiverName=$receiverName&bankName=$bankName&aliasSender=$aliasSender&aliasReceiver=$aliasReceiver&receiptId=$receiptId"
                        println("Navegando a: $route") // Debug para verificar la ruta
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

