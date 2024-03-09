package com.example.betabreak.ui.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BottomNavViewModel : ViewModel() {
    // Initial state of the bottom navigation
    private val _uiState = MutableStateFlow(BottomNavUiState())
    val uiState: StateFlow<BottomNavUiState> = _uiState

    // Function to update the selected bottom navigation item
    fun selectNavItem(type: BottomNavType) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.updateSelection(type)
        }
    }
}