package com.example.wallet_hci.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme

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
                title = "Tarjeta Primaria",
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
                title = "Tarjeta de Peligro",
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
                title = "Tarjeta Exitosa",
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
                title = "Tarjeta Por Defecto",
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

