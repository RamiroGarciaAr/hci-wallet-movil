package com.example.wallet_hci.data.model

import android.util.Log
import com.example.wallet_hci.data.DataSourceException
import com.example.wallet_hci.data.network.model.NetworkError
import kotlinx.serialization.json.Json
import retrofit2.Response
import java.io.IOException

abstract class RemoteDataSource {
    private val json = Json { ignoreUnknownKeys = true }

    /**
     * Handles the API response and maps it to the expected data type [T].
     * @param execute The suspend function that performs the API call.
     * @return The parsed response body of type [T].
     * @throws DataSourceException For all error scenarios.
     */
    suspend fun <T : Any> handleApiResponse(
        execute: suspend () -> Response<T>
    ): T? {
        try {
            val response = execute()
            val body = response.body()

            // Log success details
            Log.d(TAG, "API Call Successful: ${response.raw()}")

            if (response.isSuccessful) {
                return body
            }

            // Handle error responses
            response.errorBody()?.let {
                val error = json.decodeFromString<NetworkError>(it.string())
                throwDataSourceException(response.code(), error.message)
            }

            // Handle unexpected missing errors
            throw DataSourceException(UNEXPECTED_ERROR_CODE, "Missing error details")
        } catch (e: IOException) {
            Log.e(TAG, "Network connection error", e)
            throw DataSourceException(CONNECTION_ERROR_CODE, "Network connection error")
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error during API call", e)
            throw DataSourceException(UNEXPECTED_ERROR_CODE, "Unexpected error occurred")
        }
    }

    /**
     * Maps HTTP status codes to custom exceptions.
     * @param statusCode The HTTP status code.
     * @param message The error message from the server.
     * @throws DataSourceException with appropriate error code and message.
     */
    private fun throwDataSourceException(statusCode: Int, message: String): DataSourceException {
        Log.w(TAG, "API Error: $statusCode, $message")
        when (statusCode) {
            400 -> throw DataSourceException(DATA_ERROR, "Bad Request: $message")
            401 -> throw DataSourceException(UNAUTHORIZED_ERROR_CODE, "Unauthorized: $message")
            else -> throw DataSourceException(UNEXPECTED_ERROR_CODE, "Error $statusCode: $message")
        }
    }

    companion object {
        const val TAG = "RemoteDataSource"

        // Error codes
        const val UNAUTHORIZED_ERROR_CODE = 1
        const val DATA_ERROR = 2
        const val CONNECTION_ERROR_CODE = 98
        const val UNEXPECTED_ERROR_CODE = 99
    }
}
