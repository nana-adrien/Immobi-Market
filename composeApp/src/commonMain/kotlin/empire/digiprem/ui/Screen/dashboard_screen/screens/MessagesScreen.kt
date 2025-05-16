package empire.digiprem.ui.Screen.dashboard_screen.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import empire.digiprem.navigation.chat
import empire.digiprem.presentation.components.NavigationTypeEnum


@Composable
fun MessagesScreen(navigationRail: NavigationTypeEnum, navHostController: NavHostController, chatState: chat?) {
 /*   val scrollState= rememberScrollState(0)
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
*/

}

