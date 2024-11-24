package com.example.wallet_hci.data.network.model

import com.example.wallet_hci.data.model.WalletDetails
import kotlinx.serialization.Serializable

@Serializable
class NetworkWalletDetails(
    var id:Int,
    var balance: Float,
    var alias: String,
    var createdAt: String,
    var updatedAt: String,
    var invested: Float,
    var cbu: String,
)
{
    fun asModel(): WalletDetails {
        return WalletDetails(
            id = this.id,
            balance = this.balance,
            alias = this.alias,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt,
            invested = this.invested,
            cbu = this.cbu
        )
    }
}
