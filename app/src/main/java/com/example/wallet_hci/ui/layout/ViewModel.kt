package com.example.wallet_hci.ui.layout

import kotlinx.serialization.Serializable
import androidx.compose.runtime.Composable


abstract class ViewModel {
    @Composable
    abstract fun View()
}