package com.example.wallet_hci.screens.app.contacts


data class Contact(
    val name: String,
    val email: String,
    val banks: List<Bank>,
    var isFavorite: Boolean = false,
    val isRecent: Boolean = false
)

data class Bank(
    val name: String,
    val icon: Int // Referencia al recurso drawable del ícono
)
