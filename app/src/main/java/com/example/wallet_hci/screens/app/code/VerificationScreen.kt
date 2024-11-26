package com.example.wallet_hci.screens.app.code

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wallet_hci.R
import com.example.wallet_hci.ui.components.VerificationCodeInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationScreen(
    viewModel: VerificationViewModel = viewModel(),
    onVerificationSuccess: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusRequesters = List(state.code.size) { FocusRequester() }

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
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(bottom = 16.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(id = R.string.welcome_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 35.sp,
                    color = colorResource(R.color.primary_600),
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                Text(
                    text = stringResource(id = R.string.mail_sent),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    color = colorResource(R.color.primary_600),
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(150.dp))

                Text(
                    text = stringResource(id = R.string.verification_code),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.primary_600),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                VerificationCodeInput(
                    code = state.code,
                    onCodeChanged = { index, newValue -> viewModel.onCodeChanged(index, newValue) },
                    modifier = Modifier.fillMaxWidth(),
                    focusRequesters = focusRequesters
                )

                Spacer(modifier = Modifier.height(16.dp))

                state.errorMsg?.let { error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onCancel,
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = stringResource(id = R.string.cancel))
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = { viewModel.onEvent(VerificationEvent.Verify) },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary_500)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = stringResource(id = R.string.continue_action))
                    }
                }
            }
        }
    }
}
