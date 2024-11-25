package com.example.wallet_hci.data.model

import com.example.wallet_hci.data.model.Card
import com.example.wallet_hci.data.model.CardType
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
class NetworkCard(
    var id: Int?,
    var number: String,
    var expirationDate: String,
    var fullName: String,
    var cvv: String? = null,
    var type: String,
    var createdAt: String?,
    var updatedAt: String?
) {
    fun asModel(): Card {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))
        return Card(
            id = this.id,
            number = this.number,
            expirationDate = this.expirationDate,
            fullName = this.fullName,
            cvv = this.cvv,
            type = when (this.type) { "DEBIT" -> CardType.DEBIT else -> CardType.CREDIT },
            createdAt = this.createdAt?.let { dateFormat.parse(this.createdAt!!) },
            updatedAt = this.updatedAt?.let { dateFormat.parse(this.updatedAt!!) }
        )
    }
}