 package empire.digiprem.presentation.views

   import androidx.compose.foundation.layout.Row
   import androidx.compose.foundation.layout.fillMaxSize
   import androidx.compose.foundation.layout.size
   import androidx.compose.material.icons.Icons
   import androidx.compose.material.icons.filled.ArrowBack
   import androidx.compose.material.icons.filled.Edit
   import androidx.compose.material.icons.filled.Loop
   import androidx.compose.material3.Icon
   import androidx.compose.material3.IconButton
   import androidx.compose.material3.MaterialTheme
   import androidx.compose.material3.Scaffold
   import empire.digiprem.navigation.ViewProfil
  import androidx.compose.runtime.Composable
  import androidx.compose.runtime.collectAsState
  import androidx.lifecycle.viewmodel.compose.viewModel
  import androidx.compose.runtime.getValue
   import androidx.compose.ui.Alignment
   import androidx.compose.ui.Modifier
   import androidx.compose.ui.unit.dp
   import org.koin.compose.viewmodel.koinViewModel
  import androidx.navigation.NavHostController
   import empire.digiprem.config.isCompactMobilePlatform
   import empire.digiprem.presentation.components.AppHeader
   import empire.digiprem.presentation.components.AppIconActionButton
   import empire.digiprem.presentation.components.AsyncImageSection
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

     Scaffold(
         containerColor = MaterialTheme.colorScheme.background,
         modifier = Modifier.fillMaxSize(),
         topBar = {
             if (isCompactMobilePlatform()){
                 AppHeader(
                     navigationIcon = {
                         Row(
                             verticalAlignment = Alignment.CenterVertically
                         ) {
                             IconButton(onClick = {
                                 navController.popBackStack()
                             }
                             ) {
                                 Icon(
                                     imageVector = Icons.Filled.ArrowBack,
                                     contentDescription = ""
                                 )
                             }

                         }

                     }
                 ) {
                     AppIconActionButton(
                         onClick = {
                             // navController.navigate(ViewNotifications())
                         }
                     ) {
                         Icon(
                             imageVector = Icons.Default.Loop,
                             contentDescription = "search",
                         )
                     }
                     AppIconActionButton(
                         onClick = {
                             navController.navigate(ViewProfil())
                         }
                     ) {
                         Icon(
                             imageVector = Icons.Default.Edit,
                             contentDescription = "search",
                         )
                     }
                 }
             }
         }
     ) {

     }
 }