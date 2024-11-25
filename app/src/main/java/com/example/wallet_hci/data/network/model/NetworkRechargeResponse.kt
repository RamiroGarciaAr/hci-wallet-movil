package com.example.wallet_hci.data.model

import com.example.wallet_hci.data.model.RechargeResponse
import kotlinx.serialization.Serializable

@Serializable
class NetworkRechargeResponse (
    var newBalance: Float
){
    fun asModel(): RechargeResponse {

        return RechargeResponse(
            newBalance = this.newBalance
        )
    }
}