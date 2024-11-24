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
    onClick: () -> Unit = {}
) {
    val card = cardList.find { it.issuer == issuer }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.primary_700),
            contentColor = colorResource(R.color.white)
        ),
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick
    ){
        Column(
            modifier = Modifier
                .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
            Icon(
                painter = painterResource(id = card?.resourceId ?: 0),
                contentDescription = "Visa",
                tint = colorResource(R.color.white),
                modifier = Modifier.size(32.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.fillMaxWidth()
            ){
                Column(){
                    Text("**** **** **** $lastFourDigits")
                    Text("Card Holder")
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "Arrow",
                    tint = colorResource(R.color.white),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}