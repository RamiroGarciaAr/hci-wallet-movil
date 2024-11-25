package com.example.wallet_hci.screens.app.registration

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen { email, password, confirmPassword ->
        // Aquí puedes simular la acción de registro para pruebas
        println("Email: $email, Password: $password, ConfirmPassword: $confirmPassword")
    }
}
