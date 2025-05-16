/* Auto create component */
package empire.digiprem.navigation

/* Auto import file */
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import empire.digiprem.config.isCompactMobilePlatform
import empire.digiprem.presentation.components.*


@Composable
fun AppNavigationConfig(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    appScrollState: ScrollState,
) {
    var enableNavRail by remember { mutableStateOf(false) }
    var isPopupOpen by remember { mutableStateOf(true) }
    var selectedNavItem by remember { mutableStateOf(EnumView.ViewStatistics) }
    var activeTopBarAction by remember { mutableStateOf(TopBarAction()) }
    val isCompactSize = isCompactMobilePlatform()
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

    val navigationItems = mutableListOf<NavigationItem>(
        /* Auto config NavigationItem Content*/
        NavigationItem(
            label = "Statistics",
            icon = Icons.Default.QueryStats,
            selected = selectedNavItem == EnumView.ViewStatistics,
            onClick = {
                navController.navigate(ViewStatistics())
            },
            subNavigationItem = listOf(
                /* Auto config ViewStatistics section subNavigationItems Content*/
            )
        ),
        NavigationItem(
            label = "Property",
            icon = Icons.Default.Home,
            selected = selectedNavItem == EnumView.ViewProperty,
            onClick = {
                navController.navigate(ViewProperty())
            },
            subNavigationItem = listOf(
                /* Auto config ViewProperty section subNavigationItems Content*/
                NavigationItem(
                    label = "All a Property",
                    icon = Icons.Default.SelectAll,
                    selected = selectedNavItem == EnumView.ViewPropertyALLProperty,
                    onClick = {
                        navController.navigate(ViewPropertyALLProperty())
                    },
                ),
                NavigationItem(
                    label = "Add a Property",
                    icon = Icons.Default.AddHome,
                    selected = selectedNavItem == EnumView.ViewPropertyAddProperty,
                    onClick = {
                        navController.navigate(ViewPropertyAddProperty())
                    },
                ),
            )
        ),
        NavigationItem(
            label = "Negotiations",
            icon = Icons.Default.Handshake,
            selected = selectedNavItem == EnumView.ViewNegotiations,
            onClick = {
                navController.navigate(ViewNegotiations())
            },
            subNavigationItem = listOf(
                /* Auto config ViewNegotiations section subNavigationItems Content*/
            )
        ),
        NavigationItem(
            label = "Settings",
            icon = Icons.Default.Settings,
            selected = selectedNavItem == EnumView.ViewSettings,
            onClick = {
                navController.navigate(ViewSettings())
            },
            subNavigationItem = listOf(
                /* Auto config ViewSettings section subNavigationItems Content*/
            )
        ),
    )
    if (isCompactSize.not()){
        navigationItems += listOf(
            NavigationItem(
                label = "Chat",
                icon = Icons.Default.Chat,
                selected = selectedNavItem == EnumView.ViewChat,
                onClick = {
                    navController.navigate(ViewChat())
                },
                subNavigationItem = listOf(
                    /* Auto config ViewChat section subNavigationItems Content*/
                )
            ),
            NavigationItem(
                label = "Notifications",
                icon = Icons.Default.Notifications,
                selected = selectedNavItem == EnumView.ViewNotifications,
                onClick = {
                    navController.navigate(ViewNotifications())
                },
                subNavigationItem = listOf(
                    /* Auto config ViewNotifications section subNavigationItems Content*/
                )
            ),
            NavigationItem(
                label = "Profil",
                icon = Icons.Default.Person,
                selected = selectedNavItem == EnumView.ViewProfil,
                onClick = {
                    navController.navigate(ViewProfil())
                },
                subNavigationItem = listOf(
                    /* Auto config ViewProfil section subNavigationItems Content*/
                )
            ),
        )
    }

    NavigationRailWithPopupDrawer(
        enableNavRail = enableNavRail,
        enabledExpensiveMenu = true,
        isPopupOpen = isPopupOpen,
        topBar = {
            AppHeader(
                navigationIcon = {
                    if (isCompactSize.not()) {
                        CustomNavigationRailItem(
                            icon = Icons.Default.Menu,
                        ) {
                            isPopupOpen = !isPopupOpen
                        }
                    }
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AppIconActionButton(
                        onClick = {
                            navController.navigate(ViewHome(isConnected = true))
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Home",
                        )
                    }
                    AppIconActionButton(
                        onClick = {
                            navController.navigate(ViewHome(isConnected = true))
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
                }

            }

        },
        onClickFloatingActionButton = {
            navController.navigate(ViewHome(isConnected = true))
        },
        navigationItems = navigationItems
    ) {
        val isCompactSize = isCompactMobilePlatform()
        AppNavigation(
            modifier = modifier,
            navController = navController,
            startDestination = if (isCompactSize) ViewSplash()  else ViewHome()
        ) { enumView ->
            selectedNavItem = enumView
            enableNavRail = EnumView.entries.filter { it == enumView }.any { it.includeInDashboard } || if (isCompactSize) enumView==EnumView.ViewHome  else false
        }
    }
}


/*Auto generate EnumView*/
enum class EnumView(val includeInDashboard: Boolean = false) {
    /*Auto generate EnumView content*/
    ViewSplash,
    ViewPropertyALLProperty(true),
    ViewPropertyAddProperty(true),
    ViewProfil(true),
    ViewProperty(true),
    ViewStatistics(true),
    ViewChat(true),
    ViewSettings(true),
    ViewNotifications(true),
    ViewNegotiations(true),
    ViewDetailRealEstateItem,
    ViewVerifyIdentity,
    ViewResetPassword,
    ViewForgotPassword,
    ViewRegister,
    ViewLogin,
    ViewHome,
    ViewConversations
}
