package com.example.betabreak.data

import android.util.Log
import androidx.navigation.NavHostController

sealed class Screens(val route: String) {
    object Login : Screens("login")
    object Home : Screens("home")
    object Carabiner : Screens("carabiner")
    object Ropes : Screens("ropes")
    object Harness : Screens("harness")
    object BelayDevice : Screens("belay_device")
    object AutoBelay : Screens("auto_belay")
    object Setting : Screens("settings")
    object Help : Screens("help")
    object Report : Screens("reports")

}

fun navigateTo(
    screen: Screens,
    navController: NavHostController
) {
    // Debug log to understand navigation behavior
    Log.d("NavigationDebug", "Navigating to ${screen.route}")

    navController.navigate(screen.route) {
        // Configure popUpTo to avoid a large stack of destinations
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        // Avoid multiple instances of the same screen
        launchSingleTop = true
        // Restore state when navigating back to a destination
        restoreState = true
    }
}

