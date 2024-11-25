package com.example.wallet_hci.screens.app.registration

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wallet_hci.R

@Composable
fun RegistrationAdditionalInfo(onCancel: () -> Unit, onRegisterComplete: () -> Unit) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Título de bienvenida
            Text(
                text = stringResource(id = R.string.welcome_title),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.blue_bar)
            )

            Text(
                text = stringResource(id = R.string.welcome_subtitle),
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de texto
            OutlinedTextField(
                value = "",
                onValueChange = { /* Nombre */ },
                label = { Text(stringResource(id = R.string.name_label)) },
                placeholder = { Text(stringResource(id = R.string.name_placeholder)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = "",
                onValueChange = { /* Apellido */ },
                label = { Text(stringResource(id = R.string.last_name_label)) },
                placeholder = { Text(stringResource(id = R.string.last_name_placeholder)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = "",
                onValueChange = { /* Nacionalidad */ },
                label = { Text(stringResource(id = R.string.nationality_label)) },
                placeholder = { Text(stringResource(id = R.string.nationality_placeholder)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // para términos y condiciones
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = false,
                    onCheckedChange = { /* Manejar cambios */ }
                )
                Text(
                    text = stringResource(id = R.string.terms_conditions_text),
                    modifier = Modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botones Cancelar y Registrarse
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onCancel,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text(text = stringResource(id = R.string.cancel_button))
                }

                Button(
                    onClick = onRegisterComplete,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.blue_bar)
                    )
                ) {
                    Text(text = stringResource(id = R.string.register_button))
                }
            }
        }
    }
}
