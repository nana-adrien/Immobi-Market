 package empire.digiprem.presentation.views

   import androidx.compose.foundation.background
   import androidx.compose.foundation.layout.*
   import androidx.compose.foundation.rememberScrollState
   import androidx.compose.foundation.shape.RoundedCornerShape
   import androidx.compose.foundation.verticalScroll
   import androidx.compose.material.icons.Icons
   import androidx.compose.material.icons.filled.MoreHoriz
   import androidx.compose.material3.*
   import empire.digiprem.navigation.ViewMessenger
  import androidx.compose.runtime.Composable
  import androidx.compose.runtime.collectAsState
  import androidx.lifecycle.viewmodel.compose.viewModel
  import androidx.compose.runtime.getValue
   import androidx.compose.ui.Alignment
   import androidx.compose.ui.Modifier
   import androidx.compose.ui.draw.shadow
   import androidx.compose.ui.text.font.FontWeight
   import androidx.compose.ui.unit.dp
   import org.koin.compose.viewmodel.koinViewModel
  import androidx.navigation.NavHostController
   import empire.digiprem.presentation.components.AppVerticalScrollBar
   import empire.digiprem.presentation.components.NavigationApp
   import empire.digiprem.presentation.components.NavigationTypeEnum
   import empire.digiprem.presentation.viewmodels.MessengerViewModel
import empire.digiprem.presentation.intents.MessengerIntent
        
 @Composable
 fun MessengerView(
 viewMessenger:ViewMessenger,
 navController: NavHostController,
 messengerViewModel:MessengerViewModel = koinViewModel(),
 firstContent: @Composable() (() -> Unit?)? = null,
 secondContent: @Composable() (() -> Unit?)? = null
 ) {
        // val messengerViewModel:MessengerViewModel = viewModel{MessengerViewModel()}
         val state by messengerViewModel.state.collectAsState()
         val onSendIntent=messengerViewModel::onIntentHandler

     val scrollState= rememberScrollState(0)
     Box(modifier = Modifier.fillMaxSize()){
         Box(modifier = Modifier.fillMaxSize().verticalScroll(scrollState), contentAlignment = Alignment.Center) {
             Column(modifier = Modifier.padding(50.dp).height(700.dp).width(1000.dp),verticalArrangement = Arrangement.spacedBy(20.dp),) {
                 Box(
                     modifier = Modifier.fillMaxWidth().wrapContentHeight().shadow(elevation = 1.dp, shape = RoundedCornerShape(10.dp))
                         .background(
                             MaterialTheme.colorScheme.background
                         ), contentAlignment = Alignment.Center
                 ) {
                     Column {
                         Row(
                             modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
                             verticalAlignment = Alignment.CenterVertically,
                             horizontalArrangement = Arrangement.SpaceBetween
                         ) {
                             Text(
                                 text = "Notification",
                                 style = MaterialTheme.typography.titleMedium.copy(
                                     color = MaterialTheme.colorScheme.onBackground,
                                     fontWeight = FontWeight.Bold
                                 ),
                             )

                             IconButton(onClick = {}) {
                                 Icon(
                                     imageVector = Icons.Default.MoreHoriz,
                                     contentDescription = null
                                 )
                             }

                         }
                         Row(
                             modifier = Modifier.fillMaxWidth(),
                             verticalAlignment = Alignment.CenterVertically,
                             horizontalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.Start)
                         ) {
                             TextButton(
                                 onClick = {},
                             ) {
                                 Text(
                                     "all"
                                 )
                             }
                             TextButton(
                                 onClick = {},
                             ) {
                                 Text(
                                     "Non lu"
                                 )
                             }
                             TextButton(
                                 onClick = {},
                             ) {
                                 Text(
                                     "voir plus..."
                                 )
                             }
                         }
                     }
                 }
                 Box(
                     modifier = Modifier.fillMaxWidth().fillMaxHeight().shadow(elevation = 1.dp, shape = RoundedCornerShape(10.dp))
                 ) {
                     NavigationApp(
                         NavigationTypeEnum.NAVIGATION_RAIL ,
                         firstContent = firstContent ,
                         secondContent = secondContent
                     )
                 }
             }
         }
         AppVerticalScrollBar(
             modifier = Modifier.align(Alignment.TopEnd),
             scrollState = scrollState

         )
     }
 }