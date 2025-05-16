 package empire.digiprem.presentation.views

   import empire.digiprem.navigation.ViewStatistics
  import androidx.compose.runtime.Composable
  import androidx.compose.runtime.collectAsState
  import androidx.lifecycle.viewmodel.compose.viewModel
  import androidx.compose.runtime.getValue
  import org.koin.compose.viewmodel.koinViewModel
  import androidx.navigation.NavHostController
 import empire.digiprem.presentation.viewmodels.StatisticsViewModel
import empire.digiprem.presentation.intents.StatisticsIntent
        
 @Composable
 fun StatisticsView(
 viewStatistics:ViewStatistics,
 navController: NavHostController,
 statisticsViewModel:StatisticsViewModel = koinViewModel()
 ) {
        // val statisticsViewModel:StatisticsViewModel = viewModel{StatisticsViewModel()}
         val state by statisticsViewModel.state.collectAsState()
         val onSendIntent=statisticsViewModel::onIntentHandler
 }