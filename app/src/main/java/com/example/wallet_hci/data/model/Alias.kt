package com.example.wallet_hci.data.model

import com.example.wallet_hci.data.network.model.NetworkAlias

class Alias (
    var alias : String
)
{
    fun asNetworkModel(): NetworkAlias {
        return NetworkAlias(
            alias = alias
        )
    }
}