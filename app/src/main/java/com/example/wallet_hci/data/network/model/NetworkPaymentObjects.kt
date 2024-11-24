package com.example.wallet_hci.data.network.model
import com.example.wallet_hci.data.model.PaymentData
import kotlinx.serialization.Serializable


@Serializable
class NetworkLinkPaymentObject (val payment: NetworkPaymentData)
{
    fun asModel(): PaymentData {
        return payment.asModel()
    }
}