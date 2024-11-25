package com.example.wallet_hci.data.api

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
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Header

interface APIPaymentService {
    @POST("payment")
    suspend fun generateLink(@Body linkPayment: NetworkNewPaymentLink, @Header("Authorization") token: String): Response<NetworkLinkUuid>

    @GET("payment")
    suspend fun getPayments(@Header("Authorization") token: String): Response<List<NetworkPayment>>

    @POST("payment")
    suspend fun payWithCard(@Body cardPayment : NetworkCardPayment, @Header("Authorization") token: String): Response<Unit>

    @POST("payment/link/{linkUuid}")
    suspend fun settlePayment(@Body linkPayment: NetworkPaymentLink, @Path("linkUuid") linkUuid: String, @Header("Authorization") token: String): Response<NetworkPaymentResponse>

    @GET("payment/link/{linkUuid}")
    suspend fun getPaymentLink(@Path("linkUuid") linkUuid: String, @Header("Authorization") token: String): Response<NetworkPaymentResponse>

    @POST("payment")
    suspend fun payWithBalance(@Body balancePayment : NetworkBalancePayment, @Header("Authorization") token: String): Response<Unit>

    @GET("payment/link/{linkUuid}")
    suspend fun getPaymentData(@Path("linkUuid") linkUuid: String, @Header("Authorization") token: String): Response<NetworkLinkPaymentObject>
}