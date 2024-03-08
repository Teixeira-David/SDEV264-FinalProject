package com.example.betabreak.ui.screens.loginscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.betabreak.R
import com.example.betabreak.data.Screens

@Composable
fun LoginScreen(navController: NavHostController) {
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
        // Insert the logo image at the center of the screen
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                // Center the logo horizontally
                .align(Alignment.CenterHorizontally)
                .size(200.dp)
        )
        // Add a spacer between the logo and the text field
        Spacer(modifier = Modifier.height(16.dp))

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
            onClick = {
                if (strUserName.isNotEmpty() && strPassword.isNotEmpty()) {
                    // Assume this is where you'd verify the credentials
                    navController.navigate(Screens.Home.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            }
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
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}