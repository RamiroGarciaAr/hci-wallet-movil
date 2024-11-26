package com.example.wallet_hci.screens.app.Login

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.wallet_hci.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    onLoginClick: (String, String) -> Unit = { _, _ -> },
    onRegisterClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
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
                    .size(120.dp)
                    .padding(bottom = 16.dp)
            )

            // Campos de entrada
            val email = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }

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
                label = { Text(stringResource.(id=R.string.password)) },
                placeholder = { Text("**********") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            // Botón de inicio de sesión
            Button(
                onClick = { onLoginClick(email.value, password.value) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0056D2) // Cambia a tu color azul
                )
            ) {
                Text(text = "Iniciar sesión", color = Color.White)
            }

            // Enlace para recuperar contraseña
            TextButton(onClick = onForgotPasswordClick) {
                Text(text = stringResource.(id=R.string.forgot_password)), color = Color(0xFF0056D2))
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
                    containerColor = Color(0xFF0056D2) // Cambia a tu color azul
                )
            ) {
                Text(text = stringResource.(id=R.string.create_account), color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogInScreenPreview() {
    LogInScreen()
}
