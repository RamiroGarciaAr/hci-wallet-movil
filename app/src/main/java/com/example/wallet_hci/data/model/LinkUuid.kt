package com.example.wallet_hci.data.model

import com.example.wallet_hci.data.network.model.NetworkLinkUuid

class LinkUuid(
    val linkUuid: String
){
      fun asNetworkModel(): NetworkLinkUuid {
        return NetworkLinkUuid(
            linkUuid = this.linkUuid
        )
    }
}