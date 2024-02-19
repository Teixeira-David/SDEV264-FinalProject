package com.example.betabreak.ui.screens.homescreen

import androidx.lifecycle.ViewModel
import com.example.betabreak.data.DashboardCompData
import com.example.betabreak.data.RockGymCompData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class HomeDashboardViewModel : ViewModel() {
    /*
    Class Name: HomeDashboardViewModel
    Class Description: This class is a ViewModel class that is used to manage the data for the BetaBreakActivity class.
     */
    private val _uiState = MutableStateFlow(
        DashboardUiState(
            homeListData = DashboardCompData.getDashboardData(),
            currentComp = DashboardCompData.getDashboardData().first()
        )
    )
    val uiState: StateFlow<DashboardUiState> = _uiState


    fun updateCurrentComp(selectedComp: Any?) {
        _uiState.update {
            it.copy(currentComp = selectedComp)
        }
    }

    fun navigateToListPage() {
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }


    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(isShowingListPage = false)
        }
    }
}

data class DashboardUiState(
    val homeListData: List<RockGymCompData> = emptyList(),
    val currentComp: Any? = DashboardCompData.dashboardData,
    val isShowingListPage: Boolean = true
)
