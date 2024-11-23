package com.example.wallet_hci.app.screens.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.colorResource
import com.example.wallet_hci.R

@Composable
fun CardHolder() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.primary_100)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text("Card Holder")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Card Number")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Expiration Date")
                Spacer(modifier = Modifier.height(8.dp))
                Text("CVV")
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text("Card Holder")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Card Number")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Expiration Date")
                Spacer(modifier = Modifier.height(8.dp))
                Text("CVV")
            }
        }
    }
}