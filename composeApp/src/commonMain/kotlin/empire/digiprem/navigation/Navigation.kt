package empire.digiprem.navigation


import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import androidx.savedstate.SavedState
import androidx.savedstate.read
import androidx.savedstate.write
import empire.digiprem.core.utils.getName
import empire.digiprem.presentation.components.*
import empire.digiprem.presentation.views.AuthenticateScreen.*
import empire.digiprem.ui.Screen.dashboard_screen.DashboardNavigation
import empire.digiprem.ui.Screen.dashboard_screen.HomeScreen
import empire.digiprem.ui.Screen.dashboard_screen.screens.AnnonceSuiviScreen
import empire.digiprem.ui.Screen.dashboard_screen.screens.MessagesScreen
import empire.digiprem.ui.Screen.dashboard_screen.screens.NotificationsScreen
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

object PageTransitionAnimation {
    val enterTransition = slideInHorizontally(
        animationSpec = tween(
            durationMillis = 600,
            delayMillis = 90,
            easing = LinearOutSlowInEasing
        ), initialOffsetX = { it }
    )
    val enterTransition2 = slideInHorizontally(
        animationSpec = tween(
            durationMillis = 600,
            delayMillis = 40,
            easing = LinearOutSlowInEasing
        ), initialOffsetX = { it }
    )
    val enterTransition3 = slideInHorizontally(
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 30,
            easing = LinearOutSlowInEasing
        ), initialOffsetX = { -it / 2 }
    )
    val exitTransition = fadeOut()
    val popExitTransition2 = slideOutHorizontally(
        animationSpec = tween(
            durationMillis = 600,
            delayMillis = 30,
            easing = LinearOutSlowInEasing
        ), targetOffsetX = { -it })
    val popExitTransition = slideOutHorizontally(
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 90,
            easing = LinearOutSlowInEasing
        ), targetOffsetX = { it })
}


@Serializable
enum class SectionEnum {
    annonces,
    notifications,
    chat,
    annonceParcourrue
}


@Serializable
data class Dashboard(val section: String)

@Serializable
sealed class Dashboard2 {
}

@Serializable
@SerialName("dashboard/annonces")
object annonces

@Serializable
@SerialName("dashboard/notifications")
object notifications

@Serializable
@SerialName("dashboard/chat")
data class chat(val message: String="", val content: String="")

@Serializable
@SerialName("dashboard/annonceParcourrue")
object annonceParcourrue



@Serializable
object Dsh_AnnonceParcourrue

object Dsb_AnnonceParcourrue_Annonce

@Serializable
@SerialName("dashboard/Myrealestate")
object MyRealEstate


@Serializable
object login

@Serializable
object forgotPassword


@Serializable
data class DetailRealEstate(val isConnected: Boolean = false, val productItem: String = "", val scrollPosition: Int = 0)


@Serializable
object verifyIdentity

@Serializable
object signUp

@Serializable
object resetPassword


