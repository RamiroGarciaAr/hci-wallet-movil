package com.example.wallet_hci.data.model

import com.example.wallet_hci.data.model.User
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Serializable
class NetworkUser(
    val id: Int?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: String
) {
    /**
     * Converts a NetworkUser object into a User object.
     * Handles date parsing for birthDate.
     * @return User object
     * @throws IllegalArgumentException if the birthDate cannot be parsed
     */
    fun asModel(): User {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Safely parse the birthDate string into a Date object
        val parsedBirthDate: Date = try {
            dateFormat.parse(birthDate) ?: throw IllegalArgumentException("Invalid date format")
        } catch (e: Exception) {
            throw IllegalArgumentException("Error parsing birthDate: $birthDate", e)
        }

        return User(
            id = id,
            firstName = firstName,
            lastName = lastName,
            email = email,
            birthDate = parsedBirthDate
        )
    }
}
