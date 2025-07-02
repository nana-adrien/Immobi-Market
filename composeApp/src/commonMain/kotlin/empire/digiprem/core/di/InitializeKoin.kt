package empire.digiprem.core.di


import androidx.navigation.NavController
import empire.digiprem.app.service.JwtTokenService
import empire.digiprem.data.remote.config.HttpConstants
import empire.digiprem.data.remote.config.KtorfitServiceCreator
import empire.digiprem.data.remote.config.provideHttpClient
import empire.digiprem.data.remote.service.OAuthEndPointEndPointService
import empire.digiprem.domain.servives.IJwtTokenService
import empire.digiprem.presentation.viewmodels.*
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

 
import empire.digiprem.presentation.viewmodels.SplashViewModel


 
import empire.digiprem.presentation.viewmodels.MobileDashBoardViewModel


 
import empire.digiprem.presentation.viewmodels.MessengerViewModel


 
import empire.digiprem.presentation.viewmodels.CompleteAccountViewModel
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json



import empire.digiprem.presentation.viewmodels.RequestsValidationViewModel


 
import empire.digiprem.presentation.viewmodels.AllRealEstateViewModel


 
import empire.digiprem.presentation.viewmodels.OwnerCertifierViewModel


 
import empire.digiprem.presentation.viewmodels.ClaimsViewModel


 
import empire.digiprem.presentation.viewmodels.SuccessfulAuthViewModel
import empire.digiprem.presentation.viewmodels.componenet.SessionManager


val commonModules = module {
  viewModel { ClaimsViewModel() }
  viewModel { OwnerCertifierViewModel() }
  viewModel { AllRealEstateViewModel() }
  viewModel { RequestsValidationViewModel() }
  viewModel { MessengerViewModel() }
  viewModel { MobileDashBoardViewModel() }
  viewModel { SplashViewModel() }
  viewModel { PropertyALLPropertyViewModel() }
    viewModel { PropertyAddPropertyViewModel() }
    viewModel { ProfilViewModel() }
    viewModel { PropertyViewModel() }
    viewModel { ConversationsViewModel() }
    viewModel { NegotiationsViewModel() }
    viewModel { DetailRealEstateItemViewModel() }
     viewModel {(navController: NavController,sessionManager: SessionManager) -> SuccessfulAuthViewModel(navController,sessionManager) }
     viewModel {(navController: NavController) -> CompleteAccountViewModel(navController) }
    viewModel {(navController: NavController) ->   VerifyIdentityViewModel(navController) }
     viewModel {(navController: NavController) ->  RegisterViewModel(navController)  }
     viewModel {(navController: NavController) ->  LoginViewModel(navController) }
    viewModel { ResetPasswordViewModel() }
    viewModel { ForgotPasswordViewModel() }
    viewModel { StatisticsViewModel() }
    viewModel { ChatViewModel() }
    viewModel { HomeViewModel() }
    viewModel { SettingsViewModel() }
    viewModel { NotificationsViewModel() }
    viewModel { SessionManager(get(),get()) }
}

val appModule= module {
    single<IJwtTokenService>{JwtTokenService(get()) }
    single<HttpClient>{ provideHttpClient(HttpConstants.BASE_URL,get()) }
    single <KtorfitServiceCreator>{ KtorfitServiceCreator(HttpConstants.BASE_URL,get(),get()) }
    single <OAuthEndPointEndPointService>{OAuthEndPointEndPointService()}
}
expect val initializeModules: Module

fun InitializeKoin(
    config: (KoinApplication.() -> Unit)? = null
) {
    startKoin {
        config?.invoke(this)
        modules(
            listOf(
                appModule,
                commonModules,
                initializeModules
            )
        )

    }


}