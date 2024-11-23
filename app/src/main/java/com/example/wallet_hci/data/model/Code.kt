package com.example.wallet_hci.data.model

import com.example.wallet_hci.data.network.model.NetworkCode

class Code (
    var code: String
){
    fun asNetworkModel(): NetworkCode {
        return NetworkCode(
            code = code
        )
    }
}