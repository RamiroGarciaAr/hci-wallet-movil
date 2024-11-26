package com.example.wallet_hci.screens.app.transfers


import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.wallet_hci.app.screens.home.TransferScreen


@Preview(showBackground = true)
@Composable
fun PreviewTransferScreen() {
    TransferScreen(
        accountBalance = "$1500.00", // Muestra un saldo simulado
        onCancel = {
            // Acción simulada al cancelar
            // Deberia volver al menu inicial. Es decir al Home. Mismo si aprieto la flechita para atras

            println("Cancel action triggered.")
        },
        onContinue = { paymentMethod, amount, description, cardId ->
            // Acción simulada al continuar
            println("Payment method: $paymentMethod")
            println("Amount: $amount")
            println("Description: $description")
            println("Card ID: ${cardId ?: "No card selected"}")
        }
    )
}
