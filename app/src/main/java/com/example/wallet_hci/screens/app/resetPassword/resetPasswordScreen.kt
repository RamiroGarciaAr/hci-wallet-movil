package com.example.wallet_hci.screens.app.resetPassword

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallet_hci.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreen(
    onPasswordChange: (String) -> Unit = {},
    onNavigateBack: () -> Unit = {}
) {
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Restablecer contraseña",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF0056D2) // Azul
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color(0xFF0056D2) // Azul
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Campos de contraseñas en el centro
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Nueva contraseña") },
                    placeholder = { Text("********") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF0056D2), // Azul
                        cursorColor = Color(0xFF0056D2), // Azul
                        focusedLabelColor = Color(0xFF0056D2) // Azul
                    )
                )

                OutlinedTextField(
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    label = { Text("Confirmar contraseña") },
                    placeholder = { Text("********") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF0056D2), // Azul
                        cursorColor = Color(0xFF0056D2), // Azul
                        focusedLabelColor = Color(0xFF0056D2) // Azul
                    )
                )
            }

            // Botones en la parte inferior
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        if (password.value == confirmPassword.value && password.value.isNotBlank()) {
                            onPasswordChange(password.value)
                        }
                    },
                    enabled = password.value == confirmPassword.value && password.value.isNotBlank(),
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0056D2) // Azul
                    )
                ) {
                    Text(text = "Cambiar contraseña", color = Color.White)
                }

                TextButton(onClick = onNavigateBack, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Cancelar y volver", color = Color(0xFF0056D2))
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewResetPasswordScreen() {
    MaterialTheme {
        ResetPasswordScreen(
            onPasswordChange = { println("Contraseña cambiada: $it") },
            onNavigateBack = { println("Volver atrás") }
        )
    }
}
