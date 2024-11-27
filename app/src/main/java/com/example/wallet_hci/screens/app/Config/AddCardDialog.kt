package com.example.wallet_hci.screens.app.Config

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.wallet_hci.R

@Composable
fun AddCardDialog(
    onDismiss: () -> Unit,
    onSave: (String, String, String, String) -> Unit
) {
    var cardNumber by remember { mutableStateOf("") }
    var cardHolder by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Add New Card") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = cardNumber,
                    onValueChange = { if (it.length <= 16) cardNumber = it },
                    label = { Text("Card Number") },
                    placeholder = { Text("1234 5678 9012 3456") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = cardHolder,
                    onValueChange = { cardHolder = it },
                    label = { Text("Card Holder Name") },
                    placeholder = { Text("John Doe") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = expiryDate,
                        onValueChange = { if (it.length <= 5) expiryDate = it },
                        label = { Text("Expiry Date") },
                        placeholder = { Text("MM/YY") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f)
                    )

                    OutlinedTextField(
                        value = cvv,
                        onValueChange = { if (it.length <= 3) cvv = it },
                        label = { Text("CVV") },
                        placeholder = { Text("***") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        visualTransformation = VisualTransformation.None,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (cardNumber.isNotEmpty() && cardHolder.isNotEmpty() && expiryDate.isNotEmpty() && cvv.isNotEmpty()) {
                        onSave(cardNumber, cardHolder, expiryDate, cvv)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary_500))
            ) {
                Text("Save", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(Color.White)) {
                Text("Cancel",color = colorResource(id = R.color.primary_500))

            }
        }
    )
}
