 package empire.digiprem.presentation.views

   import empire.digiprem.navigation.ViewOwnerCertifier
  import androidx.compose.runtime.Composable
  import androidx.compose.runtime.collectAsState
  import androidx.lifecycle.viewmodel.compose.viewModel
  import androidx.compose.runtime.getValue
  import org.koin.compose.viewmodel.koinViewModel
  import androidx.navigation.NavHostController
 import empire.digiprem.presentation.viewmodels.OwnerCertifierViewModel
import empire.digiprem.presentation.intents.OwnerCertifierIntent
        
 @Composable
 fun OwnerCertifierView(
 viewOwnerCertifier:ViewOwnerCertifier,
 navController: NavHostController,
 ownercertifierViewModel:OwnerCertifierViewModel = koinViewModel()
 ) {
        // val ownercertifierViewModel:OwnerCertifierViewModel = viewModel{OwnerCertifierViewModel()}
         val state by ownercertifierViewModel.state.collectAsState()
         val onSendIntent=ownercertifierViewModel::onIntentHandler
 }