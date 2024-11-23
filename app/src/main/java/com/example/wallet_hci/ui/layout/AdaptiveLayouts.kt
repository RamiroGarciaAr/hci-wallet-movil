package com.example.wallet_hci.ui.layout

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.window.core.layout.*

import com.example.wallet_hci.ui.layout.CompactLayout
import com.example.wallet_hci.ui.layout.ExpandedLayout
import com.example.wallet_hci.ui.layout.MediumLayout

object WindowSizeClass {
    val COMPACT = Pair(WindowWidthSizeClass.COMPACT, WindowHeightSizeClass.COMPACT)
    val MEDIUM = Pair(WindowWidthSizeClass.MEDIUM, WindowHeightSizeClass.MEDIUM)
    val EXPANDED = Pair(WindowWidthSizeClass.EXPANDED, WindowHeightSizeClass.EXPANDED)
}

@Composable
fun AdaptativeApp(
    compact: @Composable () -> Unit,
    medium: @Composable () -> Unit,
    expanded: @Composable () -> Unit
){
    val windowInfo = currentWindowAdaptiveInfo()
    
    val width = windowInfo.windowSizeClass.windowWidthSizeClass
    val height = windowInfo.windowSizeClass.windowHeightSizeClass

    when(Pair(width, height))
    {
        WindowSizeClass.COMPACT -> compact()
        WindowSizeClass.MEDIUM -> medium()
        WindowSizeClass.EXPANDED -> expanded()
        else -> Text("Unknown window size class")
    }
}