package empire.digiprem.ui.Screen.dashboard_screen.screens.notifications

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import composeApp.src.commonMain.ComposeResources.drawable.IM_Plan_de_travail_1
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.images
import empire.digiprem.presentation.components.AppVerticalScrollBar
import empire.digiprem.presentation.views.NotificationItem
import org.jetbrains.compose.resources.painterResource

@Composable
fun Notifications(notifications: List<NotificationItem> = emptyList()) {
    val state= rememberLazyListState()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            state = state,
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

            items(notifications) {
                ListItem(
                    modifier = Modifier.fillParentMaxWidth().background(if (it.isRead) Color.Transparent else MaterialTheme.colorScheme.secondary.copy(0.2f)).clickable {  },
                    headlineContent = { Text(it.title) },
                    supportingContent = {
                        Text(text = it.body, maxLines = 3, overflow = TextOverflow.Ellipsis)
                    },
                    leadingContent = {
                        Image(
                            modifier = Modifier.size(50.dp),
                            painter = painterResource(Res.drawable.IM_Plan_de_travail_1),
                            contentDescription = null
                        )
                    },)
            }

        }
        AppVerticalScrollBar(
            modifier = Modifier.align(Alignment.CenterEnd),
            lazyListState = state
        )
    }

}