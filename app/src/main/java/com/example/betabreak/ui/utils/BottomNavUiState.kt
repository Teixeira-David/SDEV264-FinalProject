package com.example.betabreak.ui.utils

import com.example.betabreak.R

data class BottomNavUiState(
    // Holds a list of BottomNavItem representing each item in the bottom navigation.
    val items: List<BottomNavItem> = listOf(
        BottomNavItem(BottomNavType.Home, "Home", R.drawable.ic_home_img, true),
        BottomNavItem(BottomNavType.Reports, "Reports", R.drawable.ic_reports_img, false),
        BottomNavItem(BottomNavType.Settings, "Settings", R.drawable.ic_settings_img, false),
        BottomNavItem(BottomNavType.Help, "Help", R.drawable.ic_help, false)
    )
) {
    // Helper function to update the selected state based on BottomNavType
    fun updateSelection(selectedType: BottomNavType): BottomNavUiState {
        val updatedItems = items.map { item ->
            if (item.type == selectedType) item.copy(isSelected = true)
            else item.copy(isSelected = false)
        }
        return copy(items = updatedItems)
    }
}

data class BottomNavItem(
    val type: BottomNavType, // Enum representing the type of navigation item
    val label: String, // Display label for the item
    val icon: Int, // Resource ID for the item's icon
    val isSelected: Boolean // Boolean indicating if the item is currently selected
)

enum class BottomNavType {
    /*
        Class Name: BottomNavType
        Class Description: This class is a data class that is used to define the different types of navigation.
     */
    Home, Reports, Settings, Help
}