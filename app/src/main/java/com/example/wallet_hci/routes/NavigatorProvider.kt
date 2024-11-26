package com.example.wallet_hci.routes

import android.annotation.SuppressLint
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.wallet_hci.app.routes.Navigator

// Proveedor de Navigator para el Composition Local
@SuppressLint("CompositionLocalNaming")
val NavigatorProvider = staticCompositionLocalOf<Navigator> {
    error("Navigator not provided")
}
