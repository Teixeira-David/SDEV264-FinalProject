package com.example.betabreak.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.betabreak.data.Screens
import com.example.betabreak.data.navigateTo

private val Typography.caption: TextStyle
    get() {
        return TextStyle(
            color = Color.Black, // Text color for the caption
            fontSize = 12.sp, // Font size of the caption text
        )
    }

@Composable
fun BottomBar(
    navController: NavHostController,
    viewModel: BottomNavViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .background(color = Color.White)
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            uiState.items.forEach { item ->
                BottomBarItem(
                    iconDrawable = item.icon,
                    text = item.label,
                    onItemClick = {
                        viewModel.selectNavItem(item.type)
                        // Convert BottomNavType to Screens and navigate
                        val screen = when (item.type) {
                            BottomNavType.Home -> Screens.Home
                            BottomNavType.Reports -> Screens.Report
                            BottomNavType.Settings -> Screens.Setting
                            BottomNavType.Help -> Screens.Help
                        }
                        navigateTo(screen, navController)
                    },
                    isSelected = item.isSelected
                )
            }
        }
    }
}

@Composable
fun BottomBarItem(
    iconDrawable: Int,
    text: String,
    onItemClick: () -> Unit,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onItemClick() }
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = iconDrawable),
            contentDescription = text,
            tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = text,
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
            style = MaterialTheme.typography.caption
        )
    }
}

