package com.example.wallet_hci.screens.app.code

data class VerificationState(
    val code: List<String> = List(4) { "" },
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)


sealed class VerificationEvent {
    object Verify : VerificationEvent()
}
