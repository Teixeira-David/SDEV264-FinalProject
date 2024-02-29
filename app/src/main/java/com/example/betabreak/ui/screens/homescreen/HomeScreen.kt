package com.example.betabreak.ui.screens.homescreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.betabreak.R
import com.example.betabreak.data.DashboardCompData
import com.example.betabreak.data.RockGymCompData
import com.example.betabreak.ui.theme.BetaBreakTheme
import com.example.betabreak.ui.utils.BottomBar
import com.example.betabreak.ui.utils.HomeDashboardContentType

private val Any?.inspectionDetails: Int
    get() = when (this) {
        is RockGymCompData -> this.inspectionDetails
        else -> R.string.carabiner_inspection_description
    }

private val Any?.overdueInspectionCount: Int
    get() = when (this) {
        is RockGymCompData -> this.overdueInspectionCount
        else -> 0
    }

private val Any?.isOverdue: Boolean
    get() = when (this) {
        is RockGymCompData -> this.isOverdue
        else -> false
    }

private val Any?.dueInspectionCount: Int
    get() = when (this) {
        is RockGymCompData -> this.dueInspectionCount
        else -> 0
    }

private val Any?.titleResourceID: Int
    get() = when (this) {
        is RockGymCompData -> this.titleResourceID
        else -> R.string.carabiner_dashboard_title
    }

private val Any?.imageBanner: Int
    get() = when (this) {
        is RockGymCompData -> this.imageBanner
        else -> R.drawable.ic_carabiner_banner
    }
