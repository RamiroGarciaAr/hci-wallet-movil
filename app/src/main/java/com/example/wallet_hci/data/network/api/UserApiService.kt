package com.example.wallet_hci.data.netowrk.api

import com.example.wallet_hci.data.model.NetworkCode
import com.example.wallet_hci.data.model.NetworkCredentials
import com.example.wallet_hci.data.model.NetworkRegistrationUser
import com.example.wallet_hci.data.model.NetworkToken
import com.example.wallet_hci.data.model.NetworkUser
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {

    /**
     * Logs in the user with their credentials.
     * @param credentials The user's login credentials.
     * @return A [NetworkToken] containing the authentication token.
     */
    @POST("user/login")
    suspend fun login(@Body credentials: NetworkCredentials): NetworkToken

    /**
     * Logs out the current user.
     */
    @POST("user/logout")
    suspend fun logout()

    /**
     * Fetches the current user's details.
     * @return A [NetworkUser] representing the current user.
     */
    @GET("user")
    suspend fun getCurrentUser(): NetworkUser

    /**
     * Registers a new user with the provided details.
     * @param user The registration details for the new user.
     * @return A [NetworkUser] representing the newly registered user.
     */
    @POST("user")
    suspend fun register(@Body user: NetworkRegistrationUser): NetworkUser

    /**
     * Verifies a user's account using a verification code.
     * @param token The verification code.
     * @return A [NetworkUser] representing the verified user.
     */
    @POST("user/verify")
    suspend fun verify(@Body token: NetworkCode): NetworkUser
}