package com.example.wallet_hci.data.model

import com.example.wallet_hci.data.network.model.NetworkRechargeRequest

class RechargeRequest(var newBalance: Float) {
    fun asNetworkModel(): NetworkRechargeRequest {
        return NetworkRechargeRequest(newBalance = this.newBalance)
    }
}
