package empire.digiprem.ui.Screen.dashboard_screen.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.octopusfx.mymessenger.ui.screen.ChatScreen
import com.octopusfx.mymessenger.ui.screen.ConversationsScreen
import empire.digiprem.presentation.components.AppVerticalScrollBar
import empire.digiprem.presentation.components.NavigationTypeEnum
import empire.digiprem.navigation.chat


@Composable
fun MessagesScreen(navigationRail: NavigationTypeEnum, navHostController: NavHostController, chatState: chat?) {
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
                        navigationRail ,
                        firstContent = { ConversationsScreen(navigationRail , navHostController) } ,
                        secondContent = { ChatScreen(navigationRail , navHostController , null, chatState) }
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

@Composable
fun NavigationApp(
    navigationRail: NavigationTypeEnum,
    firstContent: @Composable() (() -> Unit?)? = null,
    secondContent: @Composable() (() -> Unit?)? = null,
) {
    var leftWidth by remember { mutableStateOf(300f) }
    val resizerWidth = 5.dp
    var pointerIcon by remember { mutableStateOf(PointerIcon.Companion.Crosshair) }

    var isDragging by remember { mutableStateOf(false) }
    LaunchedEffect(isDragging) {

    }
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.width(leftWidth.dp).fillMaxHeight()) {
            firstContent?.invoke()
        }
        Box(
            modifier = Modifier
                .width(resizerWidth)
                .fillMaxHeight()
                // .cursorForHorizontalResize() // Curseur de redimensionnement
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        leftWidth = (leftWidth + delta).coerceIn(250f, 600f)
                    },
                    onDragStarted = {
                        pointerIcon = PointerIcon.Hand
                    },
                    onDragStopped = {
                        pointerIcon = PointerIcon.Default
                    }
                )
                .background(Color.LightGray)
        )

        Box(modifier = Modifier.fillMaxSize()) {
            secondContent?.invoke()
        }
    }
}
