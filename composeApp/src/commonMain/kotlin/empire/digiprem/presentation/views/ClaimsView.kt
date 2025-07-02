 package empire.digiprem.presentation.views

   import empire.digiprem.navigation.ViewClaims
  import androidx.compose.runtime.Composable
  import androidx.compose.runtime.collectAsState
  import androidx.lifecycle.viewmodel.compose.viewModel
  import androidx.compose.runtime.getValue
  import org.koin.compose.viewmodel.koinViewModel
  import androidx.navigation.NavHostController
 import empire.digiprem.presentation.viewmodels.ClaimsViewModel
import empire.digiprem.presentation.intents.ClaimsIntent
        
 @Composable
 fun ClaimsView(
 viewClaims:ViewClaims,
 navController: NavHostController,
 claimsViewModel:ClaimsViewModel = koinViewModel()
 ) {
        // val claimsViewModel:ClaimsViewModel = viewModel{ClaimsViewModel()}
         val state by claimsViewModel.state.collectAsState()
         val onSendIntent=claimsViewModel::onIntentHandler
 }