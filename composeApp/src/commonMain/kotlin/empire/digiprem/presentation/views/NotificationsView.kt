 package empire.digiprem.presentation.views

   import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.images
import empire.digiprem.navigation.ViewNotifications
import empire.digiprem.presentation.viewmodels.NotificationsViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

 @Composable
 fun NotificationsView(
 viewNotifications:ViewNotifications,
 navController: NavHostController,
 notificationsViewModel:NotificationsViewModel = koinViewModel()
 ) {
        // val notificationsViewModel:NotificationsViewModel = viewModel{NotificationsViewModel()}
         val state by notificationsViewModel.state.collectAsState()
         val onSendIntent=notificationsViewModel::onIntentHandler

     val stateE= rememberLazyListState()
     Box(
         modifier = Modifier.fillMaxSize()
     ) {
         LazyColumn(
             state = stateE,
         ) {
             /* item {
                  Column {
                      Row(
                          modifier = Modifier.fillParentMaxWidth().padding(start = 10.dp, end = 10.dp),
                          verticalAlignment = Alignment.CenterVertically,
                          horizontalArrangement = Arrangement.SpaceBetween
                      ) {
                          Text(
                              text = "Notification",
                              style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.Bold),
                          )

                          IconButton(onClick = {}) {
                              Icon(
                                  imageVector = Icons.Default.MoreHoriz,
                                  contentDescription = null
                              )
                          }

                      }
                      Row(
                          modifier = Modifier.fillParentMaxWidth(),
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

              }*/
             val text =
                 "Oui, Kotlin Multiplatform (KMP) prend en charge les flow grâce à Kotlin Coroutines, qui est entièrement compatible avec les plateformes cibles, y compris Android, iOS et JavaScript.\n" +
                         "\n" +
                         "Sur Kotlin/Native (pour iOS), vous pouvez utiliser Flow de la même manière que sur Android, bien qu'il y ait quelques petites considérations liées à la gestion des threads et à l'interopérabilité avec le framework iOS."

             items(count = 10) {
                 ListItem(
                     modifier = Modifier.fillParentMaxWidth().clickable {  },
                     headlineContent = { Text("bonjour le monde ") },
                     supportingContent = {
                         Text(text = text, maxLines = 3, overflow = TextOverflow.Ellipsis)
                     },
                     leadingContent = {
                         Image(
                             modifier = Modifier.size(50.dp),
                             painter = painterResource(Res.drawable.images),
                             contentDescription = null
                         )
                     },)
             }
         }
         /*AppVerticalScrollBar(
             modifier = Modifier.align(Alignment.CenterEnd),
             lazyListState = state
         )*/
     }
 }