package com.example.betabreak.ui.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betabreak.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BottomNavViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        BottomNavUiState(
            items = listOf(
                BottomNavItem(BottomNavType.Home, "Home", R.drawable.ic_home_img),
                BottomNavItem(BottomNavType.Reports, "Reports", R.drawable.ic_reports_img),
                BottomNavItem(BottomNavType.Settings, "Settings", R.drawable.ic_settings_img),
                BottomNavItem(BottomNavType.Help, "Help", R.drawable.ic_help)
            )
        )
    )
    val uiState: StateFlow<BottomNavUiState> = _uiState

    fun selectNavItem(selectedType: BottomNavType) {
        viewModelScope.launch {
            // No need to update each item's isSelected. Instead, update the selectedType in uiState.
            _uiState.value = _uiState.value.copy(selectedType = selectedType)
        }
    }
}