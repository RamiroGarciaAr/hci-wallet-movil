package com.example.wallet_hci.data.network.model

import com.example.wallet_hci.data.model.RechargeRequest
import kotlinx.serialization.Serializable as serializable

@serializable
class NetworkRechargeRequest(var newBalance: Float) {
    fun asModel(): RechargeRequest {
        return RechargeRequest(newBalance = this.newBalance)
    }
}