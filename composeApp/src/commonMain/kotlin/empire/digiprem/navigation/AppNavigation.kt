package empire.digiprem.navigation

/*@Auto import file*/
import empire.digiprem.presentation.views.Authentication.SuccessfulAuthView
import empire.digiprem.presentation.views.ClaimsView
import empire.digiprem.presentation.views.OwnerCertifierView
import empire.digiprem.presentation.views.AllRealEstateView
import empire.digiprem.presentation.views.RequestsValidationView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import empire.digiprem.config.isCompactMobilePlatform
import empire.digiprem.presentation.views.*
import empire.digiprem.presentation.views.Authentication.*

/*@Auto Generate*/
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Any,
    onNavigate: (EnumView) -> Unit
) {
    val isCompactMobilePlatform = isCompactMobilePlatform()
    NavHost(
        navController,
        modifier = modifier,
        startDestination = startDestination,
    ) {
        /*@Auto Generate Composable*/
        composable<ViewSuccessfulAuth> {
            onNavigate(EnumView.ViewSuccessfulAuth)
            val viewSuccessfulAuth = it.toRoute<ViewSuccessfulAuth>()
            SuccessfulAuthView(viewSuccessfulAuth = viewSuccessfulAuth, navController = navController)
        }
        composable<ViewClaims> {
            onNavigate(EnumView.ViewClaims)
            val viewClaims = it.toRoute<ViewClaims>()
            ClaimsView(viewClaims = viewClaims, navController = navController)
        }
        composable<ViewOwnerCertifier> {
            onNavigate(EnumView.ViewOwnerCertifier)
            val viewOwnerCertifier = it.toRoute<ViewOwnerCertifier>()
            OwnerCertifierView(viewOwnerCertifier = viewOwnerCertifier, navController = navController)
        }
        composable<ViewAllRealEstate> {
            onNavigate(EnumView.ViewAllRealEstate)
            val viewAllRealEstate = it.toRoute<ViewAllRealEstate>()
            AllRealEstateView(viewAllRealEstate = viewAllRealEstate, navController = navController)
        }
        composable<ViewRequestsValidation> {
            onNavigate(EnumView.ViewRequestsValidation)
            val viewRequestsValidation = it.toRoute<ViewRequestsValidation>()
            RequestsValidationView(viewRequestsValidation = viewRequestsValidation, navController = navController)
        }
        composable<ViewCompleteAccount> {
            onNavigate(EnumView.ViewCreateAccount)
            val viewCompleteAccount = it.toRoute<ViewCompleteAccount>()
            CompleteAccountView(viewCompleteAccount = viewCompleteAccount, navController = navController)
        }
        if (isCompactMobilePlatform) {
            composable<ViewMobileDashBoard> {
                onNavigate(EnumView.ViewMobileDashBoard)
                val viewMobileDashBoard = it.toRoute<ViewMobileDashBoard>()
                MobileDashBoardView(viewMobileDashBoard = viewMobileDashBoard, navController = navController)
            }
            composable<ViewSplash> {
                onNavigate(EnumView.ViewSplash)
                val viewSplash = it.toRoute<ViewSplash>()
                SplashView(viewSplash = viewSplash, navController = navController)
            }
            composable<ViewConversations> {
                onNavigate(EnumView.ViewConversations)
                val viewConversations = it.toRoute<ViewConversations>()
                ConversationsView(viewConversations = viewConversations, navController = navController)
            }
            composable<ViewChat> {
                onNavigate(EnumView.ViewChat)
                val viewChat = it.toRoute<ViewChat>()
                ChatView(
                    viewChat = viewChat,
                    navController = navController,
                )
            }
        }
        composable<ViewPropertyALLProperty> {
            onNavigate(EnumView.ViewPropertyALLProperty)
            val viewPropertyALLProperty = it.toRoute<ViewPropertyALLProperty>()
            PropertyALLPropertyView(viewPropertyALLProperty = viewPropertyALLProperty, navController = navController)
        }
        composable<ViewPropertyAddProperty> {
            onNavigate(EnumView.ViewPropertyAddProperty)
            val viewPropertyAddProperty = it.toRoute<ViewPropertyAddProperty>()
            PropertyAddPropertyView(viewPropertyAddProperty = viewPropertyAddProperty, navController = navController)
        }
        composable<ViewProfil> {
            onNavigate(EnumView.ViewProfil)
            val viewProfil = it.toRoute<ViewProfil>()
            ProfilView(viewProfil = viewProfil, navController = navController)
        }
        composable<ViewNegotiations> {
            onNavigate(EnumView.ViewNegotiations)
            val viewNegotiations = it.toRoute<ViewNegotiations>()
            NegotiationsView(viewNegotiations = viewNegotiations, navController = navController)
        }
        composable<ViewDetailRealEstateItem> {
            onNavigate(EnumView.ViewDetailRealEstateItem)
            val viewDetailRealEstateItem = it.toRoute<ViewDetailRealEstateItem>()
            DetailRealEstateItemView(
                viewDetailRealEstateItem = viewDetailRealEstateItem,
                navController = navController,
            )
        }
        composable<ViewVerifyIdentity> {
            onNavigate(EnumView.ViewVerifyIdentity)
            val viewVerifyIdentity = it.toRoute<ViewVerifyIdentity>()
            VerifyIdentityView(viewVerifyIdentity = viewVerifyIdentity, navController = navController)
        }
        composable<ViewResetPassword> {
            onNavigate(EnumView.ViewResetPassword)
            val viewResetPassword = it.toRoute<ViewResetPassword>()
            ResetPasswordView(viewResetPassword = viewResetPassword, navController = navController)
        }
        composable<ViewForgotPassword> {
            onNavigate(EnumView.ViewForgotPassword)
            val viewForgotPassword = it.toRoute<ViewForgotPassword>()
            ForgotPasswordView(viewForgotPassword = viewForgotPassword, navController = navController)
        }
        composable<ViewRegister> {
            onNavigate(EnumView.ViewRegister)
            val viewRegister = it.toRoute<ViewRegister>()
            RegisterView(viewRegister = viewRegister, navController = navController)
        }
        composable<ViewLogin> {
            onNavigate(EnumView.ViewLogin)
            val viewLogin = it.toRoute<ViewLogin>()
            LoginView(viewLogin = viewLogin, navController = navController)
        }
        composable<ViewNotifications> {
            onNavigate(EnumView.ViewNotifications)
            val viewNotifications = it.toRoute<ViewNotifications>()
            NotificationsView(viewNotifications = viewNotifications, navController = navController)
        }
        composable<ViewMessenger> {
            onNavigate(EnumView.ViewMessenger)
            val viewMessenger = it.toRoute<ViewMessenger>()
            MessengerView(
                viewMessenger = viewMessenger,
                navController = navController,
            )
        }
        if (!isCompactMobilePlatform) {
            composable<ViewProperty> {
                onNavigate(EnumView.ViewProperty)
                val viewProperty = it.toRoute<ViewProperty>()
                PropertyView(viewProperty = viewProperty, navController = navController)
            }
            composable<ViewSettings> {
                onNavigate(EnumView.ViewSettings)
                val viewSettings = it.toRoute<ViewSettings>()
                SettingsView(viewSettings = viewSettings, navController = navController)
            }
            composable<ViewStatistics> {
                onNavigate(EnumView.ViewStatistics)
                val viewStatistics = it.toRoute<ViewStatistics>()
                StatisticsView(viewStatistics = viewStatistics, navController = navController)
            }
            composable<ViewHome> {
                onNavigate(EnumView.ViewHome)
                val viewHome = it.toRoute<ViewHome>()
                HomeView(viewHome = viewHome, navController = navController)
            }
        }

    }
}