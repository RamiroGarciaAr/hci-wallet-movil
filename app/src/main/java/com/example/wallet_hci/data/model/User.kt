package com.example.wallet_hci.data.model

import android.security.NetworkSecurityPolicy
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class User(
    var id: Int?,
    var firstName: String,
    var lastName: String,
    var email: String,
    var birthDate: Date
)
{
    fun asNetworkModel():NetworkUser
    {

    }
}
