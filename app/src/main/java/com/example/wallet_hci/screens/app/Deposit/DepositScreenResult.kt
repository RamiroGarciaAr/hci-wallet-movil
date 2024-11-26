package com.example.wallet_hci.screens.app.Deposit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import com.example.wallet_hci.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wallet_hci.ui.components.CardStyle
import com.example.wallet_hci.ui.components.CustomCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepositResultScreen(
    depositedAmount: Double,
    accountName: String,
    receiptId: String = "41231", // ID de recibo generado
    onContinue: () -> Unit = {} // Acción para continuar
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Ingreso de dinero", color = colorResource(R.color.primary_500)) },
                navigationIcon = {
                    IconButton(onClick = onContinue) {
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
            // Mensaje de éxito
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.primary_500)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Ingreso de dinero exitoso",
                        style = MaterialTheme.typography.headlineSmall,
                        color = colorResource(id = R.color.primary_100),
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Se ingresaron $${String.format("%.2f", depositedAmount)} a tu cuenta de $accountName",
                        style = MaterialTheme.typography.bodyLarge,
                        color = colorResource(id = R.color.primary_100)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Comprobante #$receiptId",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(id=R.color.primary_200)
                    )
                }
            }

            // Detalles del depósito
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Mercado Pago", // Cuenta emisora fija
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "CVU: 0000034318741111138992", // CVU de la cuenta emisora
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Text(
                        text = "→",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Column {
                        Text(
                            text = accountName, // Nombre de la cuenta destino
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Alias: sol.cielo.arcoiris", // Alias del destino
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botón para continuar
            Button(
                onClick = onContinue,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.blue_bar)
                )
            ) {
                Text(text = "Continuar", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DepositResultScreenPreview() {
    DepositResultScreen(
        depositedAmount = 123.45,
        accountName = "Pagozen",
        receiptId = "41231",
        onContinue = {}
    )
}
