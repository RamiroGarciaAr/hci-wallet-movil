package com.example.wallet_hci.data.model

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