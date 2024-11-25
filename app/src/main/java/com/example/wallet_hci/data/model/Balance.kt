package com.example.wallet_hci.data.model

import com.example.wallet_hci.data.model.NetworkBalance

class Balance (
    var balance: Float
){
    fun asNetworkModel(): NetworkBalance {
        return NetworkBalance(
            balance = balance
        )
    }
}