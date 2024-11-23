package com.example.wallet_hci.ui.layout

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
/import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo

@Composable
fun AdaptativeApp()
{
    val windowInfo = currentWindowAdaptiveInfo()
    when(windowInfo.windowSizeClass)
    {
        WindowSizeClass.Compact -> CompactLayout()
        WindowSizeClass.Medium -> MediumLayout()
        WindowSizeClass.Expanded -> ExpandedLayout()
        else -> Text("Unknown window size class")
    }
}