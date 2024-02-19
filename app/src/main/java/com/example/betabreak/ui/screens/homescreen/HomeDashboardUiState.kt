package com.example.betabreak.ui.screens.homescreen

import com.example.betabreak.ui.utils.BottomNav
import com.example.betabreak.ui.utils.BottomNavType
import com.example.betabreak.ui.utils.InspectionType

data class HomeDashboardUiState(
    /*
    Class Name: HomeMenuUiState
    Class Description: This class is a data class that is used to manage the state of the home menu.
     */
    val dashboardMap: Map<BottomNavType, List<BottomNav>> = emptyMap(),
    val currentDashboardItemMap: InspectionType = InspectionType.Carabiner,
    val isShowingHomepage: Boolean = true
)
