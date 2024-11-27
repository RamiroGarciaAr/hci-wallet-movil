package com.example.wallet_hci.screens.app.Config

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel

class AccordionViewModel : ViewModel() {
    // Map to track the expanded state of each item by title
    private val _expandedStates = mutableStateMapOf<String, Boolean>()
    val expandedStates: Map<String, Boolean> get() = _expandedStates

    // Toggle the expanded state of an item
    fun toggleExpanded(title: String) {
        _expandedStates[title] = _expandedStates[title] != true
    }

    // Set the initial state for an item if not present
    fun ensureItemInitialized(title: String) {
        if (title !in _expandedStates) {
            _expandedStates[title] = false
        }
    }
}
