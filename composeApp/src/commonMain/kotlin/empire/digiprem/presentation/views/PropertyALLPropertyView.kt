 package empire.digiprem.presentation.views

   import empire.digiprem.navigation.ViewPropertyALLProperty
  import androidx.compose.runtime.Composable
  import androidx.compose.runtime.collectAsState
  import androidx.lifecycle.viewmodel.compose.viewModel
  import androidx.compose.runtime.getValue
  import org.koin.compose.viewmodel.koinViewModel
  import androidx.navigation.NavHostController
 import empire.digiprem.presentation.viewmodels.PropertyALLPropertyViewModel
import empire.digiprem.presentation.intents.PropertyALLPropertyIntent
        
 @Composable
 fun PropertyALLPropertyView(
 viewPropertyALLProperty:ViewPropertyALLProperty,
 navController: NavHostController,
 allpropertyViewModel:PropertyALLPropertyViewModel = koinViewModel()
 ) {
        // val allpropertyViewModel:PropertyALLPropertyViewModel = viewModel{PropertyALLPropertyViewModel()}
         val state by allpropertyViewModel.state.collectAsState()
         val onSendIntent=allpropertyViewModel::onIntentHandler
 }