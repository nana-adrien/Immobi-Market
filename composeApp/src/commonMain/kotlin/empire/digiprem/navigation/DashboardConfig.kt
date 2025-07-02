/* Auto create component */
package empire.digiprem.navigation

/* Auto import file */
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import empire.digiprem.config.Platform
import empire.digiprem.config.getPlatform
import empire.digiprem.config.isCompactMobilePlatform
import empire.digiprem.config.isCompactPlatform
import empire.digiprem.core.utils.getName
import empire.digiprem.enums.Role
import empire.digiprem.presentation.components.*
import empire.digiprem.presentation.views.HomeView
import empire.digiprem.presentation.views.WebDesktopHeaderAppBar
import kotlinx.coroutines.launch
import octopusfx.client.mobile.core.ui.theme.LocalSessionManager
import octopusfx.client.mobile.core.ui.theme.LocalUtilisateur


@Composable
fun ApplicationNavigationController(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    appScrollState: ScrollState,
) {
    /*if (getPlatform()==Platform.IOS|| getPlatform()==Platform.ANDROID){
        AppMobileNavigation(
            navController =  navController,
            modifier = modifier,
            appScrollState = appScrollState
        )
    }else  {*/
    AppWebDesktopNavigationConfig(
        navController = navController,
        modifier = modifier,
        appScrollState = appScrollState
    )
    //}
}

