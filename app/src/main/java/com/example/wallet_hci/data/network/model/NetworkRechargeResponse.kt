package com.example.wallet_hci.data.network.model

import java.text.SimpleDateFormat
import java.util.Locale
import kotlinx.serialization.Serializable as serializable

@serializable
class NetworkRechargeResponse (
    var newBalance: Float
){
    fun asModel(): RechargeResponse {

        return RechargeResponse(
            newBalance = this.newBalance
        )
    }
}