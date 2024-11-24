package com.example.wallet_hci.data.model

import com.example.wallet_hci.data.network.model.NetworkCardPayment

class CardPayment (
    val amount: Float,
    val description: String,
    val type: String,
    val cardId: Int,
    val receiverEmail: String
) {
    fun asNetworkModel(): NetworkCardPayment {
        return NetworkCardPayment(
            amount = amount,
            description = description,
            type = type,
            cardId = cardId,
            receiverEmail = receiverEmail
        )
    }
}