@Composable
fun AppWebDesktopNavigationConfig(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    appScrollState: ScrollState,
) {
    val utilisateur by LocalSessionManager.current.utilisateur.collectAsState()
    var enableNavRail by remember { mutableStateOf(false) }
    var isPopupOpen by remember { mutableStateOf(false) }
    var selectedNavItem by remember { mutableStateOf(EnumView.ViewStatistics) }
    var activeTopBarAction by remember { mutableStateOf(TopBarAction()) }
    val isCompactSize = isCompactPlatform()
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

    var drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed)

    val scope = rememberCoroutineScope()
    val navigationItems = mutableListOf<NavigationItem>(
        /* Auto config NavigationItem Content*/
        NavigationItem(
            label = "Statistics",
            icon = Icons.Default.QueryStats,
            selected = selectedNavItem == EnumView.ViewStatistics,
            onClick = {
                navController.navigate(ViewStatistics())
                isPopupOpen = !isPopupOpen
            },
            subNavigationItem = listOf(
                /* Auto config ViewStatistics section subNavigationItems Content*/
            )
        ),
        NavigationItem(
            label = "Profil",
            icon = Icons.Default.Person,
            selected = selectedNavItem == EnumView.ViewProfil,
            onClick = {
                navController.navigate(ViewProfil())
                isPopupOpen = !isPopupOpen
            },
            subNavigationItem = listOf(
                /* Auto config ViewProfil section subNavigationItems Content*/
            )
        ),
        NavigationItem(
            label = "Negotiations",
            icon = Icons.Default.Handshake,
            selected = selectedNavItem == EnumView.ViewNegotiations,
            onClick = {
                navController.navigate(ViewNegotiations())
                isPopupOpen = !isPopupOpen
            },
            subNavigationItem = listOf(
                /* Auto config ViewNegotiations section subNavigationItems Content*/
            )
        ),
        NavigationItem(
            label = "Messages",
            icon = Icons.Default.Chat,
            selected = selectedNavItem == EnumView.ViewMessenger,
            onClick = {
                navController.navigate(ViewMessenger())
                isPopupOpen = !isPopupOpen
            },
            subNavigationItem = listOf(
                /* Auto config ViewMessenger section subNavigationItems Content*/
            )
        ),
        NavigationItem(
            label = "Notifications",
            icon = Icons.Default.Notifications,
            selected = selectedNavItem == EnumView.ViewNotifications,
            onClick = {
                navController.navigate(ViewNotifications())
                isPopupOpen = !isPopupOpen
            },
            subNavigationItem = listOf(
                /* Auto config ViewNotifications section subNavigationItems Content*/
            )
        ),
        NavigationItem(
            label = "Paramètre",
            icon = Icons.Default.Settings,
            selected = selectedNavItem == EnumView.ViewSettings,
            onClick = {
                navController.navigate(ViewSettings())
                isPopupOpen = !isPopupOpen
            },
            subNavigationItem = listOf(
                /* Auto config ViewSettings section subNavigationItems Content*/
            )
        ),

    )
    utilisateur?.let {
        if (it.role==Role.PROPRIETAIRE) {
            navigationItems += NavigationItem(
                label = "Mes biens",
                icon = Icons.Default.Home,
                selected = selectedNavItem == EnumView.ViewProperty,
                onClick = {
                    navController.navigate(ViewProperty())
                },
                subNavigationItem = listOf(
                    /* Auto config ViewProperty section subNavigationItems Content*/
                    NavigationItem(
                        label = "Tous mes biens",
                        icon = Icons.Default.SelectAll,
                        selected = selectedNavItem == EnumView.ViewPropertyALLProperty,
                        onClick = {
                            navController.navigate(ViewPropertyALLProperty())
                            isPopupOpen = !isPopupOpen
                        },
                    ),
                    NavigationItem(
                        label = "Ajouter un bien",
                        icon = Icons.Default.AddHome,
                        selected = selectedNavItem == EnumView.ViewPropertyAddProperty,
                        onClick = {
                            navController.navigate(ViewPropertyAddProperty())
                            isPopupOpen = !isPopupOpen
                        },
                    ),
                )
            )
        }
        if (it.role==Role.ADMINISTRATEUR) {
            navigationItems += NavigationItem(
                    label = "Gerer les reclamations",
                    icon = Icons.Default.Edit,
                    selected = selectedNavItem == EnumView.ViewRequestsValidation,
                    onClick = {
                        navController.navigate(ViewRequestsValidation())
                        isPopupOpen = !isPopupOpen
                    },
                    subNavigationItem = listOf(
                        /* Auto config ViewRequestsValidation section subNavigationItems Content*/
                    )
                )
        }
    }
    val navigation = @Composable {
        LazyColumn(
            Modifier.fillMaxHeight().width(300.dp),
        ) {
            /*item {
                Box( Modifier.fillParentMaxWidth().height(300.dp).background(Color.Red)){

                }
            }*/
            item {
                AppNavigationRail(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                ) {
                    navigationItems.forEach {
                        if (!it.enableExpenciveItem) {
                            CustomNavigationRailItem(
                                modifier = it.modifier,
                                selected = if (it.subNavigationItem.isNotEmpty()) it.subNavigationItem.any { it.selected } else it.selected,
                                icon = it.icon,
                                badgeContent = it.badgeContent,
                                //contentColor = it.contentColor,
                                badgeColor = it.badgeColor,
                                badgeTextStyle = it.badgeTextStyle,
                                onClick = { it.onClick }
                            )
                        } else {
                            CustomExpensiveNavItem(
                                modifier = it.modifier,
                                label = it.label,
                                selected = if (it.subNavigationItem.isNotEmpty()) it.subNavigationItem.any { it.selected } else it.selected,
                                icon = it.icon,
                                badgeContent = it.badgeContent,
                                // contentColor = it.contentColor,
                                // badgeColor = it.badgeColor,
                                badgeTextStyle = it.badgeTextStyle,
                                //textStyle = it.textStyle,
                                onClick = it.onClick,
                                subNavItem = if (it.subNavigationItem.isEmpty()) null else {
                                    {
                                        it.subNavigationItem.forEach { item ->
                                            CustomExpensiveNavItem(
                                                modifier = item.modifier,
                                                isSubMenuItem = true,
                                                label = item.label,
                                                selected = item.selected,
                                                icon = item.icon,
                                                badgeContent = item.badgeContent,
                                                // contentColor = item.contentColor,
                                                badgeColor = item.badgeColor,
                                                badgeTextStyle = item.badgeTextStyle,
                                                // textStyle = item.textStyle,
                                                onClick = item.onClick
                                            )
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }

        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            if (isCompactSize && enableNavRail) {
                navigation()
            }
        }
    ) {
        NavigationRailWithPopupDrawer(
            enableNavRail = if (isCompactSize) false else enableNavRail,
            enabledExpensiveMenu = true,
            isPopupOpen = isPopupOpen,
            topBar = {
                if (enableNavRail) {
                    WebDesktopHeaderAppBar(
                        navController,
                        navigationIcon = {
                            if (isCompactSize && enableNavRail) {
                                CustomNavigationRailItem(
                                    contentColor = MaterialTheme.colorScheme.background,
                                    icon = Icons.Default.Menu,
                                ) {
                                    scope.launch {
                                        if (drawerState.isOpen) {
                                            drawerState.close()
                                        } else {
                                            drawerState.open()
                                        }
                                    }
                                    isPopupOpen = !isPopupOpen
                                }
                            }
                        }
                    )

                }

                /*  { Row(
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
                   }   }*/

            },
            onClickFloatingActionButton = {
                navController.navigate(ViewHome(isConnected = true))
            },
            navigationItems = navigationItems,
            navigationContent = navigation
        ) {
            val isCompactSize = isCompactMobilePlatform()
            AppNavigation(
                modifier = modifier,
                navController = navController,
                startDestination = if (isCompactSize) ViewSplash() else ViewHome()
            ) { enumView ->
                selectedNavItem = enumView
                enableNavRail = EnumView.entries.filter { it == enumView }
                    .any { it.includeInDashboard } || if (isCompactSize) enumView == EnumView.ViewHome else false
            }
        }
    }


}


@Composable
fun AppMobileNavigation(
    modifier: Modifier,
    navController: NavHostController,
    appScrollState: ScrollState
) {

    ModalNavigationDrawer(
        drawerContent = {

        }
    ) {
        val isCompactMobilePlatform = isCompactMobilePlatform()
        NavHost(
            navController,
            modifier = modifier,
            startDestination = ViewSplash(),
        ) {

        }

    }
}


/*Auto generate EnumView*/
enum class EnumView(val includeInDashboard: Boolean = false) {
    /*Auto generate EnumView content*/
    ViewSuccessfulAuth,
    ViewAllRealEstate,
    ViewOwnerCertifier,
    ViewClaims,
    ViewRequestsValidation(true),
    ViewCreateAccount,
    ViewMessenger(true),
    ViewMobileDashBoard,
    ViewSplash,
    ViewPropertyALLProperty(true),
    ViewPropertyAddProperty(true),
    ViewProfil(true),
    ViewProperty(true),
    ViewStatistics(true),
    ViewChat(false),
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
    ViewConversations;

    fun getPageTitle(): String {
        val withoutPrefix = this.name.removePrefix("View")
        // 2. Séparer selon les majuscules (en gardant les mots entiers)
        val words = withoutPrefix.split(Regex("(?=[A-Z])"))
        // 3. Filtrer les chaînes vides éventuelles
        return words.filter { it.isNotEmpty() }.joinToString(" ")
    }
}
