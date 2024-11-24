package com.example.wallet_hci.data.network.model

import com.example.wallet_hci.data.model.LinkUuid
import kotlinx.serialization.Serializable


@Serializable
class NetworkLinkUuid (
    val linkUuid: String

) {
    fun asModel(): LinkUuid {
        return LinkUuid(
            linkUuid = this.linkUuid
        )

    }
}