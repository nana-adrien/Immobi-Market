package empire.digiprem.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.compose_multiplatform
import composeApp.src.commonMain.ComposeResources.drawable.images
import empire.digiprem.config.isCompactMobilePlatform
import empire.digiprem.config.isCompactPlatform
import empire.digiprem.navigation.ViewNotifications
import empire.digiprem.navigation.ViewProfil
import empire.digiprem.presentation.components.*
import empire.digiprem.presentation.viewmodels.NotificationsViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NotificationsView(
    viewNotifications: ViewNotifications,
    navController: NavHostController,
    notificationsViewModel: NotificationsViewModel = koinViewModel()
) {
    // val notificationsViewModel:NotificationsViewModel = viewModel{NotificationsViewModel()}
    val state by notificationsViewModel.state.collectAsState()
    val onSendIntent = notificationsViewModel::onIntentHandler

    val stateE = rememberLazyListState()
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
                        AsyncImageSection(
                            online = true,
                            model = "https://lh3.googleusercontent.com/a/ACg8ocLM-v1DuVykbTszaWgG5NWBl2J2n9iIFi7MStQ08_z_prjXdg=s96-c",
                            contentDescription = "", modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(it),
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
                    modifier = Modifier.fillParentMaxWidth().clickable { },
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
                    },
                )
            }
        }
        /*AppVerticalScrollBar(
            modifier = Modifier.align(Alignment.CenterEnd),
            lazyListState = state
        )*/
    }
}