package com.example.wallet_hci.data.model

import com.example.wallet_hci.data.api.APIPaymentService
import com.example.wallet_hci.data.model.NetworkBalancePayment
import com.example.wallet_hci.data.model.NetworkCardPayment
import com.example.wallet_hci.data.model.NetworkLinkPaymentObject
import com.example.wallet_hci.data.model.NetworkLinkUuid
import com.example.wallet_hci.data.model.NetworkNewPaymentLink
import com.example.wallet_hci.data.model.NetworkPayment
import com.example.wallet_hci.data.model.NetworkPaymentLink
import com.example.wallet_hci.data.model.NetworkPaymentResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Path

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

    suspend fun payWithCard(@Body cardPayment : NetworkCardPayment): Response<Unit>
    {
        return paymentService.payWithCard(cardPayment)
    }

    suspend fun settlePayment(@Body linkPayment: NetworkPaymentLink, @Path("linkUuid") linkUuid: String): Response<NetworkPaymentResponse>
    {
        return paymentService.settlePayment(linkPayment, linkUuid)
    }

    suspend fun getPaymentLink(@Path("linkUuid") linkUuid: String): Response<NetworkPaymentResponse>
    {
        return paymentService.getPaymentLink(linkUuid)
    }

    suspend fun payWithBalance(@Body balancePayment : NetworkBalancePayment): Response<Unit>
    {
        return paymentService.payWithBalance(balancePayment)
    }

    suspend fun generateLink(linkPayment: NetworkNewPaymentLink): NetworkLinkUuid?
    {
        return handleApiResponse {
            paymentService.generateLink(linkPayment)
        }
    }
}