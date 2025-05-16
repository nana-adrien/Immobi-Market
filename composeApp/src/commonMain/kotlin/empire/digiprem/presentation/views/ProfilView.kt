 package empire.digiprem.presentation.views

   import empire.digiprem.navigation.ViewProfil
  import androidx.compose.runtime.Composable
  import androidx.compose.runtime.collectAsState
  import androidx.lifecycle.viewmodel.compose.viewModel
  import androidx.compose.runtime.getValue
  import org.koin.compose.viewmodel.koinViewModel
  import androidx.navigation.NavHostController
 import empire.digiprem.presentation.viewmodels.ProfilViewModel
import empire.digiprem.presentation.intents.ProfilIntent
        
 @Composable
 fun ProfilView(
 viewProfil:ViewProfil,
 navController: NavHostController,
 profilViewModel:ProfilViewModel = koinViewModel()
 ) {
        // val profilViewModel:ProfilViewModel = viewModel{ProfilViewModel()}
         val state by profilViewModel.state.collectAsState()
         val onSendIntent=profilViewModel::onIntentHandler
 }