/* Auto create component */
package empire.digiprem.navigation

/* Auto import file */
import empire.digiprem.navigation.ViewExemple
import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import empire.digiprem.core.utils.getName
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.navigation.NavHostController
import empire.digiprem.presentation.components.NavigationItem
import empire.digiprem.presentation.components.NavigationRailWithPopupDrawer


@Composable
fun AppNavigationConfig(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    appScrollState: ScrollState,
) {
    var enableNavRail by remember { mutableStateOf(false) }
    var selectedNavItem by remember { mutableStateOf("") }

    val navigationItems = listOf<NavigationItem>(
                                    /* Auto config NavigationItem Content*/
                            NavigationItem(
    label = ViewExemple.getName(),
    icon = Icons.Default.Edit,
    selected =selectedNavItem==ViewExemple.getName(),
    onClick = {
       navController.navigate(ViewExemple())
     },
     subNavigationItem = listOf(
        /* Auto config ViewExemple section subNavigationItems Content*/
     )
  ),
        NavigationItem(
            label = ViewExemple.getName(),
            icon = Icons.Default.Edit,
            selected = selectedNavItem == ViewExemple.getName(),
            onClick = {
                navController.navigate(ViewExemple())
            },
            subNavigationItem = listOf(
                /* Auto config ViewExemple section subNavigationItems Content*/
            )
        ),

        NavigationItem(
            label = ViewExemple.getName(),
            icon = Icons.Default.Edit,
            selected = selectedNavItem == ViewExemple.getName(),
            onClick = {
                navController.navigate(ViewExemple())
            },
            subNavigationItem = listOf(
                /* Auto config ViewExemple section subNavigationItems Content*/
            )
        ),
    )
    val routes = listOf<String>(
        /* Auto config Routes Content*/ 
                        ViewExemple.getName(), 
                        ViewExemple.getName(), 
                        ViewExemple.getName(), 
                        ViewExemple.getName(),
        ViewExemple.getName(),
    )

    NavigationRailWithPopupDrawer(
        enableNavRail = enableNavRail,
        enabledExpensiveMenu = true,
        isPopupOpen = true,
        topBar = {},
        navigationItems = navigationItems
    ) {
        AppNavigation(
            navController = navController,
            startDestination =
        ) {
            selectedNavItem = it ?: ""
            enableNavRail = routes.contains(it)
        }
    }
}