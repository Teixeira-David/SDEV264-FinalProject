package com.example.betabreak.ui.utils

import com.example.betabreak.R

// Updated UI State to remove the isSelected flag from the constructor
data class BottomNavUiState(
    val items: List<BottomNavItem> = listOf(
        BottomNavItem(BottomNavType.Home, "Home", R.drawable.ic_home_img),
        BottomNavItem(BottomNavType.Reports, "Reports", R.drawable.ic_reports_img),
        BottomNavItem(BottomNavType.Settings, "Settings", R.drawable.ic_settings_img),
        BottomNavItem(BottomNavType.Help, "Help", R.drawable.ic_help)
    ),
    val selectedType: BottomNavType = BottomNavType.Home
) {
    // Update to handle selection based on type
    fun updateSelection(selectedType: BottomNavType): BottomNavUiState {
        return copy(selectedType = selectedType)
    }
}

data class BottomNavItem(
    val type: BottomNavType,
    val label: String,
    val icon: Int
)

enum class BottomNavType(val route: String) {
    Home("home"),
    Reports("reports"),
    Settings("settings"),
    Help("help");
}