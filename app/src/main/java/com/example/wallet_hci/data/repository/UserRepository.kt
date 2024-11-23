package com.example.wallet_hci.data.repository

import com.example.wallet_hci.data.model.Code
import com.example.wallet_hci.data.model.RegistrationUser
import com.example.wallet_hci.data.model.User
import com.example.wallet_hci.data.network.UserRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class UserRepository(
    private val remoteDataSource: UserRemoteDataSource
) {

    // Mutex to make writes to cached values thread-safe.
    private val currentUserMutex = Mutex()
    // Cache of the current user fetched from the network.
    private var currentUser: User? = null

    /**
     * Logs in the user by delegating to the remote data source.
     * @param username The user's username.
     * @param password The user's password.
     */
    suspend fun login(username: String, password: String) {
        remoteDataSource.login(username, password)
    }

    /**
     * Logs out the user by delegating to the remote data source.
     */
    suspend fun logout() {
        remoteDataSource.logout()
    }

    /**
     * Retrieves the current user. Fetches from the remote source if refresh is requested or no user is cached.
     * @param refresh Whether to force refresh from the remote data source.
     * @return The current user, or null if not logged in.
     */
    suspend fun getCurrentUser(refresh: Boolean): User? {
        if (refresh || currentUser == null) {
            val networkUser = remoteDataSource.getCurrentUser()
            // Safely update the current user cache.
            currentUserMutex.withLock {
                this.currentUser = networkUser.asModel()
            }
        }
        return currentUserMutex.withLock { this.currentUser }
    }

    /**
     * Registers a new user and returns the created user.
     * @param user The registration details.
     * @return The registered user.
     */
    suspend fun register(user: RegistrationUser): User {
        val networkUser = remoteDataSource.register(user.asNetworkModel())
        return networkUser.asModel()
    }

    /**
     * Verifies a user's account using a verification code.
     * @param code The verification code.
     * @return The verified user.
     */
    suspend fun verify(code: Code): User {
        val networkUser = remoteDataSource.verify(code.asNetworkModel())
        return networkUser.asModel()
    }
}