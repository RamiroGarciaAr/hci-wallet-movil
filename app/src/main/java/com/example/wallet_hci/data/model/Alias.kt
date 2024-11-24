package com.example.wallet_hci.data.model

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