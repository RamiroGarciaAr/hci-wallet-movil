package com.example.wallet_hci.data.model

import com.example.wallet_hci.data.model.NetworkNewPaymentLink

class NewPaymentLink
    (
        val amount: Float,
        val description: String,
        val type: String
    ) {
    fun asNetworkModel(): NetworkNewPaymentLink {
        return NetworkNewPaymentLink(
            amount = amount,
            description = description,
            type = type
        )
    }
}