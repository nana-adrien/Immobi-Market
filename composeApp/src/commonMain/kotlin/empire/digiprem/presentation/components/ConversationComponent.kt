package empire.digiprem.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.InsertDriveFile
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.compose_multiplatform
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ConversationItem(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    online: Boolean = false,
    title: String = "KO-C - Balancé feat. Tenor (Official Video)",
    sender: String = "KO-C:",
    message: String = "After the success of Bollo & Caro, KO-C comes back with a new version of Balancé with the rapper Tenor. ",
    counter: Int = 0,
    conversationType: ConversationTypeEnum = ConversationTypeEnum.PRIVATE,
    messageType: MessageTypeEnum = MessageTypeEnum.TEXT,
    status: MessageStatusEnum = MessageStatusEnum.NONE,
    timeIndicator: String = "12/02/2025",
    painter: Painter = painterResource(Res.drawable.compose_multiplatform),
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .fillMaxWidth()
            .height(80.dp)
            .background(if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background)
            .padding(10.dp).then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        ImageSection(
            online = online,
            painter = painter,
            contentDescription = ""
        )
        ContentSection(
            sender = sender,
            message = message,
            title = title,
            timeIndicator = timeIndicator,
            conversationType = conversationType,
            messageType = messageType,
            status = status,
            counter = counter,
        )
    }

}

@Composable
private fun ContentSection(
    title: String,
    timeIndicator: String,
    sender: String,
    message: String,
    counter: Int = 0,
    conversationType: ConversationTypeEnum,
    messageType: MessageTypeEnum,
    status: MessageStatusEnum,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        verticalArrangement = Arrangement.Center
    ) {
        ContentTopSection(
            title = title,
            timeIndicator = timeIndicator
        )
        ContentBottomSection(
            sender = sender,
            message = message,
            conversationType = conversationType,
            messageType = messageType,
            status = status,
            counter = counter,
        )
    }
}

@Composable
private fun ContentTopSection(
    title: String,
    timeIndicator: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(3.7f)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            modifier = Modifier.weight(1.3f),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = timeIndicator,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun ContentBottomSection(
    sender: String,
    message: String,
    counter: Int = 0,
    counterColor: Color = Color.Black,
    containerColor: Color = Color.Yellow.copy(alpha = 0.7f),
    contentColor: Color = Color.White,
    conversationType: ConversationTypeEnum,
    messageType: MessageTypeEnum,
    status: MessageStatusEnum,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ContentBottomMessageSection(
            sender = sender,
            message = message,
            conversationType = conversationType,
            messageType = messageType,
            status = status
        )
        ContentBottomBadgeSection(
            counter = counter,
            counterColor = counterColor,
            contentColor = contentColor,
            containerColor = containerColor
        )
    }
}

@Composable
private fun RowScope.ContentBottomMessageSection(
    sender: String,
    message: String,
    conversationType: ConversationTypeEnum,
    messageType: MessageTypeEnum,
    status: MessageStatusEnum,
) {
    Row(
        modifier = Modifier.weight(4.2f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (sender.isNotEmpty()) {
                if (conversationType == ConversationTypeEnum.GROUP) {
                    Text(
                        text = "$sender:",
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        maxLines = 1
                    )
                }
            } else {
                if (status != MessageStatusEnum.NONE) {
                    Icon(
                        imageVector = when (status) {
                            MessageStatusEnum.NOT_SEND -> {
                                Icons.Filled.Error
                            }

                            MessageStatusEnum.SENDING -> {
                                Icons.Filled.AccessTime
                            }

                            MessageStatusEnum.SEND -> {
                                Icons.Filled.Check
                            }

                            MessageStatusEnum.RECEIVED, MessageStatusEnum.READ -> {
                                Icons.Filled.DoneAll
                            }

                            else -> {
                                Icons.Filled.Cached
                            }
                        },
                        modifier = Modifier.size(18.dp),
                        tint = if (status == MessageStatusEnum.READ) Color.Green else Color.Gray,
                        contentDescription = "",
                    )
                }

            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (messageType != MessageTypeEnum.TEXT) {
                Icon(
                    imageVector = when (messageType) {
                        MessageTypeEnum.VOCAL -> {
                            Icons.Filled.Phone
                        }

                        MessageTypeEnum.VIDEO -> {
                            Icons.Filled.Videocam
                        }

                        MessageTypeEnum.AUDIO -> {
                            Icons.Filled.Mic
                        }

                        MessageTypeEnum.FILE -> {
                            Icons.AutoMirrored.Filled.InsertDriveFile
                        }

                        else -> {
                            Icons.Filled.Cached
                        }
                    },
                    modifier = Modifier.size(18.dp),
                    tint = if (sender.isEmpty() && status == MessageStatusEnum.READ && (messageType == MessageTypeEnum.VIDEO || messageType == MessageTypeEnum.AUDIO || messageType == MessageTypeEnum.VOCAL)
                    ) Color.Green else Color.Gray,
                    contentDescription = "",
                )
            }
            Text(
                text = message,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun RowScope.ContentBottomBadgeSection(
    counter: Int = 0,
    counterColor: Color,
    containerColor: Color,
    contentColor: Color,
) {
    if (counter != 0) {
        Row(
            modifier = Modifier.weight(0.4f),
            horizontalArrangement = Arrangement.Center
        ) {
            Badge(
                modifier = Modifier.size(25.dp),
                containerColor = containerColor,
                contentColor = contentColor
            ) {
                Text(
                    "$counter",
                    color = counterColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ImageSection(
    online: Boolean,
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    badgeModifier: Modifier = Modifier,
) {
    Box {
        Image(
            modifier = Modifier
                .then(modifier)
                .size(55.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            painter = painter,
            contentDescription = contentDescription
        )
        if (online) {
            Badge(
                modifier = Modifier
                    .then(badgeModifier)
                    .size(15.dp)
                    .padding(start = 7.dp, bottom = 5.dp)
                    .align(Alignment.BottomStart),
                containerColor = Color.Green,
                contentColor = Color.White
            )
        }
    }
}@Composable
fun AsyncImageSection(
    model: Any?,
    online: Boolean,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    badgeModifier: Modifier = Modifier,
) {
    Box {
        AsyncImage(
            model = model,
            contentDescription = contentDescription,
            modifier =  Modifier
                .then(modifier)
                .size(55.dp)
                .clip(CircleShape),
            clipToBounds = true,
            contentScale = ContentScale.Crop
        )
        if (online) {
            Badge(
                modifier = Modifier
                    .then(badgeModifier)
                    .size(15.dp)
                    .padding(start = 7.dp, bottom = 5.dp)
                    .align(Alignment.BottomStart),
                containerColor = Color.Green,
                contentColor = Color.White
            )
        }
    }
}

enum class MessageStatusEnum {
    NOT_SEND,
    SENDING,
    SEND,
    RECEIVED,
    READ,
    NONE
}

enum class ConversationTypeEnum {
    GROUP,
    PRIVATE
}

enum class MessageTypeEnum {
    TEXT,
    VOCAL,
    VIDEO,
    AUDIO,
    FILE
}
