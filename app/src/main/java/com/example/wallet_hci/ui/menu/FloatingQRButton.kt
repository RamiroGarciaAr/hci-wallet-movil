package com.example.wallet_hci.ui.menu

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FloatingQRButton() {
    // TODO: Implement FloatingQRButton
    LargeFloatingActionButton (
        onClick = {},
        shape = CircleShape,
    ){
        Icon(
            Icons.Filled.AddCircle,
            contentDescription = "Scan QR Code",
            Modifier.run { size(width = 10, height = 10) }
        )
    }
}