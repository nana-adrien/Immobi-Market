package empire.digiprem.presentation.views.Authentication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import empire.digiprem.navigation.ViewSuccessfulAuth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import androidx.navigation.NavHostController
import empire.digiprem.config.Platform
import empire.digiprem.config.getPlatform
import empire.digiprem.data.local.DataBaseTemp
import empire.digiprem.presentation.intents.SuccessfulAuthIntent
import empire.digiprem.presentation.viewmodels.SuccessfulAuthViewModel
import empire.digiprem.presentation.viewmodels.componenet.SessionManager
import empire.digiprem.presentation.views.Authentication.base.AuthenticationPageWrapper
import io.ktor.websocket.*
import octopusfx.client.mobile.core.ui.theme.LocalSessionManager
import org.koin.core.parameter.parametersOf

@Composable
fun SuccessfulAuthView(
    viewSuccessfulAuth: ViewSuccessfulAuth,
    navController: NavHostController,
    sessionManager: SessionManager = LocalSessionManager.current,
    successfulauthViewModel: SuccessfulAuthViewModel = koinViewModel { parametersOf(navController, sessionManager) }
) {
    // val successfulauthViewModel:SuccessfulAuthViewModel = viewModel{SuccessfulAuthViewModel()}
    val state by successfulauthViewModel.state.collectAsState()
    val pageState by successfulauthViewModel.pageWrapperState.collectAsState()
    val onSendIntent = successfulauthViewModel::onIntentHandler

    AuthenticationPageWrapper(
        navController = navController
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            Text(
                text = "Opération réussie !!",
                color = MaterialTheme.colorScheme.background,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = "Bienvenue sur IMMOBI-MARKET !",
                color = MaterialTheme.colorScheme.background,
                style = MaterialTheme.typography.bodyLarge
            )

            AnimatedVisibility(
                (getPlatform() == Platform.DESKTOP || getPlatform() == Platform.WEB) && pageState.errorMessage.isNotEmpty()
            ) {
                FormErrorMessageSection(
                    enabled = true,
                    errorMessage = pageState.errorMessage,
                )
            }

            if (state.enabledContinueButton) {
                AppFormButton(
                    onClick = { onSendIntent(SuccessfulAuthIntent.OnContinue) },
                    enabled = !pageState.isLoading,
                    enabledProgressIndicator = pageState.isLoading,
                    label = "Réessayer",
                )
            } else {
                CircularProgressIndicator(Modifier.size(50.dp), color = MaterialTheme.colorScheme.primary)
            }
        }

    }
}