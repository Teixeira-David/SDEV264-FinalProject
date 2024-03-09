package com.example.betabreak.ui.utils

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.betabreak.data.DashboardCompData
import com.example.betabreak.data.Screens
import com.example.betabreak.ui.screens.helpscreen.HelpScreen
import com.example.betabreak.ui.screens.homescreen.HomeApp
import com.example.betabreak.ui.screens.loginscreen.LoginScreen
import com.example.betabreak.ui.screens.settings.SettingsScreenWithState

@Composable
fun AppNavigation(
    navController: NavHostController,
    windowSize: WindowWidthSizeClass
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Login.route
    ) {
        composable(Screens.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screens.Home.route) {
            val currentRockGymCompData = DashboardCompData.getDashboardData()
            HomeApp(
                windowSize = windowSize,
                onBackPressed = { navController.popBackStack() },
                rockGymCompData = currentRockGymCompData,
            )
        }
        composable(Screens.Setting.route) {
            SettingsScreenWithState(navController)
        }
        composable(Screens.Help.route) {
            HelpScreen(navController)
        }
    }
}


