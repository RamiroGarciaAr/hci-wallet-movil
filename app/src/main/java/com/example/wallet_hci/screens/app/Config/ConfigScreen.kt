package com.example.wallet_hci.screens.app.Config

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ConfigurationAccordionMenu(viewModel: AccordionViewModel = viewModel()) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Sections
        item {
            AccordionItem(
                title = "Personales",
                content = listOf("Nombre", "Apellido", "Email"),
                viewModel = viewModel,
                txtBarMsg = listOf("Nombre", "Apellido", "Email")
            )
        }
        item {
            AccordionItem(
                title = "Contacto y Seguridad",
                content = listOf("Número de teléfono"),
                viewModel = viewModel,
                txtBarMsg = listOf("Número de teléfono")
            )
        }
        item {
            AccordionItem(
                title = "Bancaria",
                content = listOf("Tu Alias", "Añadir Tarjeta"),
                viewModel = viewModel,
                txtBarMsg = listOf("Tu Alias", "Añadir Tarjeta")
            )
        }
        item {
            AccordionItem(
                title = "Zona de Peligro",
                content = listOf("Cambiar contraseña", "Eliminar Cuenta"),
                isDangerZone = true,
                viewModel = viewModel
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConfigurationAccordionMenu() {
    MaterialTheme {
        ConfigurationAccordionMenu()
    }
}