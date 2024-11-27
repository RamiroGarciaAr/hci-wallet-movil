package com.example.wallet_hci.screens.app.passwordRecovery.code

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun PreviewPasswordResetScreen() {
    PasswordResetScreen(
        emailMethod = "example@mail.com",
        onCodeEntered = { code ->
            // Acción al completar el código (para preview no se ejecuta nada)
        },
        onBack = {
            // Acción al presionar "Volver" (para preview no se ejecuta nada)
        },
        onResend = {
            // Acción al presionar "Reenviar" (para preview no se ejecuta nada)
        }
    )
}