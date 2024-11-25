package com.example.wallet_hci.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text

enum class CardStyle(val headerColor: Color, val textColor: Color) {
    Default(Color.Gray, Color.Black),
    Primary(Color(0xFF1D60F8), Color.White),
    Danger(Color(0xFFED5B5B), Color.White),
    Success(Color(0xFF4CC759), Color.White)
}

// Composable Function
@Composable
fun CustomCard(
    title: String,
    style: CardStyle = CardStyle.Default,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    androidx.compose.material3.Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = androidx.compose.material3.CardDefaults.cardElevation(8.dp), // bordes + degrade
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(style.headerColor)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = style.textColor,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            // Body
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                content()
            }
        }
    }
}
