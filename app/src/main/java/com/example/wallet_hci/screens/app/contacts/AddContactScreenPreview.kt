package com.example.wallet_hci.screens.app.contacts

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview




@Preview(showBackground = true)
@Composable
fun AddContactScreenPreview() {
    AddContactScreen(
        onBack = { /* Simula volver atrás */ },
        onAdd = { name, cvuOrAlias, email ->
            println("Nombre: $name, CVU o Alias: $cvuOrAlias, Email: $email")
        }
    )
}
