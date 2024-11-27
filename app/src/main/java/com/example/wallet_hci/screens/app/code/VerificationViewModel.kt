package com.example.wallet_hci.screens.app.code

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class VerificationViewModel : ViewModel() {

    val _state = MutableStateFlow(VerificationState())
    val state = _state.asStateFlow()

    fun onCodeChanged(index: Int, value: String) {
        _state.update { currentState ->
            val newCode = currentState.code.toMutableList().apply {
                this[index] = value
            }
            currentState.copy(code = newCode)
        }
    }

    fun onEvent(event: VerificationEvent) {
        when (event) {
            VerificationEvent.Verify -> verifyCode()
        }
    }

    private fun verifyCode() {
        val currentState = _state.value
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true, errorMsg = null) }


                if (currentState.code.joinToString("").isEmpty()) {
                    throw IllegalArgumentException("El código de verificación no puede estar vacío.")
                }                

                // Simulate success
                _state.update { it.copy(isLoading = false) }

            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, errorMsg = e.message) }
            }
        }
    }
}
