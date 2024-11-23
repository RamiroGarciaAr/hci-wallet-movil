package com.example.wallet_hci.app.screens.home

import com.example.wallet_hci.app.screens.home.ui.*
import com.example.wallet_hci.ui.layout.ViewModel
import com.example.wallet_hci.R

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.colorResource
import kotlinx.serialization.Serializable

// Related to the Card
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeView(){
    Card(
        // colors = colorResource(R.color.primary_100),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        
        modifier = Modifier
            .size(200.dp)
            .padding(8.dp)

    ) {
        AvailableMoney()
    }
}