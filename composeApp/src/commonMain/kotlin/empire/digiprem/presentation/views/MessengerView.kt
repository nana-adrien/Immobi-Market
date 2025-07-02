package empire.digiprem.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import empire.digiprem.navigation.ViewMessenger
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import androidx.navigation.NavHostController
import empire.digiprem.navigation.EnumView
import empire.digiprem.presentation.base.color.Colors
import empire.digiprem.presentation.components.*
import empire.digiprem.presentation.components.wrapper.PageWrapperState
import empire.digiprem.presentation.components.wrapper.WebDesktopPageWrapper
import empire.digiprem.presentation.viewmodels.MessengerViewModel
import empire.digiprem.presentation.intents.MessengerIntent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessengerView(
    viewMessenger: ViewMessenger,
    navController: NavHostController,
    messengerViewModel: MessengerViewModel = koinViewModel(),
) {
    // val messengerViewModel:MessengerViewModel = viewModel{MessengerViewModel()}
    val state by messengerViewModel.state.collectAsState()
    val onSendIntent = messengerViewModel::onIntentHandler
    val scrollState = rememberScrollState(0)

    /*Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            AppBox(
                modifier = Modifier.background(Colors.brushVioletLinear),
            ) {
                AppHeader(
                    containerColor = Color.Transparent,
                    navigationIcon = {
                        AppIconActionButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack, ""
                            )
                        }
                    }
                ) {

                }
            }

        }
    ) { padding ->
        */
       WebDesktopPageWrapper(
           view = EnumView.ViewMessenger,
           state = PageWrapperState(isSuccess = true)
       ){padding->
        AppBox(
            modifier = Modifier.padding(padding).background(MaterialTheme.colorScheme.background),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                AppCardWrapperEx(
                    modifier = Modifier.weight(0.3f)//.shadow(elevation = 20.dp, shape = RoundedCornerShape(7.dp))
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Scaffold(
                        topBar = {
                            Column {
                                TopAppBar(
                                    colors = TopAppBarDefaults.topAppBarColors()
                                        .copy(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                                    title = { Text("Conversation") },
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
                                                value = state.searchText,
                                                onValueChange = { onSendIntent(MessengerIntent.OnSearch(it)) }
                                            )
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
                        }
                    ) { padding ->
                        LazyColumn(
                            modifier = Modifier.padding(padding).fillMaxSize()
                        ) {
                            items(
                                items = state.conversations.filter { conv ->
                                    val search = state.searchText.trim().lowercase()
                                    if (search.isNotEmpty()) {
                                        conv.title.lowercase().contains(search) ||
                                                conv.messages.any { it.content.lowercase().contains(search) }
                                    } else true
                                },
                                key = { it.id }) { conversation ->
                                ConversationItem(
                                    modifier = Modifier,
                                    selected = conversation.selected || state.currentSelectConversation?.id == conversation.id,
                                    online = conversation.online,
                                    title = conversation.title,
                                    sender = conversation.sender,
                                    message = conversation.messages.first().content,
                                    counter = conversation.messages.count { !it.seen && !it.isSender },
                                    conversationType = conversation.conversationType,
                                    messageType = conversation.messages.first().messageType,
                                    status = conversation.status,
                                    timeIndicator = conversation.timeIndicator,
                                    onClick = {
                                        onSendIntent(MessengerIntent.OnConversationItemClicked(conversation))
                                    },
                                    onLongClick = {
                                    }
                                )
                            }
                        }
                    }
                }
                state.currentSelectConversation?.let {
                    AppCardWrapperEx(
                        modifier = Modifier.weight(0.7f) //.shadow(elevation = 20.dp, shape = RoundedCornerShape(7.dp))
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        ChatScreen(
                            conversation = it
                        ) { id, message ->
                            onSendIntent(MessengerIntent.OnSendMessage(id, message))
                        }
                    }
                }
            }
        }
    }
}