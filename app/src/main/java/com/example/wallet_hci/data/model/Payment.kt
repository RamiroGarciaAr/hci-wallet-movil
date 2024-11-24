package com.example.wallet_hci.data.model

import java.util.Date

class Payment(
    var id: Int,
    var type: String,
    var amount: Float,
    var balanceBefore: Float,
    var balanceAfter: Float,
    var pending: Boolean,
    var linkUuid: String? = null,
    var createdAt: Date? = null,
    var updatedAt: Date? = null,
    var card: Card? = null
)