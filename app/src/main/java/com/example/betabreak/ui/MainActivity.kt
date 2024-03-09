package com.example.betabreak.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.betabreak.ui.theme.BetaBreakTheme
import com.example.betabreak.ui.utils.AppNavigation

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>() // Create a ViewModel instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup splash screen with exit animation based on viewModel's state
        installSplashScreen().setOnExitAnimationListener { splashScreenViewProvider ->
            // Your animation logic here
            // Example: Fade out the splash screen icon
            splashScreenViewProvider.iconView.animate()
                .alpha(0f)
                .setDuration(200L)
                .withEndAction {
                    splashScreenViewProvider.remove()
                }
                .start()
        }
        setContent {
            BetaBreakTheme {
                // Wrap the content of your app inside Surface for Material Design
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    AppNavigation(navController = navController, windowSize = WindowWidthSizeClass.Compact)
                }
            }
        }
    }
}