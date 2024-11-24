package com.example.wallet_hci.data.network.model

import com.example.wallet_hci.data.model.BalancePayment
import kotlinx.serialization.Serializable

@Serializable
class NetworkBalancePayment (
    val amount: Float,
    val description: String,
    val type: String,
    val receiverEmail: String

){
    fun asModel(): BalancePayment {

        return BalancePayment(
            amount = this.amount,
            description = this.description,
            type = this.type,
            receiverEmail = this.receiverEmail
        )
    }
}