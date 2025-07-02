package empire.digiprem.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import empire.digiprem.navigation.ViewStatistics
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import androidx.navigation.NavHostController
import empire.digiprem.navigation.EnumView
import empire.digiprem.presentation.components.wrapper.PageWrapperState
import empire.digiprem.presentation.components.wrapper.WebDesktopPageWrapper
import empire.digiprem.presentation.viewmodels.StatisticsViewModel


@OptIn(ExperimentalMaterial3Api::class)
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
        state = PageWrapperState(isSuccess = true)
    ){
        StatisticsScreen()
    }
}


data class StatCardItem(
    val icon: ImageVector,
    val title: String,
    val value: String,
    val backgroundColor: Color
)

@Composable
fun StatCard(item: StatCardItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .aspectRatio(1.2f),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = item.backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.value,
                style = MaterialTheme.typography.headlineSmall.copy(color = Color.White)
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
            )
        }
    }
}

@Composable
fun StatisticsGrid(items: List<StatCardItem>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items.size) { index ->
            StatCard(item = items[index])
        }
    }
}

@Composable
fun StatisticsScreen() {
    val stats = listOf(
        StatCardItem(Icons.Default.Home, "Biens publiés", "12", Color(0xFF4CAF50)),
        StatCardItem(Icons.Default.HourglassBottom, "En attente", "5", Color(0xFFFFA726)),
        StatCardItem(Icons.Default.Notifications, "Notifications non lues", "3", Color(0xFF42A5F5)),
        StatCardItem(Icons.Default.Inventory, "Total des biens", "20", Color(0xFF7E57C2)),
        StatCardItem(Icons.Default.CheckCircle, "Biens acceptés", "15", Color(0xFF66BB6A)),
        StatCardItem(Icons.Default.Cancel, "Biens refusés", "2", Color(0xFFEF5350))
    )

    Column {
        Text(
            text = "Tableau de bord",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineSmall
        )
        StatisticsGrid(items = stats)
    }
}

