
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
fun CardWrapper(
    elevation: CardElevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    colors: CardColors = CardDefaults.cardColors(containerColor = colorResource(R.color.white)),
    modifier: Modifier = Modifier,
    
    content: @Composable () -> Unit
) {
    Card( 
        elevation = elevation,
        colors = colors,
        modifier = modifier.fillMaxWidth().imePadding()
    ) {
        Box(modifier = Modifier.padding(8.dp)){
            content()
        }
    }
}