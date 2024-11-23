// package com.example.wallet_hci.ui.layout

// import androidx.compose.runtime.Composable
// import androidx.compose.material3.Text
// import androidx.compose.material3.Surface
// import androidx.compose.ui.tooling.preview.Preview
// import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
// import androidx.compose.material3.adaptive.WindowSizeClass

// @Composable
// fun AdaptativeApp()
// {
//     val windowInfo = currentWindowAdaptiveInfo()
//     when(windowInfo.windowSizeClass)
//     {
//         WindowSizeClass.Compact -> CompactLayout()
//         WindowSizeClass.Medium -> MediumLayout()
//         WindowSizeClass.Expanded -> ExpandedLayout()
//         else -> Text("Unknown window size class")
//     }
// }