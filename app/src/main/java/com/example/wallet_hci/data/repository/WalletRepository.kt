package com.example.wallet_hci.data.repository

import com.example.wallet_hci.data.model.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.wallet_hci.data.network.WalletDataSource

val WalletRepositoryProvider = staticCompositionLocalOf<WalletRepository> { error("WalletRepository not provided") }

@Singleton
class WalletRepository @Inject constructor(
    private val remoteDataSource: WalletDataSource
) {

    suspend fun getCards(token: String): List<NetworkCard>? {
        return remoteDataSource.getCards(token)
    }
    suspend fun addCard(card: NetworkCard, token: String): NetworkCard? { 
        return remoteDataSource.addCard(card, token)
    }

    suspend fun deleteCard(cardId: Int, token: String): Unit? {
        return remoteDataSource.deleteCard(cardId, token)
    }

    suspend fun getBalance(token: String): NetworkBalance? {
        return remoteDataSource.getBalance(token)
    }

    suspend fun recharge(rechargeRequest: NetworkRechargeRequest, token: String): NetworkRechargeResponse? {
        return remoteDataSource.recharge(rechargeRequest, token)
    }

    suspend fun getWalletDetails(token: String): NetworkWalletDetails? {
        return remoteDataSource.getWalletDetails(token)
    }

    suspend fun updateAlias(alias: NetworkAlias, token: String): Unit? {
        return remoteDataSource.updateAlias(alias, token)
    }
}