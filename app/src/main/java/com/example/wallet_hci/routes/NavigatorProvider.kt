package com.example.wallet_hci.routes


import androidx.compose.runtime.staticCompositionLocalOf
import com.example.wallet_hci.app.routes.Navigator

// Proveedor de Navigator para el Composition Local
val NavigatorProvider = staticCompositionLocalOf<Navigator> {
    error("Navigator not provided")
}
