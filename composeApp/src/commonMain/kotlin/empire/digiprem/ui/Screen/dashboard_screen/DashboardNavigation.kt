package empire.digiprem.ui.Screen.dashboard_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import empire.digiprem.navigation.Dashboard
import empire.digiprem.navigation.Home
import empire.digiprem.navigation.SectionEnum
import empire.digiprem.presentation.components.AppHeader
import empire.digiprem.presentation.components.NavigationItem
import empire.digiprem.presentation.components.NavigationRailWithPopupDrawer
import empire.digiprem.presentation.components.TopBarAction


@Composable
fun DashboardNavigation(navController: NavHostController, dashboardState: Dashboard) {

    //  var selectedItem by remember { mutableStateOf() }
    var activeTopBarAction by remember { mutableStateOf(TopBarAction()) }
    var lamd: (String, @Composable () -> Unit) -> Unit = { title, content ->
        if (activeTopBarAction.enabled) {
            if (activeTopBarAction.currentActionName != title) {
                activeTopBarAction = activeTopBarAction.copy(
                    currentActionName = title,
                    content = content
                )
            } else {
                activeTopBarAction = TopBarAction()
            }
        } else {
            activeTopBarAction = activeTopBarAction.copy(
                currentActionName = title,
                enabled = true,
                content = content
            )
        }
    }
    var enableBar1 by remember { mutableStateOf(false) }
    var isPopupOpen by remember { mutableStateOf(false) }


    val navigation1 = listOf(
        NavigationItem(
            label = "Market Place",
            icon = Icons.Default.ShoppingCart,
            onClick = {
                navController.navigate(Home(isConnected = true))
                // selectedItem = MarketPlace
            }
        ),
        NavigationItem(
            label = "Mes annonces",
            icon = Icons.Default.Tag,
            selected = dashboardState.section == SectionEnum.annonceParcourrue.name,
            onClick = {
                navController.navigate(dashboardState.copy(section = SectionEnum.annonceParcourrue.name))
                // selectedItem = DashboardNavigationContainer.MesAnnonces.route
            },
            subNavigationItem = listOf(
                NavigationItem(
                    label = "Retour Dashboard",
                    icon = Icons.Default.ArrowBack,
                    onClick = {
                        // navController.navigate(AppNavigationContainer.Home.route)
                    }
                ),
                NavigationItem(
                    label = "Statistique",
                    icon = Icons.Default.QueryStats,
                    selected = dashboardState.section == "Statistique",
                    onClick = {
                        navController.navigate(dashboardState.copy(section = "Statistique"))

                    }
                ),
                NavigationItem(
                    label = "creer une annonce",
                    icon = Icons.Default.Create,
                    selected = dashboardState.section =="CreerUneAnnonce",
                    onClick = {
                        navController.navigate(dashboardState.copy(section = "CreerUneAnnonce"))
                    }
                ),
                NavigationItem(
                    label = "Toutes les Annonces",
                    icon = Icons.Default.SelectAll,
                    selected = dashboardState.section == "Toutes les Annonces",
                    onClick = {
                        navController.navigate(dashboardState.copy(section = SectionEnum.annonceParcourrue.name))
                    }
                ),
            )
        ),
        NavigationItem(
            label = "Notifications",
            icon = Icons.Default.Notifications,
            selected = dashboardState.section == SectionEnum.notifications.name,
            onClick = {
                navController.navigate(dashboardState.copy(section = SectionEnum.notifications.name))
                //  selectedItem = DashboardNavigationContainer.Notifications.route
            }
        ),
        NavigationItem(
            label = "Messages",
            icon = Icons.Default.Chat,
            selected = dashboardState.section == SectionEnum.chat.name,
            onClick = {
                navController.navigate(dashboardState.copy(section = SectionEnum.chat.name))
                // selectedItem = Messages.route
            }
        ),
        /*NavigationItem(
            label = "Annonces Pacouru",
            icon = Icons.Default.Traffic,
            //  selected = selectedItem == DashboardNavigationContainer.AnnonceSuivi.route,
            onClick = {
                isPopupOpen = true
                enableBar1 = false
                navigationController.navigate(AnnonceSuivi)
                // selectedItem = DashboardNavigationContainer.AnnonceSuivi.route
            }
        ),*/
    )
    /*
    val navigation2 = listOf(
        NavigationItem(
            label = "Market Place",
            icon = Icons.Default.ShoppingCart,
            selected = selectedItem == DashboardNavigationContainer.MarketPlace.route,
            onClick = {
                navigationController.navigate(DashboardNavigationContainer.MarketPlace.route)
                selectedItem = DashboardNavigationContainer.MarketPlace.route
            }
        ),
        NavigationItem(
            label = "Notifications",
            icon = Icons.Default.Notifications,
            selected = selectedItem == DashboardNavigationContainer.Notifications.route,
            onClick = {
                navigationController.navigate(DashboardNavigationContainer.Notifications.route)
                selectedItem = DashboardNavigationContainer.Notifications.route
            }
        ),
        NavigationItem(
            label = "Messages",
            icon = Icons.Default.Chat,
            selected = selectedItem == DashboardNavigationContainer.Messages.route,
            onClick = {
                enableBar1 = true
                navigationController.navigate(DashboardNavigationContainer.Messages.route)
                selectedItem = DashboardNavigationContainer.Messages.route
            }
        ),
    )*/

    Box(modifier = Modifier.fillMaxSize()) {
        NavigationRailWithPopupDrawer(
            enabledExpensiveMenu = true,
            isPopupOpen = true,
            topBar = {
                AppHeader { }
            },
            navigationItems = navigation1
        ) {
            Box(modifier = Modifier.fillMaxSize().background(Color.LightGray.copy(alpha = 0.3f))) {
                println("bonjour le monde ${dashboardState.section}")
                when (dashboardState.section) {
                    SectionEnum.annonces.name -> {

                    }

                }
            }

        }

        AnimatedVisibility(
            visible = activeTopBarAction.enabled,//enabledNotification,
            modifier = Modifier.padding(top = 70.dp, end = 20.dp).wrapContentSize().align(Alignment.TopEnd)
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                    .background(Color.White)
            ) {
                activeTopBarAction.content()
            }
        }
    }


}
