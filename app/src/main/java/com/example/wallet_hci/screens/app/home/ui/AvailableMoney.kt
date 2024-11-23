package com.example.wallet_hci.app.screens.home.ui

import com.example.wallet_hci.R
import androidx.compose.ui.res.stringResource

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import java.text.NumberFormat;
import java.util.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember



object AvailableMoneyState {
    var amount: Double = 0.0
    var isHidden by mutableStateOf(true)
}

@Composable
fun AvailableMoney() {
    var formatter = NumberFormat.getCurrencyInstance()

    formatter.setCurrency(Currency.getInstance(stringResource(R.string.currency)))
    formatter.setMaximumFractionDigits(2)
    formatter.setMinimumFractionDigits(2)

    val amount = 1685.85
    val formattedAmount = formatter.format(amount)



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text("Available Money")
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(){
            if (!AvailableMoneyState.isHidden) {
                Text(
                    text = formattedAmount,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            } else {
                Row(){
                    val firstDigitIndex = formattedAmount.indexOfFirst { it.isDigit() }
                    Text(
                        text = if (firstDigitIndex != -1) formattedAmount.substring(0, firstDigitIndex) else formattedAmount,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error,
                    )
                    Text(
                        text = formattedAmount.substring(firstDigitIndex).map { if (it.isDigit()) '*' else it }.joinToString(""),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            IconButton(onClick = { AvailableMoneyState.isHidden = !AvailableMoneyState.isHidden }) {
                Icon(
                    imageVector = if (AvailableMoneyState.isHidden) Icons.Filled.CheckCircle else Icons.Filled.AddCircle,
                    contentDescription = "Toggle visibility",
                )
            }
        }
    }
}