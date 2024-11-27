package com.example.wallet_hci.data.network

import com.example.wallet_hci.data.network.api.APIWalletService

import com.example.wallet_hci.data.model.*
class WalletDataSource(private val walletService: APIWalletService): RemoteDataSource()
{
    suspend fun getCards(token: String): List<NetworkCard>? {
        return handleApiResponse { walletService.getCards(token) }
    }

    suspend fun addCard(card: NetworkCard, token: String): NetworkCard? {
        return handleApiResponse { walletService.addCard(card, token) }
    }

    suspend fun deleteCard(cardId: Int, token: String): Unit? {
        return handleApiResponse { walletService.deleteCard(cardId, token) }
    }

    suspend fun getBalance(token: String): NetworkBalance? {
        return handleApiResponse { walletService.getBalance(token) }
    }

    suspend fun recharge(rechargeRequest: NetworkRechargeRequest, token: String): NetworkRechargeResponse? {
        return handleApiResponse { walletService.recharge(rechargeRequest, token) }
    }

    suspend fun getWalletDetails(token: String): NetworkWalletDetails? {
        return handleApiResponse { walletService.getWalletDetails(token) }
    }

    suspend fun updateAlias(alias: NetworkAlias, token: String): Unit? {
        return handleApiResponse { walletService.updateAlias(alias, token) }
    }
}