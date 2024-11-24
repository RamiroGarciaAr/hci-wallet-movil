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
            id = this.id,
            type = this.type?.let { LinkType.valueOf(it) } ?: LinkType.BALANCE,
            amount = this.amount,
            balanceBefore = this.balanceBefore ?: 0.0f,
            balanceAfter = this.balanceAfter ?: 0.0f,
            pending = this.pending ?: false,
            linkUuid = this.linkUuid,
            createdAt = this.createdAt ?: "",
            updatedAt = this.updatedAt ?: "",
            card = this.card?.asModel()
        )
    }
}