package com.example.wallet_hci.screens.app.transfers

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun PreviewTransferResultScreen() {
    TransferResultScreen(
        amount = "123.42",
        receiverName = "Damián Villablanca",
        bankName = "Banco Galicia",
        aliasSender = "sol.cielo.arcoiris",
        aliasReceiver = "sol.cielo.arcoiris",
        receiptId = "Comprobante123",
        onShare = { /* Acción simulada para compartir comprobante */ },
        onSaveContact = { /* Acción simulada para guardar contacto */ },
        onBack = { /* Acción simulada para volver atrás */ }
    )
}
