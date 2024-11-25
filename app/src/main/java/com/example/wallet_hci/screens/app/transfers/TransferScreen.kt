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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import com.example.wallet_hci.app.screens.home.ui.CardHolder


import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferScreen(
    accountBalance: String = "$1500.00", // Saldo actual del usuario
    onCancel: () -> Unit = {},
    onContinue: (paymentMethod: String, amount: Double, description: String, cardId: String?) -> Unit = { _, _, _, _ -> }
) {
    val amountMinimumError = stringResource(id = R.string.amount_minimum)
    val noValidValue = stringResource(id = R.string.no_valid_value)
    val noValidCard = stringResource(id = R.string.no_valid_card)
    val coroutineScope = rememberCoroutineScope()

    // Estados locales para manejar las entradas del usuario
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedPaymentMethod by remember { mutableStateOf("BALANCE") } // Default: Balance
    var selectedCardId by remember { mutableStateOf<String?>(null) } // Null para BALANCE
    var errorMessage by remember { mutableStateOf<String?>(null) }

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
            TextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("$ 0.00") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Seleccionar método de pago
            Text(text = stringResource(id = R.string.select_payment_method), style = MaterialTheme.typography.titleMedium)
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Opción Dinero en cuenta
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(R.color.blue_bar),
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        selectedPaymentMethod = "BALANCE"
                        selectedCardId = null
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = stringResource(id = R.string.balance_money, accountBalance),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }


                // Tarjetas de pago
                CardHolder(
                    issuer = "mastercard",
                    lastFourDigits = "0854",
                    onClick = {
                        selectedPaymentMethod = "CARD"
                        selectedCardId = "0854" // Ejemplo de ID de la tarjeta
                    }
                )
                CardHolder(
                    issuer = "mastercard",
                    lastFourDigits = "0734",
                    onClick = {
                        selectedPaymentMethod = "CARD"
                        selectedCardId = "0734" // Ejemplo de ID de la tarjeta
                    }
                )
            }

            // Ingresar motivo
            Text(text = stringResource(id = R.string.enter_reason), style = MaterialTheme.typography.titleMedium)
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(stringResource(id = R.string.reason_transfer)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.weight(1f))

            // Mostrar mensaje de error si hay
            errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Botones de acciones
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = onCancel,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = colorResource(R.color.blue_bar)
                    )
                ) {
                    Text(text = stringResource(id = R.string.cancel))
                }
                Button(
                    onClick = {
                        coroutineScope.launch {
                            // Intentar convertir el monto ingresado a un número
                            val parsedAmount = amount.toDoubleOrNull()

                            // Validar que sea un número válido y mayor a 0
                            if (parsedAmount == null || parsedAmount <= 0) {
                                errorMessage = amountMinimumError // Mensaje de error
                                return@launch
                            }

                            // Validar que el motivo no esté vacío
                            if (description.isBlank()) {
                                errorMessage = noValidValue // Mensaje de error para motivo
                                return@launch
                            }

                            // Validar el método de pago
                            if (selectedPaymentMethod == "CARD" && selectedCardId == null) {
                                errorMessage = noValidCard // Mensaje de error para tarjeta
                                return@launch
                            }

                            // Si todo es válido, resetear el error y proceder con la acción
                            errorMessage = null

                            onContinue(
                                selectedPaymentMethod,
                                parsedAmount,
                                description,
                                selectedCardId
                            )
                        }
                    },
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
