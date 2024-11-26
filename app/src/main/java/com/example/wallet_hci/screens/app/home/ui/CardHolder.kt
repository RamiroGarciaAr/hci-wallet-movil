package com.example.wallet_hci.app.screens.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.filled.ArrowDropDown
import com.example.wallet_hci.R
import android.graphics.Outline

sealed class Card(
    val issuer: String,
    val resourceId: Int
)
object VisaCard : Card(
    issuer = "visa",
    resourceId = R.drawable.ic_visa
)

object MastercardCard : Card(
    issuer = "mastercard",
    resourceId = R.drawable.ic_mastercard
)

val cardList = listOf<Card>(VisaCard, MastercardCard)

@Composable
fun CardHolder(
    issuer: String = "mastercard",
    lastFourDigits: String = "4242",
    modifier: Modifier = Modifier,
    onDelete: (() -> Unit)? = null, // Acción opcional para eliminar la tarjeta
    onClick: () -> Unit = {}
) {
    val card = cardList.find { it.issuer == issuer }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.primary_700),
            contentColor = colorResource(R.color.white)
        ),
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Información de la tarjeta
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = card?.resourceId ?: R.drawable.ic_mastercard),
                    contentDescription = "Card Icon",
                    tint = colorResource(R.color.white),
                    modifier = Modifier.size(32.dp)
                )
                Text("**** **** **** $lastFourDigits")
                Text("Card Holder")
            }

            // Botón de eliminación (si onDelete no es nulo)
            if (onDelete != null) {
                IconButton(
                    onClick = onDelete, // Acción de eliminación
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.fa_trash),
                        contentDescription = "Delete Card",
                        modifier = Modifier.height(height = 24.dp),
                        tint = colorResource(R.color.red)
                    )
                }
            }
        }
    }
}
