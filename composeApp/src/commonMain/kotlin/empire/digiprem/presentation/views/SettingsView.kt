 package empire.digiprem.presentation.views

   import empire.digiprem.navigation.ViewSettings
  import androidx.compose.runtime.Composable
  import androidx.compose.runtime.collectAsState
  import androidx.lifecycle.viewmodel.compose.viewModel
  import androidx.compose.runtime.getValue
  import org.koin.compose.viewmodel.koinViewModel
  import androidx.navigation.NavHostController
 import empire.digiprem.presentation.viewmodels.SettingsViewModel
import empire.digiprem.presentation.intents.SettingsIntent
        
 @Composable
 fun SettingsView(
 viewSettings:ViewSettings,
 navController: NavHostController,
 settingsViewModel:SettingsViewModel = koinViewModel()
 ) {
        // val settingsViewModel:SettingsViewModel = viewModel{SettingsViewModel()}
         val state by settingsViewModel.state.collectAsState()
         val onSendIntent=settingsViewModel::onIntentHandler
 }