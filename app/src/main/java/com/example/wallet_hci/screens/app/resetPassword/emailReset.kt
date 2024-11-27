package com.example.wallet_hci.screens.app.resetPassword

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallet_hci.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordEmailScreenStyled(onContinueClick: (String) -> Unit, onBackClick: () -> Unit) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Restablecer contraseña",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 32.sp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Ingrese su mail para recuperar la contraseña",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF673AB7), // Violeta para borde enfocado
                cursorColor = Color(0xFF673AB7), // Violeta para el cursor
                focusedLabelColor = Color(0xFF673AB7) // Violeta para etiqueta activa
            )
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onBackClick,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary_500)) // Fondo violeta
            ) {
                Text("Volver", color = Color.White)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { onContinueClick(email) },
                modifier = Modifier.weight(1f),
                enabled = email.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.primary_500), // Igual que "Volver"
                    contentColor = Color.White // Color del texto
                )
            ) {
                Text("Continuar", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewResetPasswordEmailScreenStyled() {
    MaterialTheme {
        ResetPasswordEmailScreenStyled(
            onContinueClick = { email -> println("Correo ingresado: $email") },
            onBackClick = { println("Volver atrás desde Email Screen") }
        )
    }
}

