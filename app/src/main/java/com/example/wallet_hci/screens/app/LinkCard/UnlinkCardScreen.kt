package com.example.wallet_hci.screens.app.LinkCard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wallet_hci.R
import com.example.wallet_hci.app.screens.home.ui.CardHolder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnlinkCardScreen(
    cardList: List<CardData>,
    onCardUnlink: (CardData) -> Unit,
    onBack: () -> Unit
) {
    val cards = remember { mutableStateListOf(*cardList.toTypedArray()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Desvincular Tarjetas", color = colorResource(R.color.primary_500)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Mostrar tarjetas
            cards.forEach { card ->
                CardHolder(
                    issuer = card.issuer,
                    lastFourDigits = card.lastFourDigits,

                    onDelete = {
                        cards.remove(card) // Eliminar tarjeta de la lista
                        onCardUnlink(card) // Notificar al controlador
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UnlinkCardScreenPreview() {
    UnlinkCardScreen(
        cardList = listOf(
            CardData("visa", "1234", R.drawable.ic_visa),
            CardData("mastercard", "5678", R.drawable.ic_mastercard)
        ),
        onCardUnlink = { println("Tarjeta desvinculada: ${it.lastFourDigits}") },
        onBack = { println("Volver atrás") }
    )
}


