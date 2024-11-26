package com.example.wallet_hci

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.material3.SnackbarHostState
import javax.inject.Inject
import javax.inject.Singleton

val UiStateProvider = staticCompositionLocalOf<UiState> { error("UiState not provided") }

@Singleton
class UiState @Inject constructor() {
    var snackbarHostState by mutableStateOf(SnackbarHostState())
    var showNavigationBar by mutableStateOf(true)
    var isLoading by mutableStateOf(false)

    fun toggleNavigationBar() {
        showNavigationBar = !showNavigationBar
    }

    fun toogleLoading() {
        isLoading = !isLoading
    }
}