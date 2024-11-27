package com.example.wallet_hci.app.screens.home.ui

import com.example.wallet_hci.R
import com.example.wallet_hci.ui.heroicons.Eye
import com.example.wallet_hci.ui.heroicons.EyeSlash
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
import com.example.wallet_hci.SessionProvider
import com.example.wallet_hci.data.network.api.PaymentApiServiceProvider
import com.example.wallet_hci.data.network.api.WalletApiServiceProvider
import com.example.wallet_hci.data.repository.WalletRepositoryProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


object AvailableMoneyState {
    var amount: Double = 0.0
    var isHidden by mutableStateOf(true)
}

@Composable
fun AvailableMoney() {

    val walletRepository = WalletRepositoryProvider.current
    val sessionManager = SessionProvider.current
    
    val balanceState = remember { mutableStateOf(0.0f) }

    CoroutineScope(Dispatchers.Main).launch {
        val token = sessionManager.loadAuthToken() ?: ""
        val balance = walletRepository.getBalance(token = token)
        balanceState.value = balance?.balance ?: 0.0f
    }

    var formatter = NumberFormat.getCurrencyInstance()

    formatter.setCurrency(Currency.getInstance(stringResource(R.string.currency)))
    formatter.setMaximumFractionDigits(2)
    formatter.setMinimumFractionDigits(2)

    val formattedAmount = formatter.format(balanceState.value)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(stringResource(R.string.available_money))
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
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
                    imageVector = if (AvailableMoneyState.isHidden) EyeSlash else Eye,
                    contentDescription = "Toggle visibility",
                )
            }
        }
    }
}