@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    appScrollState: ScrollState,
) {
    var activeTopBarAction by remember { mutableStateOf(TopBarAction()) }
    var enableNavRail by remember { mutableStateOf(false) }

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
    val DashboardNavigation1 = listOf(
        NavigationItem(
            label = "Market Place",
            icon = Icons.Default.ShoppingCart,
            selected = false,
            onClick = {
                navController.navigate(Home(isConnected = true))
                // selectedItem = MarketPlace
            },
            subNavigationItem = listOf(

            )
        ),
        NavigationItem(
            label = "Mes annonces",
            icon = Icons.Default.Tag,
            selected = navController.getRouteName() == SectionEnum.annonceParcourrue.name,
            onClick = {
                navController.navigate(annonces)
            },
            /* subNavigationItem = listOf(
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
                    selected = dashboardState.section == "CreerUneAnnonce",
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
            )*/
        ),
        NavigationItem(
            label = "Notifications",
            icon = Icons.Default.Notifications,
            selected = navController.getRouteName() == SectionEnum.notifications.name,
            onClick = {
                // navController.navigate(dashboardState.copy(section = SectionEnum.notifications.name))
                //    //  selectedItem = DashboardNavigationContainer.Notifications.route
            }
        ),
        NavigationItem(
            label = "Messages",
            icon = Icons.Default.Chat,
            selected = navController.getRouteName() == SectionEnum.chat.name,
            onClick = {
                navController.navigate(annonces)
                //  navController.navigate(dashboardState.copy(section = SectionEnum.chat.name))
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
    val listRoute = listOf(
        annonces.getName(),
        notifications.getName(),
        chat.getName(),
        annonceParcourrue.getName()
    )
    Box(modifier = Modifier.fillMaxSize()) {
        NavigationRailWithPopupDrawer(
            enableNavRail = enableNavRail,
            enabledExpensiveMenu = true,
            isPopupOpen = true,
            topBar = {
                AppHeader {
                    TextButton(
                        colors = ButtonDefaults.textButtonColors()
                            .copy(contentColor = Color.White, containerColor = MaterialTheme.colorScheme.primary),
                        onClick = {
                            println("currentDestination=${navController.currentDestination},route=${navController.getRouteName()},Dashboard2.annonces.getName()=${Home.getName()},listRoute=${listRoute},")
                        }) {
                        Text("actived")
                    }
                }
            },
            navigationItems = DashboardNavigation1
        ) {
            NavHost(
                navController,
                modifier = modifier,
                startDestination = Home(),
            ) {
                composable<Home> {
                    enableNavRail = listRoute.contains(Home.getName())
                    val homeState = it.toRoute<Home>()
                    HomeScreen(modifier = Modifier, navController = navController, homeState = homeState)
                }
                composable<login> {
                    enableNavRail = listRoute.contains(login.getName())
                    LoginScreen(navController)
                }
                composable<signUp> {
                    enableNavRail = listRoute.contains(signUp.getName())
                    RegisterScreen(navController)
                }
                composable<forgotPassword> {
                    enableNavRail = listRoute.contains(forgotPassword.getName())
                    ForgotPasswordScreen(navController)
                }
                composable<verifyIdentity> {
                    enableNavRail = listRoute.contains(verifyIdentity.getName())
                    VerifyIdentityScreen(navController)
                }
                composable<resetPassword> {
                    enableNavRail = listRoute.contains(resetPassword.getName())
                    ResetPasswordScreen(navController)
                }
                composable<Dashboard>(
                    //   typeMap = mapOf(typeOf<SectionEnum>() to DashboardStateNavType)
                ) {
                    enableNavRail = listRoute.contains(Dashboard.getName())
                    val dashboardState = it.toRoute<Dashboard>()
                    DashboardNavigation(navController, dashboardState = dashboardState)
                }

                composable<notifications> {
                    enableNavRail = listRoute.contains(notifications.getName())
                    NotificationsScreen()
                }
                composable<chat> {
                    enableNavRail = listRoute.contains(chat.getName())
                    val chatState = it.toRoute<chat>()
                    MessagesScreen(
                        NavigationTypeEnum.NAVIGATION_RAIL,
                        navController,
                        chatState= if (chatState.message.isEmpty()) null else chatState
                    )
                }
                composable<annonces> {
                    enableNavRail = listRoute.contains(annonces.getName())
                    AnnonceSuiviScreen()
                }
                composable<annonces> {
                    enableNavRail = listRoute.contains(annonces.getName())

                //    DetailRealEstateItemScreen(navController, realEstateData = ,null)
                }
                composable<MyRealEstate> {
                    Box(modifier = Modifier.fillMaxSize().background(Color.Red))
                }
               /* composable<Dashboard2.annonces> {
                    CreerUneAnnonce()
                }*/
                /*composable< Dashboard2.notifications> {
                    Box(modifier = Modifier.fillMaxSize().background(Color.Blue))
                }*/

            }
        }
    }
}
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppAuthenticationNavigation(
modifier: Modifier = Modifier,
appNavController: NavHostController,
navController: NavHostController,
appScrollState: ScrollState,
) {

Box(
Modifier.then(modifier).background(Color.White),
contentAlignment = Alignment.Center
) {
Image(
modifier = Modifier.fillMaxSize(),
painter = painterResource(Res.drawable.background_immeuble),
contentScale = ContentScale.Crop,
contentDescription = null,
)
Box(
modifier = Modifier.matchParentSize().verticalScroll(appScrollState)
) {
TopAppBar(
modifier = Modifier.align(Alignment.TopStart).height(60.dp),
colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.Transparent),
navigationIcon = {
    Image(
        painter = painterResource(Res.drawable.images),
        contentDescription = ""
    )
},
title = {},
actions = {
    TextButton(onClick = {}) {
        Text("Language")
        Icon(Icons.Filled.ArrowDropDown, contentDescription = "")
    }
}
)

NavHost(
navController,
modifier = Modifier.fillMaxSize().align(Alignment.Center).padding(top = 50.dp),
startDestination = Screen.Login.route,
popExitTransition = { PageTransitionAnimation.popExitTransition },
enterTransition = { PageTransitionAnimation.enterTransition }
) {
composable(
    route = Screen.Login.route,
    popExitTransition = { PageTransitionAnimation.exitTransition },
    popEnterTransition = { PageTransitionAnimation.enterTransition }
) {
    LoginScreen(
        navController,
        appNavController
    )
}
composable(
    route = Screen.Signup.route,
    popExitTransition = { PageTransitionAnimation.exitTransition },
    popEnterTransition = { PageTransitionAnimation.enterTransition }
) {
    RegisterScreen(navController)
}
composable(
    route = Screen.ForgotPassword.route,
    popExitTransition = { PageTransitionAnimation.exitTransition },
    popEnterTransition = { PageTransitionAnimation.enterTransition }
) {
    ForgotPasswordScreen(navController)
}
composable(
    route = Screen.Veri,
    popExitTransition = { PageTransitionAnimation.exitTransition },
    popEnterTransition = { PageTransitionAnimation.enterTransition }
) {
    VerifyIdentityScreen(navController)
}
composable(
    route = Screen.ResetPassword.route,
    popExitTransition = { PageTransitionAnimation.exitTransition },
    popEnterTransition = { PageTransitionAnimation.enterTransition }
) {
    ResetPasswordScreen(navController)
}
}
}
AppVerticalScrollBar(modifier = Modifier.align(Alignment.TopEnd), scrollState = appScrollState)
}


}*/

/*
val DashboardStateNavType = object : NavType<DasboardSection>(isNullableAllowed = false) {
override fun get(bundle: SavedState, key: String): DasboardSection? {
val json = bundle.read { getString(key) }
return json.let { Json.decodeFromString(it) }
}

override fun parseValue(value: String): DasboardSection {
return Json.let { Json.decodeFromString(value) }
}

override fun put(bundle: SavedState, key: String, value: DasboardSection) {
val json = Json.encodeToString<DasboardSection>(value)
bundle.write { putString(key, json) }
}

}

*/

val DashboardStateNavType = object : NavType<SectionEnum>(isNullableAllowed = false) {
    override fun get(bundle: SavedState, key: String): SectionEnum? {
        val json = bundle.read { getString(key) }
        return json.let { Json.decodeFromString(it) }
    }

    override fun parseValue(value: String): SectionEnum {
        return Json.let { Json.decodeFromString(value) }
    }

    override fun put(bundle: SavedState, key: String, value: SectionEnum) {
        val json = Json.encodeToString<SectionEnum>(value)
        bundle.write { putString(key, json) }
    }

}




fun NavHostController.getRouteName(): String? {
    return this.currentDestination?.route?.split("?")?.first()?.split(".")?.last()
}