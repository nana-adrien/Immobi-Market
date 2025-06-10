package com.octopusfx.mymessenger.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import empire.digiprem.presentation.views.ReplayMessage


data class ChatMessage(
    val id: String = "1",
    val userName: String = "",
    val content: String = "",
    val imageUrl: String? = null,
    val replyTo: ChatMessage? = null,
    val isSender: Boolean = false
)

@Composable
fun ChatMessageItem(
    message: ChatMessage,
    modifier: Modifier = Modifier,
    onReply: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = if (message.isSender) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 300.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(if (message.isSender) Color(0xFFD1E8FF) else Color(0xFFEDEDED))
                .combinedClickable(
                    onClick = {},
                    onLongClick = onReply
                )
                .padding(10.dp)
        ) {
            // 💬 Message auquel on répond
            message.replyTo?.let { replied ->
                ReplayMessage(replied)
                /*  Box(
                      modifier = Modifier
                          .fillMaxWidth()
                          .height(100.dp)
                          .padding(horizontal = 5.dp)
                          .padding(bottom = 2.dp, top = 10.dp)
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
                                  .fillMaxWidth()
                                  .clip(RoundedCornerShape(8.dp))
                                  .background(Color.LightGray.copy(0.3f))
                                  .padding(6.dp)
                          ) {
                              Text(
                                  text =replied.userName,
                                  fontWeight = FontWeight.Bold,
                                  fontSize = 12.sp,
                                  maxLines = 1,
                                  overflow = TextOverflow.Ellipsis
                              )
                              Text(
                                  text = replied.content,
                                  fontSize = 12.sp,
                                  maxLines = 2,
                                  overflow = TextOverflow.Ellipsis
                              )
                              replied.imageUrl?.let { imageUrl ->
                                  Spacer(modifier = Modifier.height(6.dp))
                                  AsyncImage(
                                      model = imageUrl,
                                      contentDescription = null,
                                      contentScale = ContentScale.Crop,
                                      modifier = Modifier
                                          .fillMaxWidth()
                                          .height(80.dp)
                                          .clip(RoundedCornerShape(6.dp))
                                  )
                              }
                          }

                          if(message.replyTo.imageUrl!=null){
                              Box(
                                  modifier = Modifier
                                      .weight(1f)
                                      .fillMaxSize()
                                  // .clip(RoundedCornerShape(size = 7.dp))
                              ) {
                                 *//* if (realEstateData == null) {
                                    Image(
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop,
                                        painter = painterResource(Res.drawable.compose_multiplatform),
                                        contentDescription = ""
                                    )
                                } else {*//*
                                    AsyncImage(
                                        model = message.imageUrl,
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = ""
                                    )
                                //}

                                Box(
                                    modifier = Modifier.align(Alignment.TopEnd)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = ""
                                    )
                                }
                            }
                        }

                    }


                }*/

                Spacer(modifier = Modifier.height(8.dp))
            }
            message.imageUrl?.let { imageUrl ->
                Spacer(modifier = Modifier.height(8.dp))
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
            // 📄 Contenu du message
            if (message.userName.isNotEmpty()) {
                Text(
                    text = message.userName,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            Text(
                text = message.content,
                fontSize = 13.sp
            )
        }
    }
}


fun generateRandomMessages(): List<ChatMessage> {
    val messages = mutableListOf<ChatMessage>()

    val sampleTitles = listOf(
        "Salut", "Info", "Réunion", "Tâche", "Projet", "OK", "Urgent", "Notes", "Dispo ?", "Merci"
    )
    val sampleContents = listOf(
        "Tu es dispo pour un call ?", "Le projet avance bien.",
        "Regarde ce fichier stp.", "On se voit à 14h.", "Pas de souci.",
        "Je vais vérifier ça demain.", "Tu peux m’envoyer le lien ?", "Voici la maquette.",
        "On valide ça lundi ?", "Parfait !"
    )

    repeat(20) { index ->
        val title = sampleTitles.random()
        val content = sampleContents.random()
        val isSender = listOf(true, false).random()

        // Une chance sur 4 de répondre à un message précédent
        val replyTo = if (messages.isNotEmpty() && (0..3).random() == 0) {
            messages.random()
        } else null

        messages.add(
            ChatMessage(
                id = index.toString(),
                userName = title,
                content = content,
                isSender = isSender,
                replyTo = replyTo
            )
        )
    }

    return messages
}


