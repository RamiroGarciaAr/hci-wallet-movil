package com.example.wallet_hci.data.network.model

import com.example.wallet_hci.data.model.LinkType
import com.example.wallet_hci.data.model.PaymentData

class NetworkPaymentData(
    val id: Int? = null,
    val type: String? = null,
    val amount: Float,
    val balanceBefore: Float? = null,
    val balanceAfter: Float? = null,
    val pending: Boolean? = null,
    val linkUuid: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val card: NetworkCard? = null
) {
    fun asModel(): PaymentData {
        return PaymentData(
            id = id,
            type = type?.let { LinkType.valueOf(it) } ?: LinkType.BALANCE,
            amount = amount,
            balanceBefore = balanceBefore ?: 0.0f,
            balanceAfter = balanceAfter ?: 0.0f,
            pending = pending ?: false,
            linkUuid = linkUuid,
            createdAt = createdAt ?: "",
            updatedAt = updatedAt ?: "",
            card = card?.asModel()
        )
    }
}