package com.example.wallet_hci.data.network

import com.example.wallet_hci.data.network.api.APIPaymentService
import com.example.wallet_hci.data.network.model.NetworkLinkPaymentObject
import com.example.wallet_hci.data.network.model.NetworkLinkUuid
import com.example.wallet_hci.data.network.model.NetworkNewPaymentLink
import com.example.wallet_hci.data.network.model.NetworkPayment

class PaymentRemoteDataSource(private val paymentService: APIPaymentService): RemoteDataSource()
{
    //Getters
    suspend fun getPayments(): List<NetworkPayment>? {
        return handleApiResponse {
            paymentService.getPayments()
        }
    }
    suspend fun getPaymentData(linkUuid: String): NetworkLinkPaymentObject? {
        return handleApiResponse {
            paymentService.getPaymentData(linkUuid)
        }
    }

    suspend fun generateLink(linkPayment: NetworkNewPaymentLink): NetworkLinkUuid?
    {
        return handleApiResponse {
            paymentService.generateLink(linkPayment)
        }
    }
}