package com.example.wallet_hci.screens.app.registration

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wallet_hci.R
import com.example.wallet_hci.UiStateProvider
import com.example.wallet_hci.app.routes.Routes
import com.example.wallet_hci.data.DataSourceException
import com.example.wallet_hci.data.model.RegistrationUser
import com.example.wallet_hci.data.repository.UserRepositoryProvider
import com.example.wallet_hci.routes.NavigatorProvider
import java.util.Date
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class RegistrationAdditionalInfoParams(
        val email: String,
        val password: String,
        val confirmPassword: String
)

@Composable
fun RegistrationAdditionalInfo(params: RegistrationAdditionalInfoParams) {

        val navigator = NavigatorProvider.current
        val uiState = UiStateProvider.current
        val userRepository = UserRepositoryProvider.current
        val name = remember { mutableStateOf("") }
        val lastName = remember { mutableStateOf("") }

        //     val errorResource = stringResource(id = R.string.error_registration_empty_fields)

        val onCancel: () -> Unit = { navigator.navigateBack() }
        val onRegisterComplete: () -> Unit = {
                CoroutineScope(Dispatchers.Main).launch {
                        val birthDate = Date(123456789)
                        try {
                                if (name.value.isBlank() || lastName.value.isBlank())
                                        throw IllegalArgumentException(
                                                "Por favor, rellena todos los campos."
                                        )
                                userRepository.register(
                                        RegistrationUser(
                                                firstName = name.value,
                                                lastName = lastName.value,
                                                email = params.email,
                                                birthDate = birthDate,
                                                password = params.password
                                        )
                                )
                                navigator.navigateTo(Routes.VerifyCode)
                        } catch (e: DataSourceException) {
                                when (e.code) {
                                        400 ->
                                                uiState.snackbarHostState.showSnackbar(
                                                        message = "El usuario ya existe"
                                                )
                                        401 ->
                                                uiState.snackbarHostState.showSnackbar(
                                                        message = "El usuario ya existe"
                                                )
                                        404 ->
                                                uiState.snackbarHostState.showSnackbar(
                                                        message =
                                                                "Los datos ingresados son incorrectos"
                                                )
                                        else ->
                                                uiState.snackbarHostState.showSnackbar(
                                                        message = "Error al registrar"
                                                )
                                }
                        } catch (e: Exception) {
                                uiState.snackbarHostState.showSnackbar(
                                        message = e.message ?: "Error al registrar"
                                )
                        }
                }
        }

        Scaffold { paddingValues ->
                Column(
                        modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.Start
                ) {
                        // Título de bienvenida
                        Text(
                                text = stringResource(id = R.string.welcome_title),
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(id = R.color.blue_bar)
                        )

                        Text(
                                text = stringResource(id = R.string.welcome_subtitle),
                                style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Campos de texto
                        OutlinedTextField(
                                value = name.value,
                                onValueChange = { name.value = it },
                                label = { Text(stringResource(id = R.string.name_label)) },
                                placeholder = {
                                        Text(stringResource(id = R.string.name_placeholder))
                                },
                                modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                                value = lastName.value,
                                onValueChange = { lastName.value = it },
                                label = { Text(stringResource(id = R.string.last_name_label)) },
                                placeholder = {
                                        Text(stringResource(id = R.string.last_name_placeholder))
                                },
                                modifier = Modifier.fillMaxWidth()
                        )

                        //     OutlinedTextField(
                        //             value = "",
                        //             onValueChange = { /* Nacionalidad */},
                        //             label = { Text(stringResource(id =
                        // R.string.nationality_label)) },
                        //             placeholder = { Text(stringResource(id =
                        // R.string.nationality_placeholder)) },
                        //             modifier = Modifier.fillMaxWidth()
                        //     )

                        Spacer(modifier = Modifier.height(16.dp))

                        // para términos y condiciones
                        Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                        checked = false,
                                        onCheckedChange = { /* Manejar cambios */}
                                )
                                Text(
                                        text = stringResource(id = R.string.terms_conditions_text),
                                        modifier = Modifier.padding(start = 8.dp),
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.primary
                                )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        // Botones Cancelar y Registrarse
                        Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                                Button(
                                        onClick = onCancel,
                                        colors =
                                                ButtonDefaults.buttonColors(
                                                        containerColor =
                                                                MaterialTheme.colorScheme.error
                                                )
                                ) { Text(text = stringResource(id = R.string.cancel_button)) }

                                Button(
                                        onClick = onRegisterComplete,
                                        colors =
                                                ButtonDefaults.buttonColors(
                                                        containerColor =
                                                                colorResource(id = R.color.blue_bar)
                                                )
                                ) { Text(text = stringResource(id = R.string.register_button)) }
                        }
                }
        }
}
