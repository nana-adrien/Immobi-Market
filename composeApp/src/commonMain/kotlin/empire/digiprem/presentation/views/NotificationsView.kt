package empire.digiprem.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import empire.digiprem.model.NotificationItem
import empire.digiprem.navigation.EnumView
import empire.digiprem.navigation.ViewNotifications
import empire.digiprem.presentation.components.wrapper.PageWrapperState
import empire.digiprem.presentation.components.wrapper.WebDesktopPageWrapper
import empire.digiprem.presentation.viewmodels.NotificationsViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsView(
    viewNotifications: ViewNotifications,
    navController: NavHostController,
    notificationsViewModel: NotificationsViewModel = koinViewModel()
) {
    // val notificationsViewModel:NotificationsViewModel = viewModel{NotificationsViewModel()}
    val state by notificationsViewModel.state.collectAsState()
    val onSendIntent = notificationsViewModel::onIntentHandler
    WebDesktopPageWrapper(
        modifier = Modifier,
        view = EnumView.ViewNotifications,
        state = PageWrapperState(isSuccess = true)
    ) {
        NotificationScreenPreview()
    }



   /* val stateE = rememberLazyListState()
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
            *//* item {
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

             }*//*
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
        *//*AppVerticalScrollBar(
            modifier = Modifier.align(Alignment.CenterEnd),
            lazyListState = state
        )*//*
    }*/
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    notifications: List<NotificationItem>,
    onDeleteSelected: (List<NotificationItem>) -> Unit
) {
    var selectedItems by remember {mutableStateOf(setOf<String>()) }

    val isSelectionMode = selectedItems.isNotEmpty()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (isSelectionMode) {
                        Text("${selectedItems.size} selected")
                    } else {
                        Text("Notifications")
                    }
                },
                actions = {
                    if (isSelectionMode) {
                        IconButton(onClick = {
                            val toDelete = notifications.filter { selectedItems.contains(it.id) }
                            onDeleteSelected(toDelete)
                            selectedItems = emptySet()
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                },
                navigationIcon = {
                    if (isSelectionMode) {
                        IconButton(onClick = { selectedItems = emptySet() }) {
                            Icon(Icons.Default.Close, contentDescription = "Cancel")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            items(notifications) { notification ->
                val isSelected = selectedItems.contains(notification.id)
                NotificationRow(
                    notification = notification,
                    isSelected = isSelected,
                    onClick = {
                        if (isSelectionMode) {
                            selectedItems = if (isSelected) {
                                selectedItems - notification.id
                            } else {
                                selectedItems + notification.id
                            }
                        }
                    },
                    onLongClick = {
                        selectedItems = selectedItems + notification.id
                    }
                )
            }
        }
    }
}


@Composable
fun NotificationRow(
    notification: NotificationItem,
    isSelected: Boolean = false,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .background(if (isSelected) Color(0xFFE0F7FA) else Color.Transparent)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(notification.title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(notification.body, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(notification.time, fontSize = 12.sp, color = Color.LightGray)
        }
    }
}

@Composable
fun NotificationScreenPreview() {
    val sampleNotifications = listOf(
        NotificationItem("1", "New Message", "You have a new message from John", "5 mins ago"),
        NotificationItem("2", "App Update", "Version 2.0 is available", "1 hour ago"),
        NotificationItem("3", "Reminder", "Meeting at 3PM today", "Yesterday")
    )
    NotificationScreen(notifications = sampleNotifications, onDeleteSelected = {})
}
