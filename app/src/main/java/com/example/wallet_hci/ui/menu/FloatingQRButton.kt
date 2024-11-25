package com.example.wallet_hci.ui.menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.colorResource
import com.example.wallet_hci.R

import androidx.compose.ui.unit.dp

import com.example.wallet_hci.app.routes.Navigator

@Composable
fun FloatingQRButton(navigator: Navigator) {
    // TODO: Implement FloatingQRButton
    FloatingActionButton (
        onClick = { navigator.navigateTo("scan") },
        shape = CircleShape,
        modifier = Modifier.size(68.dp),
        containerColor = colorResource(R.color.primary_500),
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 15.dp)
    ){
        Icon(
            painter = painterResource(id = R.drawable.ic_qrcode),
            contentDescription = "Scan QR Code",
            modifier = Modifier.size(32.dp),
            tint = colorResource(R.color.white)
        )
    }
}