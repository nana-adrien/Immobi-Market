package empire.digiprem.ui.Screen.base


import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.background_immeuble
import composeApp.src.commonMain.ComposeResources.drawable.images
import empire.digiprem.ui.Screen.AuthenticateScreen.*
import empire.digiprem.ui.Screen.dashboard_screen.HomeScreen
import empire.digiprem.ui.component.AppVerticalScrollBar
import org.jetbrains.compose.resources.painterResource

object PageTransitionAnimation {

    val enterTransition = slideInHorizontally(
        animationSpec = tween(
            durationMillis = 600 ,
            delayMillis = 90 ,
            easing = LinearOutSlowInEasing
        ) , initialOffsetX = { it }
    )
    val enterTransition2 = slideInHorizontally(
        animationSpec = tween(
            durationMillis = 600 ,
            delayMillis = 40 ,
            easing = LinearOutSlowInEasing
        ) , initialOffsetX = { it }
    )
    val enterTransition3 = slideInHorizontally(
        animationSpec = tween(
            durationMillis = 500 ,
            delayMillis = 30 ,
            easing = LinearOutSlowInEasing
        ) , initialOffsetX = { -it / 2 }
    )
    val exitTransition = fadeOut()
    val popExitTransition2 = slideOutHorizontally(
        animationSpec = tween(
            durationMillis = 600 ,
            delayMillis = 30 ,
            easing = LinearOutSlowInEasing
        ) , targetOffsetX = { -it })
    val popExitTransition = slideOutHorizontally(
        animationSpec = tween(
            durationMillis = 500 ,
            delayMillis = 90 ,
            easing = LinearOutSlowInEasing
        ) , targetOffsetX = { it })
}

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Signup : Screen("signup")
    object ForgotPassword : Screen("forgot_password")
    object ResetPassword : Screen("reset_password")
    object VerifyIdentity : Screen("verify_identity")
}
sealed class AppNavigationContainer(val route: String) {
    object Home : Screen("home")
    object Authentication : Screen("Authentication")
}

@Composable
fun AppNavigation(
    modifier: Modifier=Modifier,
    navController: NavHostController,
    appScrollState: ScrollState, ){

    val authenticationNavController= rememberNavController()
    NavHost(
        navController,
        modifier = modifier,
        startDestination = AppNavigationContainer.Home.route,
        popExitTransition = { PageTransitionAnimation.popExitTransition },
        enterTransition = { PageTransitionAnimation.enterTransition }
    ){
        composable(
            route=AppNavigationContainer.Home.route,
            popExitTransition = { PageTransitionAnimation.exitTransition },
            popEnterTransition = { PageTransitionAnimation.enterTransition }
        ){
            HomeScreen(
                modifier = Modifier,
                navController=navController,
                authenticationNavController=authenticationNavController
            )
        }
        composable(
            route=AppNavigationContainer.Authentication.route,
            popExitTransition = { PageTransitionAnimation.exitTransition },
            popEnterTransition = { PageTransitionAnimation.enterTransition }
        ){
            AppAuthenticationNavigation(
                modifier = Modifier,
                appNavController =navController,
                navController =authenticationNavController,
                appScrollState=appScrollState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppAuthenticationNavigation(
    modifier: Modifier=Modifier,
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
            ){
                composable(
                    route=Screen.Login.route,
                    popExitTransition = { PageTransitionAnimation.exitTransition },
                    popEnterTransition = { PageTransitionAnimation.enterTransition }
                ){
                    LoginScreen(
                        navController,
                        appNavController
                    )
                }
                composable(
                    route=Screen.Signup.route,
                    popExitTransition = { PageTransitionAnimation.exitTransition },
                    popEnterTransition = { PageTransitionAnimation.enterTransition }
                ){
                    RegisterScreen(navController)
                }
                composable(
                    route=Screen.ForgotPassword.route,
                    popExitTransition = { PageTransitionAnimation.exitTransition },
                    popEnterTransition = { PageTransitionAnimation.enterTransition }
                ){
                    ForgotPasswordScreen(navController)
                }
                composable(
                    route=Screen.VerifyIdentity.route,
                    popExitTransition = { PageTransitionAnimation.exitTransition },
                    popEnterTransition = { PageTransitionAnimation.enterTransition }
                ){
                    VerifyIdentityScreen(navController)
                }
                composable(
                    route=Screen.ResetPassword.route,
                    popExitTransition = { PageTransitionAnimation.exitTransition },
                    popEnterTransition = { PageTransitionAnimation.enterTransition }
                ){
                    ResetPasswordScreen(navController)
                }
            }
        }
        AppVerticalScrollBar(modifier = Modifier.align(Alignment.TopEnd), scrollState = appScrollState  )
    }



}