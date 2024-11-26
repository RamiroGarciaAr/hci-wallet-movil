package com.example.wallet_hci.screens.app.contacts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class FilterOption { ALL, FAVORITES, RECENTS }

@Composable
fun ContactScreen() {
    // Obtén el ViewModel
    val viewModel: ContactsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    // Obtén la lista de contactos desde el ViewModel
    val contacts = viewModel.contacts

    // Diseña la pantalla como antes, pero usa los datos del ViewModel
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF5F5F5))
    ) {
        // Barra de búsqueda
        var searchQuery by remember { mutableStateOf("") }
        SearchBar(query = searchQuery, onQueryChange = { searchQuery = it })

        // Lista de contactos (filtros opcionales)
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            items(contacts) { contact ->
                ContactItem(
                    contact = contact,
                    onFavoriteToggle = { viewModel.toggleFavorite(contact) }
                )
            }
        }
    }
}


@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text(stringResource.(id=R.string.find_contact)) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = stringResource.(id=R.string.find_contact)) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
fun ContactItem(contact: Contact, onFavoriteToggle: (Contact) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = contact.name, fontWeight = FontWeight.Bold)
            Text(text = contact.email, color = Color.Gray, fontSize = 12.sp)
            Row {
                contact.banks.forEach { bank ->
                    Icon(
                        painter = painterResource(id = bank.icon),
                        contentDescription = bank.name,
                        modifier = Modifier.size(24.dp).padding(2.dp)
                    )
                }
            }
        }
        IconButton(onClick = { onFavoriteToggle(contact) }) {
            Icon(
                imageVector = if (contact.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = if (contact.isFavorite) stringResource.(id=R.string.unmark_favorite) else stringResource.(id=R.string.mark_favorite),
                tint = if (contact.isFavorite) Color.Red else Color.Gray
            )
        }
    }
}

@Composable
fun FiltersRow(selectedFilter: FilterOption, onFilterSelected: (FilterOption) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        FilterButton(stringResource.(id=R.string.all_contacts), isSelected = selectedFilter == FilterOption.ALL) {
            onFilterSelected(FilterOption.ALL)
        }
        FilterButton(stringResource.(id=R.string.favorites), isSelected = selectedFilter == FilterOption.FAVORITES) {
            onFilterSelected(FilterOption.FAVORITES)
        }
        FilterButton(stringResource.(id=R.string.recent), isSelected = selectedFilter == FilterOption.RECENTS) {
            onFilterSelected(FilterOption.RECENTS)
        }
    }
}

@Composable
fun FilterButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF0066CC) else Color.White,
            contentColor = if (isSelected) Color.White else Color.Black
        ),
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Text(text = text)
    }
}

fun filterContacts(
    contacts: List<Contact>,
    query: String,
    filter: FilterOption
): List<Contact> {
    return contacts.filter { contact ->
        // Filtrar por nombre o email
        contact.name.contains(query, ignoreCase = true) ||
                contact.email.contains(query, ignoreCase = true)
    }.filter { contact ->
        // Aplicar filtro adicional
        when (filter) {
            FilterOption.ALL -> true
            FilterOption.FAVORITES -> contact.isFavorite
            FilterOption.RECENTS -> contact.isRecent
        }
    }
}

