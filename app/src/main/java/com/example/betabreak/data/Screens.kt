package com.example.betabreak.data

import androidx.navigation.NavHostController

sealed class Screens(val route: String) {
    /*
    Class Name: Screens
    Class Description: This class is used to create a class for the screens.
     */
    object Login : Screens("login")
    object Home : Screens("home")
    object Carabiner : Screens("carabiner")
    object Ropes : Screens("ropes")
    object Harness : Screens("harness")
    object BelayDevice : Screens("belay_device")
    object AutoBelay : Screens("auto_belay")
}

// Function to navigate to different screens based on the provided Screen object
fun navigateTo(screen: Screens, navController: NavHostController) {
    navController.navigate(screen.route)
}