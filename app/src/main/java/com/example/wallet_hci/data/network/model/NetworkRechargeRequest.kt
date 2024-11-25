package com.example.wallet_hci.data.model

import com.example.wallet_hci.data.model.RechargeRequest
import kotlinx.serialization.Serializable

@Serializable
class NetworkRechargeRequest(var newBalance: Float) {
    fun asModel(): RechargeRequest {
        return RechargeRequest(newBalance = this.newBalance)
    }
}