package com.example.betabreak.ui

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.betabreak.ui.theme.BetaBreakTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>() // Create a ViewModel instance

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate started")

        try {
            // Install the splash screen and set a condition to keep it visible
            installSplashScreen().apply {
                // Configure the splash screen during the splash screen initialization
                setKeepOnScreenCondition {
                    // Keep the splash screen visible until the ViewModel is ready
                    !viewModel.isReady.value
                }
                // Shrink the splash screen window to the minimum size and jump to log in screen
                setOnExitAnimationListener { screen ->
                    val zoomX = ObjectAnimator.ofFloat(
                        screen.iconView,
                        View.SCALE_X,
                        0.4f,
                        0.0f
                    )
                    zoomX.interpolator = OvershootInterpolator() // Add an overshoot interpolator
                    zoomX.duration = 500L // Wait 500 milliseconds
                    zoomX.doOnEnd { screen.remove() }  // Remove the splash screen

                    val zoomY = ObjectAnimator.ofFloat(
                        screen.iconView,
                        View.SCALE_Y,
                        0.4f,
                        0.0f
                    )
                    zoomY.interpolator = OvershootInterpolator()
                    zoomY.duration = 500L
                    zoomY.doOnEnd { screen.remove() }

                    zoomX.start() // Start the zoomX animation
                    zoomY.start() // Start the zoomY animation
                }
            }

            setContent {
                BetaBreakTheme {
                    val navController = rememberNavController()
                    val windowSize = WindowWidthSizeClass.Compact
                    AppNavigation(navController, windowSize)
                }
            }
            Log.d("MainActivity", "onCreate finished successfully")
        } catch (e: Exception) {
            Log.e("MainActivity", "onCreate failed with exception: $e")
        }
    }
}