package com.example.wallet_hci.screens.app.code

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wallet_hci.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationScreen(
    viewModel: VerificationViewModel = viewModel(),
    onVerificationSuccess: () -> Unit,
    onCancel: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // App Logo
                Image(
                    painter = painterResource(id = R.drawable.app_logo), // Replace with your drawable resource
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Title
                Text(
                    text = stringResource(id=R.string.welcome_title),
                    style = MaterialTheme.typography.titleLarge
                )



                // Subtitle
                Text(
                    text = stringResource(id=R.string.mail_sent),
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Verification Code Fields
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    state.code.forEachIndexed { index, codeValue ->
                        TextField(
                            value = codeValue,
                            onValueChange = { newValue ->
                                if (newValue.length <= 1) { // Solo permitir un carácter
                                    viewModel.onCodeChanged(index, newValue)
                                }
                            },
                            modifier = Modifier.size(60.dp),
                            colors = OutlinedTextFieldDefaults.colors( unfocusedContainerColor = Color.White),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true

                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Error message
                state.errorMsg?.let { error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onCancel,
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = stringResource(id=R.string.cancel))
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = { viewModel.onEvent(VerificationEvent.Verify) },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.blue_bar)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text  = stringResource(id=R.string.continue_action))
                    }
                }
            }
        }
    }
}
