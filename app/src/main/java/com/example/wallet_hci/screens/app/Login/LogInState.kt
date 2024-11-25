package com.example.wallet_hci.screens.app.Login


data class LogInState(
    val email: String = "",
    val password: String = "",
    val errorMsg: String? = null,
    val isLoading: Boolean = false

)

sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    object Login : LoginEvent()
}