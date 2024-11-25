package com.example.wallet_hci.app.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.example.wallet_hci.R
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.wallet_hci.app.screens.home.ui.CardHolder
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferScreen(
    accountBalance: String = "1500.00", // Saldo actual del usuario
    selectedContact: String? = null, // Contacto seleccionado
    onGoToContacts: () -> Unit = {}, // Acción para abrir pantalla de contactos
    onCancel: () -> Unit = {}, // Acción para cancelar
    onContinue: (String, Double, String, String?) -> Unit = { _, _, _, _ -> } // Acción para continuar
) {
    val amountMinimumError = stringResource(id = R.string.amount_minimum)
    val noValidValue = stringResource(id = R.string.no_valid_value)
    val noValidCard = stringResource(id = R.string.no_valid_card)
    val insufficientFundsError = stringResource(id = R.string.insufficient_funds)
    val noContactSelectedError = stringResource(id = R.string.no_contact_selected) // Nuevo mensaje de error
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
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
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
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    // Botón único para ir a la pantalla de contactos
                    Button(
                        onClick = onGoToContacts,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.blue_bar)
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.go_to_contacts),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                item {
                    // Muestra el contacto seleccionado o un mensaje si no hay ninguno
                    if (selectedContact.isNullOrBlank() ) {
                        Text(
                            text = stringResource(id = R.string.no_contact_selected_warning),
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.selected_contact, selectedContact),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                item {
                    // Ingresar monto
                    Text(
                        text = stringResource(id = R.string.enter_amount),
                        style = MaterialTheme.typography.titleMedium
                    )
                    TextField(
                        value = amount,
                        onValueChange = { amount = it },
                        label = { Text("$ 0.00") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }

                item {
                    // Seleccionar método de pago
                    Text(
                        text = stringResource(id = R.string.select_payment_method),
                        style = MaterialTheme.typography.titleMedium
                    )
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
                                selectedCardId = "0854"
                            }
                        )
                        CardHolder(
                            issuer = "mastercard",
                            lastFourDigits = "0734",
                            onClick = {
                                selectedPaymentMethod = "CARD"
                                selectedCardId = "0734"
                            }
                        )
                    }
                }

                item {
                    // Ingresar motivo
                    Text(
                        text = stringResource(id = R.string.enter_reason),
                        style = MaterialTheme.typography.titleMedium
                    )
                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text(stringResource(id = R.string.reason_transfer)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }

                item {
                    // Mostrar mensaje de error si hay
                    errorMessage?.let {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            // Botones de acciones fuera de LazyColumn
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
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
                            val parsedAmount = amount.toDoubleOrNull()
                            val balance = accountBalance.toDoubleOrNull() ?: 0.0
                            when {
                                selectedContact == null -> {
                                    errorMessage = noContactSelectedError
                                    return@launch
                                }
                                parsedAmount == null || parsedAmount <= 0 -> {
                                    errorMessage = amountMinimumError
                                    return@launch
                                }
                                description.isBlank() -> {
                                    errorMessage = noValidValue
                                    return@launch
                                }
                                selectedPaymentMethod == "CARD" && selectedCardId == null -> {
                                    errorMessage = noValidCard
                                    return@launch
                                }
                                selectedPaymentMethod == "BALANCE" && parsedAmount > balance -> {
                                    errorMessage = insufficientFundsError
                                    return@launch
                                }
                            }

                            errorMessage = null
                            onContinue(
                                selectedPaymentMethod,
                                parsedAmount!!, // Aseguramos que no sea null después de las validaciones
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
