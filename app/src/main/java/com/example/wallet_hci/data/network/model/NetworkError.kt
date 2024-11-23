package com.example.wallet_hci.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkError(
    val message: String
)