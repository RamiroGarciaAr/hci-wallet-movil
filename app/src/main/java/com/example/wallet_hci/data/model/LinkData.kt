package com.example.wallet_hci.data.model

class LinkData(
    val id: Int? = null,
    val type: LinkType,
    val amount: Float,
    val balanceBefore: Float,
    val balanceAfter: Float,
    val pending: Boolean,
    val linkUuid: String?,
    val createdAt: String,
    val updatedAt: String,
    val card: Card?
)