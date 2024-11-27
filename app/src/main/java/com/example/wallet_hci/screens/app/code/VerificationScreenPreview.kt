package com.example.wallet_hci.screens.app.code

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun VerificationScreenPreview() {
    // Mock ViewModel with fake state
    val mockViewModel = object : VerificationViewModel() {
        init {
            // Set some mock data for the preview
            _state.value = VerificationState(
                code = listOf("", "", "", ""), // Example pre-filled code
                isLoading = false, // Not loading for preview
                errorMsg = null // No error for preview
            )
        }
    }

    // Mock `onVerificationSuccess` and `onCancel` lambdas
    VerificationScreen(
        viewModel = mockViewModel,
    )
}