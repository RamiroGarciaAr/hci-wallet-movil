package com.example.wallet_hci.screens.app.passwordRecovery.code

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.resolveDefaults
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.wallet_hci.ui.components.VerificationCodeInput
import androidx.compose.ui.unit.sp
import com.example.wallet_hci.R
import kotlinx.coroutines.delay

@Composable
fun PasswordResetScreen(
    emailMethod: String,
    onCodeEntered: (List<String>) -> Unit,
    onBack: () -> Unit,
    onResend: () -> Unit
) {
    val initialCode = remember { List(4) { "" } } // 4-digit verification code
    val (code, setCode) = remember { mutableStateOf(initialCode) }
    val focusRequesters = List(code.size) { FocusRequester() }

    // Countdown timer state
    var remainingTime by remember { mutableStateOf(30) } // Start countdown at 30 seconds
    var isTimerActive by remember { mutableStateOf(true) }

    // Launch the countdown effect
    LaunchedEffect(isTimerActive) {
        if (isTimerActive) {
            while (remainingTime > 0) {
                delay(1000L) // Wait for 1 second
                remainingTime--
            }
            isTimerActive = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp) // Reserve space for the bottom buttons
        ) {
            // Title
            Text(
                text = "Restablecer contraseña",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = 35.sp,
                color = colorResource(R.color.primary_600),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, top = 32.dp)
            )
            HorizontalDivider(
                thickness = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            // Subtitle
            Text(
                text = "Te hemos enviado un mensaje al mail $emailMethod",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                color = colorResource(R.color.primary_600),
                textAlign = TextAlign.Start,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Verification Code Input
            VerificationCodeInput(
                code = code,
                onCodeChanged = { index, value ->
                    setCode(code.toMutableList().apply { this[index] = value })
                    if (code.all { it.isNotEmpty() }) {
                        onCodeEntered(code)
                    }
                },
                focusRequesters = focusRequesters,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Resend Button with Countdown
            if (isTimerActive) {
                Text(
                    text = "¿No te llegó el código? Reenviar en ${remainingTime}s",
                    style = MaterialTheme.typography.bodySmall.copy(color = colorResource(R.color.primary_600)),
                    textAlign = TextAlign.Center
                )
            } else {
                Button(
                    onClick = {
                        remainingTime = 30 // Reset timer
                        isTimerActive = true
                        onResend() // Trigger resend action
                    },
                    modifier = Modifier.wrapContentWidth(),
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.primary_600))
                ) {
                    Text(
                        text = "Reenviar código",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }
        }

        // Bottom Buttons
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter) // Place at the bottom of the screen
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(colorResource(R.color.primary_600)),
                modifier = Modifier.weight(1f) // Equal weight for symmetry
            ) {
                Text(
                    text = stringResource(id = R.string.back),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { if (code.all { it.isNotEmpty() }) onCodeEntered(code) },
                colors = ButtonDefaults.buttonColors(colorResource(R.color.primary_600)),
                modifier = Modifier.weight(1f) // Equal weight for symmetry
            ) {
                Text(
                    text = stringResource(id = R.string.continue_action),
                    color = Color.White
                )
            }
        }
    }
}