@Composable
fun HomeApp(
    /*
    Function Name: HomeApp
    Function Description: This function is used to create the main app screen for the BetaBreak app.
     */
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
    rockGymCompData: List<RockGymCompData>,
) {
    val viewModel: HomeDashboardViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> HomeDashboardContentType.ListOnly
        WindowWidthSizeClass.Expanded -> HomeDashboardContentType.ListAndDetail
        else -> HomeDashboardContentType.ListOnly
    }
    // Create a NavController to navigate between the list and detail screen
    val navController = rememberNavController()
    val selectedTabIndex = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            HomeAppBar(
                isShowingListPage = uiState.isShowingListPage,
                onBackButtonClick = { viewModel.navigateToListPage() },
                windowSize = windowSize
            )
        },
        bottomBar = {
            BottomBar(
                onItemClick = { index ->
                    selectedTabIndex.value = index
                    when (index) {
                        0 -> navController.navigate("home") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                        1 -> navController.navigate("report") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                        2 -> navController.navigate("help") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                        3 -> navController.navigate("settings") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                selectedIndex = selectedTabIndex.value
            )
        }
    ) { innerPadding ->
        when {
            contentType == HomeDashboardContentType.ListAndDetail -> {
                HomeListAndDetail(
                    homeListData = uiState.homeListData,
                    selectedComp = uiState.currentComp as RockGymCompData?,
                    onClick = { selectedRockGymCompData -> viewModel.updateCurrentComp(selectedRockGymCompData) },
                    onBackPressed = onBackPressed,
                    contentPadding = innerPadding,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            uiState.isShowingListPage -> {
                HomeList(
                    homeListData = uiState.homeListData,
                    onClick = { selectedRockGymCompData ->
                        viewModel.updateCurrentComp(selectedRockGymCompData)
                        viewModel.navigateToDetailPage()
                    },
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                    contentPadding = innerPadding,
                )
            }
            else -> {
                HomeListDetail(
                    selectedComp = uiState.currentComp,
                    contentPadding = innerPadding,
                    onBackPressed = { viewModel.navigateToListPage() }
                )
            }
        }
    }
}

/*
@Composable
fun HomeApp(
    /*
    Function Name: HomeApp
    Function Description: This function is used to create the main app screen for the BetaBreak app.
     */
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
    rockGymCompData: List<RockGymCompData>,
    navController: NavHostController,
) {
    val viewModel: HomeDashboardViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> HomeDashboardContentType.ListOnly
        WindowWidthSizeClass.Expanded -> HomeDashboardContentType.ListAndDetail
        else -> HomeDashboardContentType.ListOnly
    }

    Scaffold(
        topBar = {
            HomeAppBar(
                isShowingListPage = uiState.isShowingListPage,
                onBackButtonClick = { viewModel.navigateToListPage() },
                windowSize = windowSize
            )
        }
    ) { innerPadding ->
        when {
            contentType == HomeDashboardContentType.ListAndDetail -> {
                HomeListAndDetail(
                    homeListData = uiState.homeListData,
                    selectedComp = uiState.currentComp as RockGymCompData?,
                    onClick = { selectedRockGymCompData -> viewModel.updateCurrentComp(selectedRockGymCompData) },
                    onBackPressed = onBackPressed,
                    contentPadding = innerPadding,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            uiState.isShowingListPage -> {
                HomeList(
                    homeListData = uiState.homeListData,
                    onClick = { selectedRockGymCompData ->
                        viewModel.updateCurrentComp(selectedRockGymCompData)
                        viewModel.navigateToDetailPage()
                    },
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                    contentPadding = innerPadding,
                )
            }
            else -> {
                HomeListDetail(
                    selectedComp = uiState.currentComp,
                    contentPadding = innerPadding,
                    onBackPressed = { viewModel.navigateToListPage() }
                )
            }
        }
    }
}
*/


@Composable
private fun HomeListAndDetail(
    homeListData: List<RockGymCompData>,
    selectedComp: RockGymCompData?,
    onClick: (RockGymCompData?) -> Unit,
    onBackPressed: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier,
) {
    Row(
        modifier = modifier
    ) {
        HomeList(
            homeListData = homeListData,
            onClick = onClick,
            contentPadding = PaddingValues(
                top = contentPadding.calculateTopPadding(),
            ),
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )
        HomeListDetail(
            selectedComp = selectedComp,
            modifier = Modifier.weight(3f),
            contentPadding = PaddingValues(
                top = contentPadding.calculateTopPadding(),
            ),
            onBackPressed = onBackPressed,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    onBackButtonClick: () -> Unit,
    isShowingListPage: Boolean,
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val isShowingDetailPage = windowSize != WindowWidthSizeClass.Expanded && !isShowingListPage
    val dashboardItems = listOf(
        stringResource(R.string.carabiners_screen),
        stringResource(R.string.ropes_screen),
        stringResource(R.string.harness_screen),
        stringResource(R.string.belay_device_screen),
        stringResource(R.string.auto_belay_screen),
    )
    val selectedDashboardItem by remember { mutableStateOf(0) }

    TopAppBar(
        title = {
            Text(
                text =stringResource(R.string.dashboard_label),
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = if (isShowingDetailPage) {
            {
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        } else {
            { Box {} }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeListItem(
    homeListData: RockGymCompData,
    onItemClick: (RockGymCompData?) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(homeListData) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            HomeListImageItem(
                homeListData = homeListData,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(homeListData.titleResourceID),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
                Text(
                    text = stringResource(homeListData.subtitleResourceID),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
                Spacer(Modifier.weight(1f))
                Row {
                    Text(
                        text = pluralStringResource(
                            R.plurals.inspection_count_coming,
                            homeListData.dueInspectionCount,
                            homeListData.dueInspectionCount
                        ),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.weight(1f))
                    if (homeListData.isOverdue) {
                        Text(
                            text = pluralStringResource(
                                R.plurals.overdue_inspection_count,
                                homeListData.overdueInspectionCount,
                                homeListData.overdueInspectionCount
                            ),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeListImageItem(homeListData: RockGymCompData, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(homeListData.imageResourceID),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun HomeList(
    homeListData: List<RockGymCompData>,
    onClick: (RockGymCompData?) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_medium)),
    ) {
        items(homeListData, key = { homeListData -> homeListData.rgCompID }) { homeListData ->
            HomeListItem(
                homeListData = homeListData,
                onItemClick = onClick
            )
        }
    }
}

@Composable
private fun HomeListDetail(
    selectedComp: Any?,
    onBackPressed: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }
    val scrollState = rememberScrollState()
    val layoutDirection = LocalLayoutDirection.current
    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(top = contentPadding.calculateTopPadding())
    ) {
        Column(
            modifier = Modifier
                .padding(
                    bottom = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    end = contentPadding.calculateEndPadding(layoutDirection)
                )
        ) {
            Box {
                Box {
                    Image(
                        painter = painterResource(selectedComp.imageBanner),
                        contentDescription = null,
                        alignment = Alignment.TopCenter,
                        contentScale = ContentScale.FillWidth,
                    )
                }
                Column(
                    Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, MaterialTheme.colorScheme.scrim),
                                0f,
                                400f
                            )
                        )
                ) {
                    Text(
                        text = stringResource(selectedComp.titleResourceID),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        modifier = Modifier
                            .padding(horizontal = dimensionResource(R.dimen.padding_small))
                    )
                    Row(
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    ) {
                        Text(
                            text = pluralStringResource(
                                R.plurals.inspection_count_coming,
                                selectedComp.dueInspectionCount,
                                selectedComp.dueInspectionCount
                            ),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.inverseOnSurface,
                        )
                        Spacer(Modifier.weight(1f))
                        if (selectedComp.isOverdue) {
                            Text(
                                text = pluralStringResource(
                                    R.plurals.overdue_inspection_count,
                                    selectedComp.overdueInspectionCount,
                                    selectedComp.overdueInspectionCount
                                ),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                            )
                        }
                    }
                }
                Text(
                    text = stringResource(selectedComp.inspectionDetails),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        vertical = dimensionResource(R.dimen.padding_detail_content_vertical),
                        horizontal = dimensionResource(R.dimen.padding_detail_content_horizontal)
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeListItemPreview() {
    BetaBreakTheme {
        HomeListItem(
            homeListData = DashboardCompData.dashboardData,
            onItemClick = {}
        )
    }
}

@Preview
@Composable
fun HomeListPreview() {
    BetaBreakTheme {
        Surface {
            HomeList(
                homeListData = DashboardCompData.getDashboardData(),
                onClick = {},
            )
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
fun HomeListAndDetailsPreview() {
    BetaBreakTheme {
        Surface {
            HomeListAndDetail(
                homeListData = DashboardCompData.getDashboardData(),
                selectedComp = DashboardCompData.getDashboardData().getOrElse(0) {
                    DashboardCompData.dashboardData
                },
                onClick = {},
                onBackPressed = {},
                contentPadding = PaddingValues(all = 16.dp),
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
    }
}
