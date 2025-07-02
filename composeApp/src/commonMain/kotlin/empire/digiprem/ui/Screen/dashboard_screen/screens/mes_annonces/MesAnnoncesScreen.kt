package empire.digiprem.ui.Screen.dashboard_screen.screens.mes_annonces

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.SelectAll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import empire.digiprem.presentation.components.NavigationItem
import empire.digiprem.presentation.components.NavigationRailWithPopupDrawer
import empire.digiprem.presentation.components.TopBarAction

sealed class MesAnnoncesNavigationContainer(val route: String) {
    object Create : MesAnnoncesNavigationContainer("Create")
    object Annonces : MesAnnoncesNavigationContainer("Annonces")
    object statistique : MesAnnoncesNavigationContainer("MesAnnonces")
    object AnnonceSuivi : MesAnnoncesNavigationContainer("AnnonceSuivi")
}

/*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MesAnnoncesScreen(navController: NavHostController) {
    val navigationController = rememberNavController()
    var selectedItem by remember { mutableStateOf(MesAnnoncesNavigationContainer.statistique.route) }
    var isPopupOpen by remember { mutableStateOf(false) }
    var enabledNotification by remember { mutableStateOf(false) }
    var activeTopBarAction by remember { mutableStateOf(TopBarAction()) }
    Box(modifier = Modifier.fillMaxSize()) {
        NavigationRailWithPopupDrawer(
            enabledExpensiveMenu = true,
            isPopupOpen = true,
            navigationItems = listOf(
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
                    selected = selectedItem == MesAnnoncesNavigationContainer.statistique.route,
                    onClick = {
                        navigationController.navigate(MesAnnoncesNavigationContainer.statistique.route)
                        selectedItem = MesAnnoncesNavigationContainer.statistique.route
                    }
                ),
                NavigationItem(
                    label = "creer une annonce",
                    icon = Icons.Default.Create,
                    selected = selectedItem == MesAnnoncesNavigationContainer.Create.route,
                    onClick = {
                        navigationController.navigate(MesAnnoncesNavigationContainer.Create.route)
                        selectedItem = MesAnnoncesNavigationContainer.Create.route
                    }
                ),
                NavigationItem(
                    label = "Toutes les Annonces",
                    icon = Icons.Default.SelectAll,
                    selected = selectedItem == MesAnnoncesNavigationContainer.Annonces.route,
                    onClick = {
                        navigationController.navigate(MesAnnoncesNavigationContainer.Annonces.route)
                        selectedItem = MesAnnoncesNavigationContainer.Annonces.route
                    }
                ),
            ),
            topBar = {},
            navigationContent = {}
        ) {
            NavHost(
                modifier = Modifier.fillMaxSize().background(Color.LightGray.copy(alpha = 0.3f)),
                navController = navigationController,
                startDestination = MesAnnoncesNavigationContainer.statistique.route
            ) {
                composable(MesAnnoncesNavigationContainer.statistique.route) {
                    Box(modifier = Modifier.fillMaxSize().background(Color.Red))
                }
                composable(MesAnnoncesNavigationContainer.Create.route) {
                }
                composable(MesAnnoncesNavigationContainer.Annonces.route) {
                    Box(modifier = Modifier.fillMaxSize().background(Color.Red))
                }
                */
/*
                composable(MesAnnoncesNavigationContainer.Annonces.route) {
                    MesAnnoncesScreen(navController)
                }*//*

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
*/
