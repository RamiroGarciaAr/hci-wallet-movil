 package com.example.wallet_hci.screens.app.Login

 import com.example.wallet_hci.MainActivity
 import androidx.lifecycle.ViewModel
 import androidx.lifecycle.ViewModelProvider
 import androidx.lifecycle.viewModelScope
 import com.example.wallet_hci.data.repository.UserRepository
 import kotlinx.coroutines.flow.MutableStateFlow
 import kotlinx.coroutines.flow.asStateFlow
 import kotlinx.coroutines.flow.update
 import kotlinx.coroutines.launch


 class LoginView(
     private val userRepository: UserRepository
 ) : ViewModel() {

     private val _state = MutableStateFlow(LogInState())
     val state = _state.asStateFlow()

     private var onLoginSuccess: (() -> Unit)? = null // Callback to navigate to the Dashboard

     /**
      * Handles user events such as email/password updates and login actions.
      */
     fun onEvent(event: LoginEvent) {
         when (event) {
             is LoginEvent.EmailChanged -> updateEmail(event.email)
             is LoginEvent.PasswordChanged -> updatePassword(event.password)
             LoginEvent.Login -> validateAndLogin()
         }
     }

     /**
      * Updates the email field in the state.
      */
     private fun updateEmail(email: String) {
         _state.update { it.copy(email = email) }
     }

     /**
      * Updates the password field in the state.
      */
     private fun updatePassword(password: String) {
         _state.update { it.copy(password = password) }
     }

     /**
      * Validates the input fields and initiates the login process.
      */
     private fun validateAndLogin() {
         val currentState = _state.value
         try {
             // Validate email and password
             require(currentState.email.isNotEmpty()) { "Please enter a valid email." }
             require(currentState.password.isNotEmpty()) { "Please enter a valid password." }

             // Proceed to login
             loginUser()

         } catch (e: IllegalArgumentException) {
             // Update state with the validation error message
             _state.update { it.copy(errorMsg = e.message ?: "Validation error") }
         }
     }

     /**
      * Executes the login process using the UserRepository.
      */
     private fun loginUser() {
         viewModelScope.launch {
             try {
                 _state.update { it.copy(isLoading = true) } // Set loading state

                 userRepository.login(_state.value.email, _state.value.password)

                 // Clear loading state and error message
                 _state.update { it.copy(isLoading = false) }

                 // Trigger the success callback
                 onLoginSuccess?.invoke()

             } catch (e: Exception) {
                 // Update state with error message
                 _state.update { it.copy(isLoading = false, errorMsg = e.message ?: "An error occurred during login") }
             }
         }
     }

     companion object {
         const val TAG = "LoginView"


         fun provideFactory(
             userRepository: UserRepository
         ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
             @Suppress("UNCHECKED_CAST")
             override fun <T : ViewModel> create(modelClass: Class<T>): T {
                 if (modelClass.isAssignableFrom(LoginView::class.java)) {
                     return LoginView(userRepository) as T
                 }
                 throw IllegalArgumentException("Unknown ViewModel class")
             }
         }
     }
 }
