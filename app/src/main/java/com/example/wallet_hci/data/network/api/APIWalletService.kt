package com.example.wallet_hci.data.network.api

import com.example.wallet_hci.data.model.NetworkAlias
import com.example.wallet_hci.data.model.NetworkBalance
import com.example.wallet_hci.data.model.NetworkCard
import com.example.wallet_hci.data.model.NetworkRechargeRequest
import com.example.wallet_hci.data.model.NetworkRechargeResponse
import com.example.wallet_hci.data.model.NetworkWalletDetails
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APIWalletService {

    // Fetch all cards associated with the wallet
    @GET("wallet/cards")
    suspend fun getCards(): Response<List<NetworkCard>>

    // Add a new card to the wallet
    @POST("wallet/cards")
    suspend fun addCard(@Body card: NetworkCard): Response<NetworkCard>

    // Delete a card from the wallet by ID
    @DELETE("wallet/cards/{cardId}")
    suspend fun deleteCard(@Path("cardId") cardId: Int): Response<Unit>

    // Fetch the wallet balance
    @GET("wallet/balance")
    suspend fun getBalance(): Response<NetworkBalance>

    // Recharge the wallet
    @POST("wallet/recharge")
    suspend fun recharge(@Body rechargeRequest: NetworkRechargeRequest): Response<NetworkRechargeResponse>

    // Get wallet details
    @GET("wallet/details")
    suspend fun getWalletDetails(): Response<NetworkWalletDetails>

    // Update wallet alias
    @PUT("wallet/update-alias")
    suspend fun updateAlias(@Body alias: NetworkAlias): Response<Unit>
}
