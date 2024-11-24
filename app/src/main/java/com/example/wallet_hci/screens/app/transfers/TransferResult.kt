package com.example.wallet_hci.screens.app.transfers

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.colorResource
import com.example.wallet_hci.R
import com.example.wallet_hci.ui.components.CustomCard
import com.example.wallet_hci.ui.components.CardStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferResultScreen(
    onShare: () -> Unit = {},
    onSaveContact: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.transfer)) },
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Back"
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
            CustomCard(
                title = stringResource(id = R.string.transfer_success_title),
                style = CardStyle.Success
            ) {
                Column {
                    Text(
                        text = stringResource(
                            id = R.string.transfer_success_message,
                            "$123.42",
                            "Damián Villablanca",
                            "Banco Galicia"
                        ),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = R.string.transfer_receipt, "#41231"),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            // Detalles de la transferencia
            CustomCard(
                title = stringResource(id = R.string.transfer_details_title),
                style = CardStyle.Default
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = "Tobías Juhasz",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Alias: sol.cielo.arcoiris",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Text(
                        text = "→",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Column {
                        Text(
                            text = "Damián Villablanca",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Alias: sol.cielo.arcoiris",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botones
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = onSaveContact,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text(text = stringResource(id = R.string.save_contact))
                }
                Button(
                    onClick = onShare,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.blue_bar)
                    )
                ) {
                    Text(text = stringResource(id = R.string.share_receipt))
                }
            }
        }
    }
}
