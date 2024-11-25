package com.example.wallet_hci

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton
import androidx.compose.runtime.staticCompositionLocalOf

val SessionProvider = staticCompositionLocalOf<SessionManager> { error("SessionManager not provided") }

@Singleton
class SessionManager @Inject constructor(context: Context) {
    private var preferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun loadAuthToken(): String? {
        return preferences.getString(AUTH_TOKEN, null)
    }

    fun removeAuthToken() {
        val editor = preferences.edit()
        editor.remove(AUTH_TOKEN)
        editor.apply()
    }

    fun saveAuthToken(token: String) {
        val editor = preferences.edit()
        editor.putString(AUTH_TOKEN, token)
        editor.apply()
    }

    companion object {
        const val PREFERENCES_NAME = "app"
        const val AUTH_TOKEN = "auth_token"
    }
}