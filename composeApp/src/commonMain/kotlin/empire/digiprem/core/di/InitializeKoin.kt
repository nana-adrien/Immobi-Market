package empire.digiprem.core.di


import empire.digiprem.presentation.viewmodels.*
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

 
import empire.digiprem.presentation.viewmodels.SplashViewModel


 
import empire.digiprem.presentation.viewmodels.MobileDashBoardViewModel


 
import empire.digiprem.presentation.viewmodels.MessengerViewModel


 val commonModules = module {
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
    viewModel { VerifyIdentityViewModel() }
    viewModel { ResetPasswordViewModel() }
    viewModel { ForgotPasswordViewModel() }
    viewModel { RegisterViewModel() }
    viewModel { StatisticsViewModel() }
    viewModel { LoginViewModel() }
    viewModel { ChatViewModel() }
    viewModel { HomeViewModel() }
    viewModel { SettingsViewModel() }
    viewModel { NotificationsViewModel() }
}
expect val initializeModules: Module

fun InitializeKoin(
    config: (KoinApplication.() -> Unit)? = null
) {
    startKoin {
        config?.invoke(this)
        modules(
            listOf(
                commonModules,
                initializeModules
            )
        )

    }


}