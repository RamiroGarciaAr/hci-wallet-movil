package com.example.wallet_hci.screens.app.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.wallet_hci.R
import com.example.wallet_hci.data.repository.UserRepositoryProvider
import com.example.wallet_hci.routes.NavigatorProvider

import com.example.wallet_hci.data.model.RegistrationUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    // onRegisterClick: (String, String, String) -> Unit = { _, _, _ -> }
) {
    val navigator = NavigatorProvider.current
    val userRepository = UserRepositoryProvider.current

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo de la aplicación
            Image(
                painter = painterResource(id = R.drawable.app_logo), // Cambia a tu recurso de logo
                contentDescription = stringResource(id = R.string.app_logo_description),
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp)
            )

            // Campos de entrada
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var confirmPassword by remember { mutableStateOf("") }

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(id = R.string.email_label)) },
                placeholder = { Text(stringResource(id = R.string.email_placeholder)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(id = R.string.password_label)) },
                placeholder = { Text(stringResource(id = R.string.password_placeholder)) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(stringResource(id = R.string.confirm_password_label)) },
                placeholder = { Text(stringResource(id = R.string.confirm_password_placeholder)) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            // Botón "Registrarse"
            Button(
                onClick = { 

                    val dateBirth = Date(123456789)
                    val registrationUser = RegistrationUser(
                        firstName = "Test",
                        lastName = "Test",
                        email = email,
                        birthDate = dateBirth,
                        password = password
                    )

                    CoroutineScope(Dispatchers.Main).launch {
                        userRepository.register(registrationUser)
                    }
                 },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.blue_bar) // Cambia a tu color azul
                )
            ) {
                Text(text = stringResource(id = R.string.register_button), color = Color.White)
            }
        }
    }
}


