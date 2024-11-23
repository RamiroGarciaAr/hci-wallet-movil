package com.example.wallet_hci.screens.auth.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class LogInViewModel(
    // private val userRepository:UserRepository
    ): ViewModel() {
    private val _state = MutableStateFlow(LogInState())
    val state = _state.asStateFlow()

    var onLoginSuccess: (() -> Unit)? = null

    lateinit var onLogin: () -> Unit

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> _state.update { it.copy(email = event.email) }
            is LoginEvent.PasswordChanged -> _state.update { it.copy(password = event.password) }
            is LoginEvent.Login -> validateAndLogin()
            else -> throw IllegalArgumentException("Unknown event: $event")
        }
    }

    fun validateAndLogin()
        {
            try{
                // Validamos que los campos no sean vacios
                require(_state.value.email.isNotEmpty()) { "Ingrese un correo electrónico válido." }
                require(_state.value.password.isNotEmpty()) { "Ingrese una contraseña válida." }

                loginUser()
            }
            catch (e: IllegalArgumentException) {
                // Si hay un error en la validación, muestra el mensaje de error
                _state.update { it.copy(error = e.message ?: "Error de validación") }
            }
        }
        private fun loginUser()
        {
           viewModelScope.launch{
            try{
                _state.value = _state.value.copy(isLoading = true)

            }
           }
        }

    }