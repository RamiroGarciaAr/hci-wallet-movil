package com.example.wallet_hci.data

class DataSourceException(
    var code: Int,
    message: String,
) : Exception(message)