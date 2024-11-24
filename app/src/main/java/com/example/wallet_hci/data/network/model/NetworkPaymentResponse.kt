package com.example.wallet_hci.data.network.model

import com.example.wallet_hci.data.model.PaymentResponse
import kotlinx.serialization.Serializable

@Serializable
class NetworkPaymentResponse (
    val success: Boolean
) {
    fun asModel(): PaymentResponse {
        return PaymentResponse(
            success = this.success
        )

    }
}