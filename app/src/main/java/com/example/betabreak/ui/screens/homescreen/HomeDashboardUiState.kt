package com.example.betabreak.ui.screens.homescreen

import com.example.betabreak.ui.utils.InspectionType

data class HomeDashboardUiState(
    /*
    Class Name: HomeMenuUiState
    Class Description: This class is a data class that is used to manage the state of the home menu.
     */
    val currentDashboardItemMap: InspectionType = InspectionType.Carabiner,
    val isShowingHomepage: Boolean = true
)
