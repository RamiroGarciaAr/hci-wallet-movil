package com.example.wallet_hci.data.network.model

import com.example.wallet_hci.data.model.User
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
class NetworkUser(
    val id: Int?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: String
) {
    fun asModel(): User {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))

        return User(
            id = id,
            firstName = firstName,
            lastName = lastName,
            email = email,
            birthDate = dateFormat.parse(birthDate)!!
        )
    }
}