 package empire.digiprem.presentation.views

   import empire.digiprem.navigation.ViewNegotiations
  import androidx.compose.runtime.Composable
  import androidx.compose.runtime.collectAsState
  import androidx.lifecycle.viewmodel.compose.viewModel
  import androidx.compose.runtime.getValue
  import org.koin.compose.viewmodel.koinViewModel
  import androidx.navigation.NavHostController
 import empire.digiprem.presentation.viewmodels.NegotiationsViewModel
import empire.digiprem.presentation.intents.NegotiationsIntent
        
 @Composable
 fun NegotiationsView(
 viewNegotiations:ViewNegotiations,
 navController: NavHostController,
 negotiationsViewModel:NegotiationsViewModel = koinViewModel()
 ) {
        // val negotiationsViewModel:NegotiationsViewModel = viewModel{NegotiationsViewModel()}
         val state by negotiationsViewModel.state.collectAsState()
         val onSendIntent=negotiationsViewModel::onIntentHandler
 }