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

    // Add a function to update the visibility of the home page
    fun showHomePage(show: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(isShowingHomePage = show)
        }
    }

    fun updateCurrentComp(selectedComp: Any?) {
        _uiState.update {
            it.copy(currentComp = selectedComp)
        }
    }

    // Modify the navigateToListPage and navigateToDetailPage functions if necessary to update the isShowingHomePage state.
    fun navigateToListPage() {
        _uiState.update {
            it.copy(isShowingListPage = true, isShowingHomePage = false)  // Set isShowingHomePage accordingly
        }
    }

    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(isShowingListPage = false, isShowingHomePage = false)  // Set isShowingHomePage accordingly
        }
    }
}

data class DashboardUiState(
    val homeListData: List<RockGymCompData> = emptyList(),
    val currentComp: Any? = DashboardCompData.dashboardData,
    val isShowingListPage: Boolean = true,
    val isShowingHomePage: Boolean = true
)
