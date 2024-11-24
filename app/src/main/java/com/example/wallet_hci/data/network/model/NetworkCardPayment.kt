package com.example.wallet_hci.data.network.model

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
            amount = this.amount,
            description = this.description,
            type = this.type,
            cardId = this.cardId,
            receiverEmail = this.receiverEmail
        )
    }
}