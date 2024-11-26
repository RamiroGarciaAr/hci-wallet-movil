package com.example.wallet_hci.screens.app.contacts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.wallet_hci.R

enum class FilterOption { ALL, FAVORITES, RECENTS }
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(
    onBack: () -> Unit, // Acción para volver a la pantalla anterior
    onAddContact: () -> Unit, // Acción para agregar un nuevo contacto
    onContactSelected: (String) -> Unit // Acción al seleccionar un contacto
) {
    // Obtén el ViewModel
    val viewModel: ContactsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    // Obtén la lista de contactos desde el ViewModel
    val contacts = viewModel.contacts

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Contactos") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF0066CC) // Azul
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onAddContact) {
                        Icon(
                            painter = painterResource(id = R.drawable.plus), // Cambia por el recurso correcto
                            contentDescription = "Agregar contacto",
                            tint = Color(0xFF0066CC) // Azul
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(paddingValues)
        ) {
            // Barra de búsqueda
            var searchQuery by remember { mutableStateOf("") }
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { },
                active = false,
                onActiveChange = { }
            )

            // Lista de contactos
            LazyColumn(modifier = Modifier.padding(8.dp)) {
                items(contacts) { contact ->
                    ContactItem(
                        contact = contact,
                        onFavoriteToggle = { viewModel.toggleFavorite(contact) },
                        onClick = { onContactSelected(contact.name) } // Aquí pasamos el contacto seleccionado
                    )
                }
            }
        }
    }
}

@Composable
fun ContactItem(
    contact: Contact,
    onFavoriteToggle: (Contact) -> Unit,
    onClick: () -> Unit // Acción al hacer clic en el contacto
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .clickable(onClick = onClick) // Añadimos acción clic
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            // Nombre del contacto
            Text(text = contact.name, fontWeight = FontWeight.Bold)
            // Email del contacto
            Text(text = contact.email, color = Color.Gray, fontSize = 12.sp)
            // Iconos de bancos asociados
            Row {
                contact.banks.forEach { bank ->
                    Icon(
                        painter = painterResource(id = bank.icon),
                        contentDescription = bank.name,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(2.dp)
                    )
                }
            }
        }
        // Botón de favorito
        IconButton(onClick = { onFavoriteToggle(contact) }) {
            Icon(
                imageVector = if (contact.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = if (contact.isFavorite) "Desmarcar favorito" else "Marcar como favorito",
                tint = if (contact.isFavorite) Color.Red else Color.Gray
            )
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit
) {
    // Componente de barra de búsqueda
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Buscar un contacto") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        singleLine = true
    )
}



