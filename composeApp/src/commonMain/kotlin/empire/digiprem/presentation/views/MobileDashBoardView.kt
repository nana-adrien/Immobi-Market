package empire.digiprem.presentation.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import empire.digiprem.navigation.*
import empire.digiprem.presentation.components.*
import empire.digiprem.presentation.components.app.equipmentes
import empire.digiprem.presentation.viewmodels.MobileDashBoardViewModel
import kotlinx.coroutines.Dispatchers
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

data class HorizontalPagerItem(
    val navigationItem: NavigationItem,
    val content: @Composable () -> Unit
)

@Composable
fun MobileDashBoardView(
    viewMobileDashBoard: ViewMobileDashBoard,
    navController: NavHostController,
    mobiledashboardViewModel: MobileDashBoardViewModel = koinViewModel()
) {
    // val mobiledashboardViewModel:MobileDashBoardViewModel = viewModel{MobileDashBoardViewModel()}
    val state by mobiledashboardViewModel.state.collectAsState()
    val onSendIntent = mobiledashboardViewModel::onIntentHandler
    val views: MutableList<HorizontalPagerItem> = mutableListOf(
       HorizontalPagerItem(
            NavigationItem(
                label = "Market",
                icon = Icons.Default.ShoppingCart,
                onClick = {},
            ),
            { HomeView(viewHome = ViewHome(), navController = navController) }
        ),
    )
    if (viewMobileDashBoard.isConnected) {
        views += listOf(
            HorizontalPagerItem(
                NavigationItem(
                    label = "Property",
                    icon = Icons.Default.Home,
                    onClick = {
                        navController.navigate(ViewProperty())
                    },
                ),
                { PropertyView(viewProperty = ViewProperty(), navController = navController) }
            ),
            HorizontalPagerItem(
                NavigationItem(
                    label = "Statistics",
                    icon = Icons.Default.QueryStats,
                    onClick = {},
                ),
                { StatisticsView(viewStatistics = ViewStatistics(), navController = navController) }
            ),

            HorizontalPagerItem(
                NavigationItem(
                    label = "Chat",
                    icon = Icons.Default.Chat,
                    onClick = { },
                ),
                {
                    ConversationsView(viewConversations = ViewConversations(), navController = navController)
                }
            ),
            HorizontalPagerItem(
                NavigationItem(
                    label = "Settings",
                    icon = Icons.Default.Settings,
                    onClick = { },
                ),
                {
                    SettingsView(viewSettings = ViewSettings(), navController = navController)
                }
            ),
        )
    }

    val pageState = rememberPagerState(0) { views.size }
    val scope = rememberCoroutineScope{ Dispatchers.Unconfined}
    val enableSort = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AppHeader(
            ) {
                Row(
                    modifier = Modifier.padding(end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (pageState.currentPage == 0) {
                        AppIconActionButton(
                            onClick = {
                                enableSort.value = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Sort,
                                contentDescription = "sort",
                            )
                            DropdownMenu(
                                expanded = enableSort.value,
                                onDismissRequest = { enableSort.value = false }
                            ) {
                                RealEstateSearchForm(
                                    availableEquipments = equipmentes
                                ) {
                                    println("Serache form=$it")
                                }
                            }
                        }
                    }
                    if (viewMobileDashBoard.isConnected) {
                        AppIconActionButton(
                            onClick = {
                                navController.navigate(ViewNotifications())
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Home",
                            )
                        }
                        AppIconActionButton(
                            onClick = {
                                navController.navigate(ViewProfil())
                            }
                        ) {
                            AsyncImage(
                                model = "https://lh3.googleusercontent.com/a/ACg8ocLM-v1DuVykbTszaWgG5NWBl2J2n9iIFi7MStQ08_z_prjXdg=s96-c",
                                contentDescription = "profile",
                                modifier = Modifier.fillMaxSize(),
                                clipToBounds = true,
                                contentScale = ContentScale.Crop
                            )
                        }
                    } else {
                        Row(
                            modifier = Modifier.width(80.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            AppButton(onClick = {
                                navController.navigate(ViewLogin())
                            }) {
                                Text(
                                    "se connecter",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = it
                                    )
                                )
                            }
                        }
                    }
                }
            }
        },
        bottomBar = {
            if (viewMobileDashBoard.isConnected) {
                AppBottomBar {
                    views.forEachIndexed { index, it ->
                        Column(
                            modifier = Modifier.weight(1f).height(66.dp)
                                .clickable {
                                   // scope.launch {
                                        pageState.requestScrollToPage(page =index)
                                       // pageState.scrollToPage(page = index)
                                    //}
                                }.padding(5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            var isClicked=(pageState.currentPage == index)
                            // Animation de couleur
                            val backgroundColor by animateColorAsState(
                                targetValue = if (isClicked)  MaterialTheme.colorScheme.primary else Color.Transparent,
                                animationSpec = tween(durationMillis = 300)
                            )

                            val scale by animateFloatAsState(
                                targetValue = if (isClicked) 1.1f else 1f,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                ),
                                label = "scale"
                            )

                            Box(
                                modifier = Modifier.wrapContentSize().graphicsLayer {
                                    scaleX = scale
                                    //  scaleY = 1f
                                }//.clip(CircleShape)
                                    .background(backgroundColor, shape = RoundedCornerShape(16.dp))
                                    .padding(vertical = 3.dp, horizontal = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    //modifier = Modifier.size(17.dp),
                                    imageVector = it.navigationItem.icon,
                                    contentDescription = "",
                                    tint = if (index == pageState.currentPage) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.primary,
                                )
                            }

                            Text(
                                it.navigationItem.label.split(" ").last(),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontWeight = if (index == pageState.currentPage)  FontWeight.Bold else  FontWeight.Normal
                                )
                            )
                        }
                    }
                }
            }
        }

    ) {
        HorizontalPager(
            state = pageState,
            key = {ti->ti},
            modifier = Modifier.padding(it).fillMaxSize()
        ) { page ->
            views[page].content()
        }
    }

}

@Preview()
@Composable
fun view() {

}