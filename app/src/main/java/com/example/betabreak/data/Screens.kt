package com.example.betabreak.data

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

    // Adding a parameterized route for detail screens
    object Detail : Screens("detail/{rockGymCompId}") {
        // Function to create a route with a specific rockGymCompId
        fun createRoute(rockGymCompId: String) = "detail/$rockGymCompId"
    }
}

// Function to navigate to different screens based on the provided Screen object
fun navigateTo(screen: Screens, navController: NavHostController) {
    navController.navigate(screen.route)
}

