package empire.digiprem.core.di


import androidx.navigation.NavController
import empire.digiprem.app.service.JwtTokenService
import empire.digiprem.data.remote.config.HttpConstants
import empire.digiprem.data.remote.config.KtorfitServiceCreator
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


 val commonModules = module {
  viewModel { CompleteAccountViewModel() }
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
    viewModel {(navController: NavController) ->   VerifyIdentityViewModel(navController) }
    viewModel { ResetPasswordViewModel() }
    viewModel { ForgotPasswordViewModel() }
    viewModel {(navController: NavController) ->  RegisterViewModel(navController)  }
    viewModel { StatisticsViewModel() }
    viewModel { LoginViewModel() }
    viewModel { ChatViewModel() }
    viewModel { HomeViewModel() }
    viewModel { SettingsViewModel() }
    viewModel { NotificationsViewModel() }
}

val appModule= module {
    single<IJwtTokenService>{JwtTokenService(get()) }
    single <KtorfitServiceCreator>{ KtorfitServiceCreator(HttpConstants.BASE_URL,get())}
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