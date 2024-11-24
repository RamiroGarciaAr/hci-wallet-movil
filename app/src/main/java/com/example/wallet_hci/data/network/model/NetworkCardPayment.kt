package com.example.wallet_hci.data.model

import com.example.wallet_hci.data.model.CardPayment
import kotlinx.serialization.Serializable

@Serializable
class NetworkCardPayment(
    val amount: Float,
    val description: String,
    val type: String,
    val cardId: Int,
    val receiverEmail: String
){
    fun asModel(): CardPayment {
        return CardPayment(
            amount = amount,
            description = description,
            type = type,
            cardId = cardId,
            receiverEmail = receiverEmail
        )
    }
}