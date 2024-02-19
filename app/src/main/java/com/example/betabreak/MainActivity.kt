package com.example.betabreak

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.betabreak.ui.theme.BetaBreakTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>() // Create a ViewModel instance

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Call the login screen
                    LoginScreen(onLoginSubmitted = { strUserName, strPassword ->
                        // Handle login logic here
                        println("Username: $strUserName, Password: $strPassword")
                    })
                }
            }
        }
    }
}

@Composable
fun LoginScreen(onLoginSubmitted: (String, String) -> Unit) {
    /*
    Function Name: LoginScreen
    Function Description: This function is used to create a login screen for the app.
     */
    // Declare Local Variables
    var strUserName by remember { mutableStateOf("") }
    var strPassword by remember { mutableStateOf("") }
    var blnPasswordVisible by remember { mutableStateOf(false) }

    // Add functionality to create a login screen
    Column( // Add a Column to the Composable
        // Add a modifier to the Column
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Add a Text Composable to the Column
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineMedium // Set the text style to headlineMedium
        )
        // Create the OutlinedTextField for the username
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = strUserName,
            onValueChange = { strUserName = it },
            label = { Text("Username") },
            singleLine = true
        )
        // Create the OutlinedTextField for the password
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = strPassword,
            onValueChange = { strPassword = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = if (blnPasswordVisible) VisualTransformation.None else PasswordsVisualTransformation,
            trailingIcon = {
                val image = if (blnPasswordVisible)
                    // Turn on the visibility of the password
                    painterResource(id = R.drawable.ic_visibility)
                else
                    // Turn off the visibility of the password
                    painterResource(id = R.drawable.ic_visibility_off)

                // Add the IconButton
                IconButton(onClick = { blnPasswordVisible = !blnPasswordVisible }) {
                    Icon(painter = image, contentDescription = "Toggle password visibility")
                }
            }
        )
        // Now add the login button
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onLoginSubmitted(strUserName, strPassword) },
            enabled = strUserName.isNotBlank() && strPassword.isNotBlank()
        ) {
            Text("Login")
        }
    }
}

object PasswordsVisualTransformation : VisualTransformation {
    /*
    Class Name: PasswordsVisualTransformation
    Class Description: This class is used to create a custom visual transformation for the password field.
     */
    override fun filter(text: AnnotatedString): TransformedText {
        // Create a custom visual transformation for the password field
        val bullet = "\u2022"
        val transformString = AnnotatedString(text.text.map { bullet }.joinToString(""))

        // Set the offsetMapping of the text
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int = offset
            override fun transformedToOriginal(offset: Int): Int = offset
        }
        return TransformedText(transformString, offsetMapping)
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    // Create a preview of the LoginScreen
    LoginScreen(onLoginSubmitted = { strUserName, strPassword ->
        println("Username: $strUserName, Password: $strPassword")

    })
}