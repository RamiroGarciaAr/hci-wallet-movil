package com.example.wallet_hci.data.network

import com.example.wallet_hci.data.api.APIPaymentService
import com.example.wallet_hci.data.model.NetworkBalancePayment
import com.example.wallet_hci.data.model.NetworkCardPayment
import com.example.wallet_hci.data.model.NetworkLinkPaymentObject
import com.example.wallet_hci.data.model.NetworkLinkUuid
import com.example.wallet_hci.data.model.NetworkNewPaymentLink
import com.example.wallet_hci.data.model.NetworkPayment
import com.example.wallet_hci.data.model.NetworkPaymentLink
import com.example.wallet_hci.data.model.NetworkPaymentResponse
import retrofit2.http.Body
import retrofit2.http.Path

class WalletDataSource(private val paymentService: APIPaymentService): RemoteDataSource()
{
    //Getters
    suspend fun getPayments(token: String): List<NetworkPayment>? {
        return handleApiResponse { paymentService.getPayments(token)}
    }
    suspend fun getPaymentData(linkUuid: String, token: String): NetworkLinkPaymentObject? {
        return handleApiResponse { paymentService.getPaymentData(linkUuid, token) }
    }

    suspend fun payWithCard(@Body cardPayment : NetworkCardPayment, token: String): Unit? {
        return handleApiResponse { paymentService.payWithCard(cardPayment, token) }
    }

    suspend fun settlePayment(@Body linkPayment: NetworkPaymentLink, @Path("linkUuid") linkUuid: String, token: String): NetworkPaymentResponse?
    {
        return handleApiResponse { paymentService.settlePayment(linkPayment, linkUuid, token) }
    }

    suspend fun getPaymentLink(@Path("linkUuid") linkUuid: String, token: String): NetworkPaymentResponse?
    {
        return handleApiResponse { paymentService.getPaymentLink(linkUuid, token) }
    }

    suspend fun payWithBalance(@Body balancePayment : NetworkBalancePayment, token: String): Unit?
    {
        return handleApiResponse { paymentService.payWithBalance(balancePayment, token) }
    }

    suspend fun generateLink(linkPayment: NetworkNewPaymentLink, token: String): NetworkLinkUuid?
    {
        return handleApiResponse { paymentService.generateLink(linkPayment, token) }
    }
}