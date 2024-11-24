package com.example.wallet_hci.data.model

import com.example.wallet_hci.data.network.model.NetworkLinkUuid

class LinkUuid(
    val linkUuid: String
){
    suspend  fun asNetworkModel(): NetworkLinkUuid {
        return NetworkLinkUuid(
            return linkUuid
        )
    }
}