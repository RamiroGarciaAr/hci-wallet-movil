package com.example.wallet_hci.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.font.FontWeight

@Preview(showBackground = true)
@Composable
fun PreviewCustomCard() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Tarjeta con estilo Primario
            CustomCard(
                header = { Text("Tarjeta Primaria", fontWeight = FontWeight.Bold) },
                style = CardStyle.Primary,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Este es el contenido de la tarjeta primaria.",
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tarjeta con estilo de Peligro
            CustomCard(
                header = { Text("Tarjeta de Peligro", fontWeight = FontWeight.Bold) },
                style = CardStyle.Danger,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Este es el contenido de la tarjeta de peligro.",
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tarjeta con estilo de Éxito
            CustomCard(
                header = { Text("Tarjeta Exitosa", fontWeight = FontWeight.Bold) },
                style = CardStyle.Success,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Contenido que indica éxito en la operación.",
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tarjeta con estilo por Defecto
            CustomCard(
                header = { Text("Tarjeta Por Defecto", fontWeight = FontWeight.Bold) },
                style = CardStyle.Default,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Esta es una tarjeta con estilo por defecto.",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
