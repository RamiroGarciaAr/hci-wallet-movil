package com.example.wallet_hci.screens.app.contacts


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreen(
    onBack: () -> Unit = {}, // Acción para volver atrás
    onAdd: (String, String, String) -> Unit = { _, _, _ -> } // Acción para agregar un contacto
) {
    // Campos para los datos del contacto
    val name = remember { mutableStateOf("") }
    val cvuOrAlias = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Agregar Contacto") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
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
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Completa los datos del contacto",
                style = MaterialTheme.typography.titleMedium
            )

            // Campo: Nombre y Apellido
            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Nombre y Apellido") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Campo: CVU o Alias
            OutlinedTextField(
                value = cvuOrAlias.value,
                onValueChange = { cvuOrAlias.value = it },
                label = { Text("CVU o Alias") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Campo: Dirección Email
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Dirección Email") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para agregar el contacto
            Button(
                onClick = { onAdd(name.value, cvuOrAlias.value, email.value) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0066CC))
            ) {
                Text(
                    text = "Agregar Contacto",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

