package com.example.wallet_hci.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun Activity() {
    Column {
        Text("Activity")
    }




    @Composable
    fun MoneyAvailableSection() {
        Column(
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el ancho
                .background(Color(0xFF0066CC)) // Color azul de fondo
                .padding(16.dp) // Espaciado interno
        ) {
            Text(
                text = "Dinero disponible", // Título
                color = Color.White, // Texto blanco
                fontSize = 20.sp // Tamaño de texto
            )
            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre elementos
            Row {
                Text(
                    text = "$ 1,658.85", // Dinero disponible
                    color = Color.White,
                    fontSize = 36.sp, // Texto grande
                    fontWeight = FontWeight.Bold // Texto en negrita
                )
            }
        }
    }
    @Composable
    fun MovementItem(date: String, description: String, amount: String, isIncome: Boolean) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (isIncome) Color.Transparent else Color.White)// Ocupa todo el ancho
                .padding(vertical = 8.dp) // Espacio entre elementos
        ) {
            Text(
                text = date, // Fecha
                modifier = Modifier.weight(1f), // Ocupa 1 unidad de espacio
                fontSize = 14.sp
            )
            Text(
                text = description, // Descripción
                modifier = Modifier.weight(3f), // Ocupa 3 unidades de espacio
                fontSize = 14.sp
            )
            Text(
                text = amount, // Monto
                modifier = Modifier.weight(1f), // Ocupa 1 unidad
                fontSize = 14.sp,
                color = if (isIncome) Color.Green else Color.Red // Verde si es ingreso, rojo si es gasto
            )
        }
    }


    @Composable
    fun MovementList() {
        Column(modifier = Modifier.padding(16.dp)) { // Un contenedor vertical con margen
            Text(
                text = "Movimientos de tu dinero",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp)) // Espacio
            repeat(5) { // Crear 5 movimientos de ejemplo
                MovementItem(date = "22/09", description = "Supermercado - Los Andes", amount = "-$100", isIncome = false)
                MovementItem(date = "22/09", description = "Transferencia recibida", amount = "+$1000", isIncome = true)
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa toda la pantalla
            .background(Color(0xFFEEEEEE)) // Color de fondo
    ) {
        MoneyAvailableSection() // Dinero disponible
        MovementList() // Lista de movimientos

    }

    
}
