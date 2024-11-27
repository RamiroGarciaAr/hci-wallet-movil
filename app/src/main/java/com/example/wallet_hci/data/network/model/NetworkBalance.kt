package com.example.wallet_hci.data.model

import kotlinx.serialization.Serializable
import com.example.wallet_hci.data.model.Balance

@Serializable
class NetworkBalance (
    var balance: Float
)
{
    fun asModel(): Balance {
        return Balance(
            balance = this.balance
        )
    }
}