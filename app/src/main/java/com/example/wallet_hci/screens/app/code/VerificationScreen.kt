package com.example.wallet_hci.screens.app.code

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

import com.example.wallet_hci.data.repository.UserRepositoryProvider
import com.example.wallet_hci.routes.NavigatorProvider
import com.example.wallet_hci.UiStateProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import com.example.wallet_hci.data.model.Code
import com.example.wallet_hci.data.DataSourceException
import com.example.wallet_hci.app.routes.Routes
import com.example.wallet_hci.ui.components.VerificationCodeInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationScreen(
    viewModel: VerificationViewModel = viewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val navigator = NavigatorProvider.current
    val uiState = UiStateProvider.current
    val userRepository = UserRepositoryProvider.current

    val verificationCode = remember { mutableStateOf("") }

    val onVerification = {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val code = verificationCode.value
                if (code.isEmpty() || code.length != 4)
                    throw IllegalArgumentException("El código de verificación debe tener 4 dígitos.")

                userRepository.verify(Code(code))
                navigator.navigateTo(Routes.Home)
            } catch (e: DataSourceException) {
                when (e.code) {
                    400, 401, 404 -> uiState.snackbarHostState.showSnackbar(
                        message = "El código de verificación no es correcto"
                    )
                    else -> uiState.snackbarHostState.showSnackbar(message = "Error al verificar")
                }
            } catch (e: Exception) {
                uiState.snackbarHostState.showSnackbar(message = e.message ?: "Error al verificar")
            }
        }
    }

    val onCancel = {
        navigator.navigateBack()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            // Main content
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
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

                Spacer(modifier = Modifier.height(50.dp))

                OutlinedTextField(
                    value = verificationCode.value,
                    onValueChange = {
                        if (it.length <= 16) verificationCode.value = it
                    },
                    label = { Text("Código de Verificación") },
                    placeholder = { Text("Ingrese el código") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = verificationCode.value.length != 16 && verificationCode.value.isNotEmpty()
                )

                Spacer(modifier = Modifier.height(16.dp))

                state.errorMsg?.let { error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            // Buttons at the bottom
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onCancel,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    modifier = Modifier.weight(1f) // Equal width for symmetry
                ) {
                    Text(text = stringResource(id = R.string.cancel))
                }

                Spacer(modifier = Modifier.width(16.dp)) // Space between buttons

                Button(
                    onClick = {onVerification()},
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary_600)),
                    modifier = Modifier.weight(1f) // Equal width for symmetry
                ) {
                    Text(text = stringResource(id = R.string.continue_action))
                }
            }
        }
    }
}
