package empire.digiprem.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.octopusfx.mymessenger.ui.screen.Conversations
import empire.digiprem.navigation.ViewConversations
import empire.digiprem.presentation.components.*
import empire.digiprem.presentation.viewmodels.ConversationsViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationsView(
    viewConversations: ViewConversations?=null,
    navController: NavHostController,
    navigationRail: NavigationTypeEnum=NavigationTypeEnum.NAVIGATION_RAIL,
    conversationsViewModel: ConversationsViewModel = koinViewModel()
) {
    // val conversationsViewModel:ConversationsViewModel = viewModel{ConversationsViewModel()}
    val state by conversationsViewModel.state.collectAsState()
    val onSendIntent = conversationsViewModel::onIntentHandler
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors()
                        .copy(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                    title = { Text("Conversation") },
                    actions = {
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Phone,
                                contentDescription = ""
                            )
                        }
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = ""
                            )
                        }
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = ""
                            )
                        }
                    }

                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .padding(horizontal = 20.dp, vertical = 7.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(size = 15.dp))
                            .background(Color.LightGray.copy(0.3f)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(0.7f)
                                .padding(2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(
                                onClick = {}
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = ""
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(3.5f)
                                .padding(end = 5.dp),
                        ) {
                            BasicTextField(
                                textStyle = MaterialTheme.typography.bodyMedium,
                                singleLine = true,
                                value = "bonjour le monde bonjour bonjour bonjour bonjour ",
                                onValueChange = {

                                })
                        }
                        Box(
                            modifier = Modifier.weight(0.3f),
                            contentAlignment = Alignment.Center
                        ) {
                            /*IconButton(
                               onClick = {}
                            ) {
                               Icon(
                                  imageVector = Icons.Filled.Send ,
                                  contentDescription = ""
                               )
                            }*/
                        }
                    }
                    Divider(
                        modifier = Modifier
                            .height(2.dp)
                            .padding(horizontal = 13.dp),
                        color = Color.LightGray
                    )
                }
                Divider()
            }

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = ""
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Conversations(navController)
        }


    }

}