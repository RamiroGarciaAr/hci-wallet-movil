package com.example.wallet_hci.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkErrorMsg(
    val message: String
)