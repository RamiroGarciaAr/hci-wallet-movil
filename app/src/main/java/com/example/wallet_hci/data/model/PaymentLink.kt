package com.example.wallet_hci.data.model

import com.example.wallet_hci.data.model.NetworkPaymentLink

class PaymentLink( val type: LinkType,
                   val cardId: Int? = null
) {
    fun asNetworkModel(): NetworkPaymentLink
    {
        return NetworkPaymentLink(
            type = type.name,
            cardId = cardId
        )
    }
}