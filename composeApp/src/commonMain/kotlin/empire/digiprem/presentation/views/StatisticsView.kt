package empire.digiprem.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import empire.digiprem.navigation.ViewStatistics
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import androidx.navigation.NavHostController
import empire.digiprem.navigation.EnumView
import empire.digiprem.presentation.components.wrapper.PageWrapperState
import empire.digiprem.presentation.components.wrapper.WebDesktopPageWrapper
import empire.digiprem.presentation.viewmodels.StatisticsViewModel


@Composable
fun StatisticsView(
    viewStatistics: ViewStatistics,
    navController: NavHostController,
    statisticsViewModel: StatisticsViewModel = koinViewModel()
) {
    // val statisticsViewModel:StatisticsViewModel = viewModel{StatisticsViewModel()}
    val state by statisticsViewModel.state.collectAsState()
    val onSendIntent = statisticsViewModel::onIntentHandler
    WebDesktopPageWrapper(
        modifier = Modifier,
        view = EnumView.ViewStatistics,
        state = PageWrapperState()
    ){
        Column(
            modifier = Modifier.width(1300.dp).height(1000.dp).background(Color.Red),
        ) {
            /* LazyVerticalGrid(
                 columns = GridCells.Adaptive(200.dp),
                 modifier = Modifier.fillMaxSize(),
                 verticalArrangement = Arrangement.spacedBy(10.dp),
                 horizontalArrangement = Arrangement.spacedBy(10.dp)
             ) {
                 items(
                     count = 4
                 ) {
                     Column(
                         modifier = Modifier.size(200.dp)
                             .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp)),
                     ) {
                         Row(
                             modifier = Modifier.weight(1f).fillMaxWidth().background(Color.White),
                             verticalAlignment = Alignment.CenterVertically,
                             horizontalArrangement = Arrangement.Center,
                         ) {
                             Icon(
                                 imageVector = Icons.Filled.Favorite,
                                 contentDescription = ""
                             )
                         }
                         Row(
                             modifier = Modifier.weight(1f).fillMaxWidth()
                                 .background(MaterialTheme.colorScheme.surfaceVariant),
                             horizontalArrangement = Arrangement.Center,
                         ) {
                             Text("Add Favorie")
                         }
                     }
                 }

             }*/
        }
    }
}

