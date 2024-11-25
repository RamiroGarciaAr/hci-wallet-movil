package com.example.wallet_hci.data.model
import com.example.wallet_hci.data.model.PaymentData

class NetworkLinkPaymentObject (val payment: NetworkPaymentData)
{
    fun asModel(): PaymentData {
        return payment.asModel()
    }
}