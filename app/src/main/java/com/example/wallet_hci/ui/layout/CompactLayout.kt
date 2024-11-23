package com.example.wallet_hci.ui.layout


import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CompactLayout() {
   Column(
       modifier = Modifier
           .fillMaxSize()
           .padding(16.dp),
       verticalArrangement = Arrangement.Center
   ) {
       Text("Compact Layout")
   }
}