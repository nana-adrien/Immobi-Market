package empire.digiprem.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.octopusfx.mymessenger.ui.screen.MessageItem
import com.octopusfx.mymessenger.ui.screen.generateRandomMessages
import empire.digiprem.model.chat.Conversation
import empire.digiprem.model.chat.Message
import empire.digiprem.navigation.ViewChat
import empire.digiprem.presentation.components.*
import empire.digiprem.presentation.components.app.RealEstateData
import empire.digiprem.presentation.intents.MessengerIntent
import empire.digiprem.presentation.viewmodels.ChatViewModel
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import org.koin.compose.viewmodel.koinViewModel
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatView(
    viewChat: ViewChat?,
    navController: NavHostController,
    navigationRail: NavigationTypeEnum = NavigationTypeEnum.BOTTOM_NAVIGATION,
    conversation: Conversation? = null,
    chatViewModel: ChatViewModel = koinViewModel(),
) {
    // val chatViewModel:ChatViewModel = viewModel{ChatViewModel()}
    val state by chatViewModel.state.collectAsState()
    val onSendIntent = chatViewModel::onIntentHandler
    var message by remember { mutableStateOf(Message()) }
    val scrolableState = rememberLazyListState()
    val realEstateData by remember {
        mutableStateOf<RealEstateData?>(/*if (viewChat != null) Json.decodeFromString<RealEstateData>(viewChat.content) else*/
            null
        )
    }

    val scope = rememberCoroutineScope()
    var messages by remember { mutableStateOf(generateRandomMessages()) }
    var textfield by remember { mutableStateOf(viewChat?.message ?: "") }


}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun ChatScreen(
    conversation: Conversation,
    navigationRail: NavigationTypeEnum = NavigationTypeEnum.BOTTOM_NAVIGATION,
    onSendMessage: (Int, Message) -> Unit,
) {
    val scrolableState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var message by remember { mutableStateOf(Message(isSender = true)) }
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            Column {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors()
                        .copy(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),

                    navigationIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (navigationRail == NavigationTypeEnum.BOTTOM_NAVIGATION) {
                                IconButton(onClick = {
                                    // navController.popBackStack()
                                }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = ""
                                    )
                                }
                            }

                            AsyncImageSection(
                                online = true,
                                model = "https://lh3.googleusercontent.com/a/ACg8ocLM-v1DuVykbTszaWgG5NWBl2J2n9iIFi7MStQ08_z_prjXdg=s96-c",
                                contentDescription = "", modifier = Modifier.size(40.dp)
                            )
                        }

                    },
                    title = {
                        Column(
                            modifier = Modifier
                                .weight(3.7f)
                                .padding(start = 10.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = conversation?.title ?: "Projet Travail(Nouvelle musique à écouter)",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "en ligne",
                                    fontWeight = FontWeight.W400,
                                    fontSize = 12.sp,
                                    maxLines = 1
                                )
                            }
                        }
                    },
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
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = ""
                            )
                        }
                    }
                )
                Divider()
            }

        },
        bottomBar = {
            Column(
                modifier = Modifier
                    //.weight(4f)
                    .fillMaxWidth()
                    //.height(65.dp)
                    .padding(horizontal = 10.dp, vertical = 1.dp).padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(size = 15.dp))
                    .background(Color.LightGray.copy(0.3f))
            ) {
                if (message.replyTo != null) {
                    ReplayMessage(message.replyTo!!, enableDeleteButton = true) {
                        message = message.copy(replyTo = null)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Box(
                        modifier = Modifier.wrapContentWidth(),
                        contentAlignment = Alignment.CenterStart
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
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(7.dp),
                        // contentAlignment = Alignment.Top
                    ) {
                        BasicTextField(
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Start),

                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                            keyboardActions = KeyboardActions(
                                onSend = {
                                    scope.launch {
                                        message = Message()
                                        scrolableState.scrollToItem((conversation.messages.size - 1))
                                    }
                                }
                            ),
                            maxLines = 5,
                            value = message.content,
                            onValueChange = {
                                message = message.copy(content = it)
                            })
                        HorizontalDivider(
                            modifier = Modifier
                                .height(2.dp)
                                .padding(bottom = 12.dp),
                            color = Color.Gray
                        )
                    }
                    Row(
                        modifier = Modifier.wrapContentWidth()
                            .padding(2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.AttachFile,
                                contentDescription = ""
                            )
                        }
                        IconButton(
                            enabled = !message.content.trim().equals(""),
                            onClick = {
                                scope.launch {
                                    message = message.copy()
                                    onSendMessage(conversation.id, message)
                                    message = Message()
                                    scrolableState.scrollToItem((conversation.messages.size - 1))
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Send,
                                contentDescription = ""
                            )
                        }
                    }
                }
                HorizontalDivider(
                    modifier = Modifier
                        .height(2.dp)
                        .padding(horizontal = 13.dp),
                    color = Color.LightGray
                )
            }
        }
    ) {
      /*  LazyColumn(
            state = scrolableState,
            modifier = Modifier.fillMaxSize().padding(it).padding(30.dp)
        ) {
            items(conversation.messages) {
                MessageItem(message = it) {
                    message = message.copy(replyTo = it)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }*/
    }
}


@Composable
fun ReplayMessage(message: Message, enableDeleteButton: Boolean = false, onClickDeleteButton: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 80.dp)
            .padding(horizontal = 5.dp)
            .padding(bottom = 2.dp, top = 3.dp)
            .clip(RoundedCornerShape(size = 7.dp))
            .background(Color.Red)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 7.dp)
                .background(Color.LightGray)
                .padding(vertical = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(4f)
                    .background(Color.LightGray.copy(0.5f))
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (message.isSender) "vous" else message.userName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = message.content,
                        fontWeight = FontWeight.W400,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 13.sp,
                        maxLines = 2
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                // .clip(RoundedCornerShape(size = 7.dp))
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = message.imageUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
                if (enableDeleteButton) {
                    IconButton(
                        modifier = Modifier.align(Alignment.TopEnd),
                        onClick = onClickDeleteButton
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = ""
                        )
                    }
                }

            }
        }
    }
}

