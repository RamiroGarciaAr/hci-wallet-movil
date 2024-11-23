package com.example.wallet_hci.data.network

import com.example.wallet_hci.SessionManager
import com.example.wallet_hci.data.network.api.UserApiService
import com.example.wallet_hci.data.network.model.NetworkCode
import com.example.wallet_hci.data.network.model.NetworkCredentials
import com.example.wallet_hci.data.network.model.NetworkRegistrationUser
import com.example.wallet_hci.data.network.model.NetworkUser

class UserRemoteDataSource(
    private val sessionManager: SessionManager,
    private val userApiService: UserApiService
) {

    /**
     * Logs in the user by sending credentials to the API.
     * On success, saves the authentication token in the session manager.
     */
    suspend fun login(username: String, password: String) {
        val token = userApiService.login(NetworkCredentials(username, password))
        sessionManager.saveAuthToken(token.token)
    }

    /**
     * Logs out the user by calling the logout endpoint and clearing the session.
     */
    suspend fun logout() {
        userApiService.logout()
        sessionManager.removeAuthToken()
    }

    /**
     * Fetches the current user's details from the API.
     * @return A [NetworkUser] object.
     */
    suspend fun getCurrentUser(): NetworkUser {
        return userApiService.getCurrentUser()
    }

    /**
     * Registers a new user by sending their information to the API.
     * @param user The [NetworkRegistrationUser] object containing registration details.
     * @return A [NetworkUser] object for the newly registered user.
     */
    suspend fun register(user: NetworkRegistrationUser): NetworkUser {
        return userApiService.register(user)
    }

    /**
     * Verifies a user's account using a verification code.
     * @param code The [NetworkCode] containing the verification token.
     * @return A [NetworkUser] object for the verified user.
     */
    suspend fun verify(code: NetworkCode): NetworkUser {
        return userApiService.verify(code)
    }
}