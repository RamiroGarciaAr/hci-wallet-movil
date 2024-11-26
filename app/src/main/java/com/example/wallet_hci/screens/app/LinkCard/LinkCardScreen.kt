package com.example.wallet_hci.screens.app.LinkCard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wallet_hci.R
import com.example.wallet_hci.routes.NavigatorProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LinkCardScreen(
    onCardLink: (String, String, String) -> Unit = { _, _, _ -> }, // Acción para agregar tarjeta (número, vencimiento, CVV)
    onBack: () -> Unit = {}
) {
    val navigator = NavigatorProvider.current
    var cardNumber by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var name by remember { mutableStateOf("")}
    var surname by remember { mutableStateOf("")}

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Vincular Tarjeta", color = colorResource(R.color.blue_bar)) },
                navigationIcon = {
                    IconButton(onClick = { navigator.navigateBack()}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = name,
                onValueChange = {name = it },
                label = {Text(stringResource(id = R.string.name))},
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = surname,
                onValueChange = {surname = it },
                label = {Text(stringResource(id = R.string.surname))},
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
                label = { Text(stringResource(id = R.string.card_number)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = expiryDate,
                onValueChange = { expiryDate = it },
                label = { Text(stringResource(id = R.string.expiration_date)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = cvv,
                onValueChange = { cvv = it },
                label = { Text(stringResource(id = R.string.cvv )) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Mostrar mensaje de error si existe
            errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (cardNumber.isBlank() || expiryDate.isBlank() || cvv.isBlank()) {
                        errorMessage = "Por favor, completa todos los campos."
                    } else {
                        errorMessage = null
                        onCardLink(cardNumber, expiryDate, cvv)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.blue_bar))
            ) {
                Text(text = "Vincular", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LinkCardScreenPreview() {
    LinkCardScreen(
        onCardLink = { number, expiry, cvv -> println("Tarjeta Vinculada: $number $expiry $cvv") },
        onBack = { println("Volver atrás") }
    )
}
