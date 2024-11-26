package com.example.wallet_hci.app.routes



/**
 * ICONS
 */
import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person2
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wallet_hci.R
import com.example.wallet_hci.SessionManager
import com.example.wallet_hci.app.Activity
import com.example.wallet_hci.app.screens.home.*
import com.example.wallet_hci.screens.app.contacts.AddContactScreen
import com.example.wallet_hci.screens.app.contacts.ContactScreen
import com.example.wallet_hci.screens.app.transfers.TransferResultScreen


import com.example.wallet_hci.screens.app.Login.LogInScreen
import com.example.wallet_hci.screens.app.registration.RegistrationScreen
import com.example.wallet_hci.screens.app.code.VerificationScreen

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.Serializable

import androidx.navigation.Navigator as NavHostNavigator

sealed interface Routes {
    @Serializable
    object Login

    @Serializable
    object Register

    @Serializable
    object Settings : Routes {
        @Composable
        override fun getName(): String {
            return stringResource(R.string.r_settings)
        }

        @Composable
        override fun getIcon(selected: Boolean): ImageVector {
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
        override fun getIcon(selected: Boolean): ImageVector {
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
        override fun getIcon(selected: Boolean): ImageVector {
            return Icons.Filled.List
        }
    }

    @Serializable
    object Contacts : Routes {
        @Composable
        override fun getName(): String {
            return stringResource(R.string.r_contacts)
        }

        @Composable
        override fun getIcon(selected: Boolean): ImageVector {
            return if (selected) Icons.Filled.Person2 else Icons.Outlined.Person2
        }
    }

    @Serializable
    data class Transfer(
        val amount: String? = null,
        val description: String? = null,
        val contactId: String? = null
    )

    @Serializable object VerifyCode

    @Serializable object Profile

    @Composable abstract fun getName(): String

    @Composable abstract fun getIcon(selected: Boolean): ImageVector
}

//@SuppressLint("CompositionLocalNaming")
//val NavigatorProvider = staticCompositionLocalOf<Navigator> { error("Navigator not provided") }

@Singleton
class Navigator @Inject constructor(private val sessionManager: SessionManager) {

    private lateinit var navController: NavHostController

    fun navigateTo(
        route: String,
        navOptions: NavOptions? = null,
        navigatorExtras: NavHostNavigator.Extras? = null
    ) {
        navController.navigate(route, navOptions, navigatorExtras)
    }

    fun <T : Any> navigateTo(
        route: T,
        navOptions: NavOptions? = null,
        navigatorExtras: NavHostNavigator.Extras? = null
    ) {
        navController.navigate(route, navOptions, navigatorExtras)
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    @Composable
    fun Routes() {
        this.navController = rememberNavController()
        NavHost(navController = this.navController, startDestination = Routes.VerifyCode) {
            composable<Routes.Home> { HomeView() }
            composable<Routes.Activity> { Activity() }
            composable<Routes.Contacts> {
                ContactScreen(
                    onBack = { navigateBack() },
                    onAddContact = { navigateTo("addContact") }, // Navega a agregar contacto
                    onContactSelected = { selectedContact ->
                        // Navega a la pantalla de transferencias con el contacto seleccionado
                        navigateTo("transfer?selectedContact=$selectedContact")
                    }
                )
            } // Ruta para Contacts

            // Transfer screen
            composable<Routes.Transfer> { TransferScreen() }
            composable<Routes.Login> { LogInScreen() }
            composable<Routes.Register> { RegistrationScreen() }
            composable<Routes.VerifyCode> { VerificationScreen() } 

            // composable<Routes.Profile> { MyProfile() }
            // TransferResult
            composable(
                route =
                "transferResult?amount={amount}&receiverName={receiverName}&bankName={bankName}&aliasSender={aliasSender}&aliasReceiver={aliasReceiver}&receiptId={receiptId}",
                arguments =
                listOf(
                    navArgument("amount") {
                        type = NavType.StringType
                        defaultValue = "0.00"
                    },
                    navArgument("receiverName") {
                        type = NavType.StringType
                        defaultValue = "Desconocido"
                    },
                    navArgument("bankName") {
                        type = NavType.StringType
                        defaultValue = "Desconocido"
                    },
                    navArgument("aliasSender") {
                        type = NavType.StringType
                        defaultValue = "AliasPropio"
                    },
                    navArgument("aliasReceiver") {
                        type = NavType.StringType
                        defaultValue = "AliasReceptor"
                    },
                    navArgument("receiptId") {
                        type = NavType.StringType
                        defaultValue = "000000"
                    }
                )
            ) { backStackEntry ->
                val amount = backStackEntry.arguments?.getString("amount") ?: "0.00"
                val receiverName =
                    backStackEntry.arguments?.getString("receiverName") ?: "Desconocido"
                val bankName = backStackEntry.arguments?.getString("bankName") ?: "Desconocido"
                val aliasSender =
                    backStackEntry.arguments?.getString("aliasSender") ?: "AliasPropio"
                val aliasReceiver =
                    backStackEntry.arguments?.getString("aliasReceiver") ?: "AliasReceptor"
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
                arguments =
                listOf(
                    navArgument("selectedContact") {
                        type = NavType.StringType
                        nullable = true // Permitir valores nulos
                    }
                )
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

                        val route =
                            "transferResult?amount=$amount&receiverName=$receiverName&bankName=$bankName&aliasSender=$aliasSender&aliasReceiver=$aliasReceiver&receiptId=$receiptId"
                        println("Navegando a: $route") // Debug para verificar la ruta
                        navigateTo(route)
                    },
                    onGoToContacts = { navigateTo("contacts") }
                )
            }
        }
    }
}