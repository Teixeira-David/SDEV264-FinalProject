package com.example.betabreak.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.betabreak.R

@Composable
fun SettingsScreen(
    userName: TextFieldValue,
    onUserNameChange: (TextFieldValue) -> Unit,
    userPassword: TextFieldValue,
    onUserPasswordChange: (TextFieldValue) -> Unit,
    userEmail: TextFieldValue,
    onUserEmailChange: (TextFieldValue) -> Unit,
    inspectionInterval: String,
    onInspectionIntervalChange: (String) -> Unit,
    isDarkThemeEnabled: Boolean,
    onDarkThemeEnabledChange: (Boolean) -> Unit
) {
    val inspectionOptions = listOf(
        stringResource(id = R.string.once_per_week),
        stringResource(id = R.string.once_per_month),
        stringResource(id = R.string.once_per_six_month),
        stringResource(id = R.string.custom_inspection_timeframe)
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text("User Information", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = userName,
            onValueChange = onUserNameChange,
            label = { Text("User Name") }
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = userPassword,
            onValueChange = onUserPasswordChange,
            label = { Text("Password") }
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = userEmail,
            onValueChange = onUserEmailChange,
            label = { Text("Email") }
        )
        Spacer(Modifier.height(16.dp))

        Text("Inspection Interval", style = MaterialTheme.typography.headlineMedium)
        inspectionOptions.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButton(
                    selected = inspectionInterval == option,
                    onClick = { onInspectionIntervalChange(option) }
                )
                Text(option, style = MaterialTheme.typography.bodyLarge)
            }
        }
        Spacer(Modifier.height(16.dp))

        Text("Theme", style = MaterialTheme.typography.headlineMedium)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enable dark theme", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.weight(1f))
            Switch(
                checked = isDarkThemeEnabled,
                onCheckedChange = onDarkThemeEnabledChange
            )
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun SettingsScreenWithState(navController: NavHostController) {
    var userName by remember { mutableStateOf(TextFieldValue("")) }
    var userEmail by remember { mutableStateOf(TextFieldValue("")) }
    var inspectionInterval by remember { mutableStateOf("Once per week") }
    var isDarkThemeEnabled by remember { mutableStateOf(false) }

    SettingsScreen(
        userName = userName,
        onUserNameChange = { userName = it },
        userPassword = TextFieldValue(),
        onUserPasswordChange = {},
        userEmail = userEmail,
        onUserEmailChange = { userEmail = it },
        inspectionInterval = inspectionInterval,
        onInspectionIntervalChange = { inspectionInterval = it },
        isDarkThemeEnabled = isDarkThemeEnabled,
        onDarkThemeEnabledChange = { isDarkThemeEnabled = it }
    )
}

@Preview(showBackground = true, name = "Settings Screen Preview")
@Composable
fun SettingsScreenPreview() {
    MaterialTheme {
        SettingsScreenWithState(navController = rememberNavController())
    }
}
