package com.example.wallet_hci.app.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.example.wallet_hci.R
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.ui.res.colorResource
import com.example.wallet_hci.app.screens.home.ui.CardHolder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferScreen(
    onCancel: () -> Unit = {},
    onContinue: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.transfer), color = colorResource(R.color.blue_bar)) },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
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
            // Ingresar monto
            Text(text = stringResource(id = R.string.enter_amount), style = MaterialTheme.typography.titleMedium)
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.blue_bar),
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$ 0.00",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            // Seleccionar método de pago
            Text(text = stringResource(id = R.string.select_payment_method), style = MaterialTheme.typography.titleMedium)
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CardHolder(issuer = "mastercard", lastFourDigits = "0854", onClick = {})
                CardHolder(issuer = "mastercard", lastFourDigits = "0734", onClick = {})
            }

            // Ingresar motivo
            Text(text = stringResource(id = R.string.enter_reason), style = MaterialTheme.typography.titleMedium)
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.blue_bar),
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.padding(16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Varios",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botones de acciones
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = onCancel,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = colorResource(R.color.blue_bar) // Color azul para el texto del botón
                    )
                ) {
                    Text(text = stringResource(id = R.string.cancel))
                }
                Button(
                    onClick = onContinue,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.blue_bar)
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.continue_action),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}
