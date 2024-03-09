package com.example.betabreak.ui.utils

import android.animation.ObjectAnimator
import android.app.Activity
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.LifecycleOwner
import com.example.betabreak.ui.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashScreenHandler(
    private val activity: Activity,
    private val viewModel: MainViewModel,
    private val lifecycleOwner: LifecycleOwner
) {
    fun initSplashScreen() {
        val splashScreen = activity.installSplashScreen()

        // Observe isReady StateFlow to determine when to end the splash screen
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.isReady.collect { isReady ->
                if (isReady) {
                    // Specify the exit animation when ready
                    splashScreen.setOnExitAnimationListener { screen ->
                        val zoomX = ObjectAnimator.ofFloat(
                            screen.iconView,
                            View.SCALE_X,
                            0.4f,
                            0.0f
                        ).apply {
                            interpolator = OvershootInterpolator()
                            duration = 500L
                            doOnEnd { screen.remove() }
                        }

                        val zoomY = ObjectAnimator.ofFloat(
                            screen.iconView,
                            View.SCALE_Y,
                            0.4f,
                            0.0f
                        ).apply {
                            interpolator = OvershootInterpolator()
                            duration = 500L
                            doOnEnd { screen.remove() }
                        }

                        zoomX.start()
                        zoomY.start()
                    }
                }
            }
        }
    }
}