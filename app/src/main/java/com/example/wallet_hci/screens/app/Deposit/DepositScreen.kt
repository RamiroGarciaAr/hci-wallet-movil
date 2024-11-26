package com.example.wallet_hci.screens.app.Deposit



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.wallet_hci.R
import com.example.wallet_hci.routes.NavigatorProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepositScreen() {
    val navigator = NavigatorProvider.current
    var amount by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Ingreso", color = colorResource(R.color.blue_bar)) },
                navigationIcon = {
                    IconButton(onClick = { navigator.navigateBack() }) {
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
            Text(
                text = stringResource(id = R.string.add_amount),
                style = MaterialTheme.typography.titleMedium
            )
            TextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("$ 0.00") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Cuenta predeterminada
            Text(
                text = stringResource(id = R.string.external_account),
                style = MaterialTheme.typography.titleMedium
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.blue_bar),
                    contentColor = colorResource(R.color.blue_bar)
                )
            ) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = stringResource(id = R.string.mercado_pay),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            // Mostrar mensaje de error si hay
            errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botones
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = { navigator.navigateBack() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "Cancelar")
                }
                Button(
                    onClick = {
                        val parsedAmount = amount.toDoubleOrNull()
                        if (parsedAmount == null || parsedAmount <= 0) {
                            errorMessage = "Por favor, ingresa un monto válido."
                        } else {
                            errorMessage = null
                            navigator.navigateTo("depositResult/$parsedAmount")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.blue_bar)
                    )
                ) {
                    Text(
                        text = "Continuar",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}
