package com.example.wallet_hci.screens.app.contacts

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.wallet_hci.R



class ContactsViewModel : ViewModel() {

    // Lista de contactos como estado mutable
    private val _contacts = mutableStateListOf<Contact>(
        Contact(
            name = "Camila López",
            email = "camila.lopez07@example.com",
            banks = listOf(Bank(name = "BBVA", icon = R.drawable.ic_santander)),
            isFavorite = false
        ),
        Contact(
            name = "Emilio Vargas",
            email = "emilio.vargas123@example.com",
            banks = listOf(Bank(name = "Santander", icon = R.drawable.ic_santander)),
            isFavorite = true
        )
    )
    val contacts: SnapshotStateList<Contact> get() = _contacts

    // Alternar favorito
    fun toggleFavorite(contact: Contact) {
        val index = _contacts.indexOf(contact)
        if (index != -1) {
            _contacts[index] = _contacts[index].copy(isFavorite = !_contacts[index].isFavorite)
        }
    }

    // Agregar nuevo contacto
    fun addContact(contact: Contact) {
        _contacts.add(contact)
    }
}
