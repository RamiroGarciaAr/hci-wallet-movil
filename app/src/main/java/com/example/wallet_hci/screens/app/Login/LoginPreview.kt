package com.example.wallet_hci.screens.app.Login

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.wallet_hci.R
import com.example.wallet_hci.routes.NavigatorProvider
import com.example.wallet_hci.data.repository.UserRepositoryProvider
import com.example.wallet_hci.app.routes.Routes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch 

import com.example.wallet_hci.SessionProvider
import com.example.wallet_hci.UiStateProvider
import com.example.wallet_hci.data.DataSourceException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    // onLoginClick: (String, String) -> Unit = { _, _ -> },
    // onRegisterClick: () -> Unit = {},
    // onForgotPasswordClick: () -> Unit = {}
) {
    val navigator = NavigatorProvider.current
    val sessionManager = SessionProvider.current
    val userRepository = UserRepositoryProvider.current
    val uiState = UiStateProvider.current
    // Campos de entrada
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val onRegisterClick = { navigator.navigateTo(Routes.Register) }
    val onForgotPasswordClick = { navigator.navigateTo(Routes.Login) }

    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

    val onLogInClick = {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                if (email.value.isBlank() || password.value.isBlank()) {
                    throw IllegalArgumentException("Por favor, rellena todos los campos.")
                }
                if (!emailRegex.matches(email.value)) {
                    throw IllegalArgumentException("El mail no tiene un formato válido.")
                }

                userRepository.login(email = email.value, password = password.value)
                navigator.navigateTo(Routes.Home) // Navigate to Home on success
            } catch (e: IllegalArgumentException) {
                uiState.snackbarHostState.showSnackbar(message = e.message ?: "Error al iniciar sesión")
            } catch (e: DataSourceException) {
                when (e.code) {
                    400, 401 -> uiState.snackbarHostState.showSnackbar(message = "El usuario no existe")
                    404 -> uiState.snackbarHostState.showSnackbar(message = "Los datos ingresados son incorrectos")
                    else -> uiState.snackbarHostState.showSnackbar(message = "Error al iniciar sesión")
                }
            } catch (e: Exception) {
                uiState.snackbarHostState.showSnackbar(message = "Error desconocido")
            }
        }
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen del logo
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 16.dp)
                    .clip(CircleShape)
            )



            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                placeholder = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Contraseña") },
                placeholder = { Text("**********") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            // Botón de inicio de sesión
            Button(
                onClick = {onLogInClick()},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.primary_500)
                )
            ) {
                Text(text = "Iniciar sesión", color = Color.White)
            }

            // Enlace para recuperar contraseña
            TextButton(onClick = onForgotPasswordClick) {
                Text(text = "¿Olvidaste tu contraseña? Recupérala", color = Color(0xFF0056D2))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Separador
            Text(text = "o", color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            // Botón para crear cuenta
            Button(
                onClick = { onRegisterClick() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.primary_500)
                )
            ) {
                Text(text = "Crear cuenta", color = Color.White)
            }
        }
    }
}

// @Preview(showBackground = true)
// @Composable
// fun LogInScreenPreview() {
//     LogInScreen()
// }
