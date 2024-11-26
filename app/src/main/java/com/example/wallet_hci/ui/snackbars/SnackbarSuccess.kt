package com.example.wallet_hci.ui.snackbars

import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.SnackbarDefaults as DefaultsSna
import androidx.compose.material3.SnackbarDuration as SnaD

class SnackbarSuccess constructor(
    actionLabel: String? = null,
    message: String,
    duration: SnaD = SnaD.Short,
    withDismissAction: Boolean = true,
    defaults: DefaultsSna = DefaultsSna,
): SnackbarVisuals {

    override val actionLabel: String? = actionLabel
    override val duration: SnaD = duration
    override val message: String = message
    override val withDismissAction: Boolean = withDismissAction
    val defaults: DefaultsSna = defaults
}