package com.octopusfx.mymessenger.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import empire.digiprem.presentation.components.*
import kotlinx.serialization.Serializable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationsScreen(navigationRail : NavigationTypeEnum, navController : NavHostController) {
   Scaffold(
      topBar = {
         Column {
            TopAppBar(
               colors =TopAppBarDefaults.topAppBarColors().copy(containerColor =  MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)) ,
               title = { Text("Conversation") } ,
               actions = {
                  IconButton(
                     onClick = {}
                  ) {
                     Icon(
                        imageVector = Icons.Filled.Phone ,
                        contentDescription = ""
                     )
                  }
                  IconButton(
                     onClick = {}
                  ) {
                     Icon(
                        imageVector = Icons.Filled.Search ,
                        contentDescription = ""
                     )
                  }
                  IconButton(
                     onClick = {}
                  ) {
                     Icon(
                        imageVector = Icons.Filled.MoreVert ,
                        contentDescription = ""
                     )
                  }
               }

            )
            Column(
               modifier = Modifier
                  .fillMaxWidth()
                  .height(55.dp)
                  .padding(horizontal = 20.dp , vertical = 7.dp)
            ) {
               Row(
                  modifier = Modifier
                     .fillMaxWidth()
                     .clip(RoundedCornerShape(size = 15.dp))
                     .background(Color.LightGray.copy(0.3f)) ,
                  verticalAlignment = Alignment.CenterVertically
               ) {
                  Box(
                     modifier = Modifier
                        .weight(0.7f)
                        .padding(2.dp) ,
                     contentAlignment = Alignment.Center
                  ) {
                     IconButton(
                        onClick = {}
                     ) {
                        Icon(
                           imageVector = Icons.Filled.Search ,
                           contentDescription = ""
                        )
                     }
                  }
                  Box(
                     modifier = Modifier
                        .weight(3.5f)
                        .padding(end = 5.dp) ,
                  ) {
                     BasicTextField(
                        textStyle = MaterialTheme.typography.bodyMedium ,
                        singleLine = true ,
                        value = "bonjour le monde bonjour bonjour bonjour bonjour " ,
                        onValueChange = {

                        })
                  }
                  Box(
                     modifier = Modifier.weight(0.3f) ,
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
                     .padding(horizontal = 13.dp) ,
                  color = Color.LightGray
               )
            }
            Divider()
         }

      } ,
      floatingActionButton = {
         FloatingActionButton(
            onClick = {}
         ) {
            Icon(
               imageVector = Icons.Default.Chat ,
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Conversations(navController : NavHostController) {

   var conversationList by remember {
      mutableStateOf(
         mutableListOf(
            Conversation(
               id = 0 ,
               selected = false ,
               online = true ,
               title = "Alice" ,
               sender = "Alice" ,
               message = "Salut, comment ça va ?" ,
               counter = 1 ,
               conversationType = ConversationTypeEnum.PRIVATE ,
               messageType = MessageTypeEnum.TEXT ,
               status = MessageStatusEnum.RECEIVED ,
               timeIndicator = "il y a 16mn"
            ) ,
            Conversation(
               id = 1 ,
               selected = true ,
               online = false ,
               title = "Projet Travail" ,
               sender = "Paul" ,
               message = "Voici le rapport de la réunion." ,
               counter = 3 ,
               conversationType = ConversationTypeEnum.GROUP ,
               messageType = MessageTypeEnum.FILE ,
               status = MessageStatusEnum.READ ,
               timeIndicator = "8:09"
            ) ,
            Conversation(
               id = 2 ,
               selected = false ,
               online = true ,
               title = "Bob" ,
               sender = "Bob" ,
               message = "Regarde cette vidéo !" ,
               counter = 2 ,
               conversationType = ConversationTypeEnum.PRIVATE ,
               messageType = MessageTypeEnum.VIDEO ,
               status = MessageStatusEnum.SEND ,
               timeIndicator = "Hier"
            ) ,
            Conversation(
               id = 3 ,
               selected = false ,
               online = false ,
               title = "Charlie" ,
               sender = "Charlie" ,
               message = "Nouvelle musique à écouter." ,
               counter = 0 ,
               conversationType = ConversationTypeEnum.PRIVATE ,
               messageType = MessageTypeEnum.AUDIO ,
               status = MessageStatusEnum.RECEIVED ,
               timeIndicator = "Lundi"
            ) ,
            Conversation(
               id = 4 ,
               selected = true ,
               online = true ,
               title = "Équipe Dev" ,
               sender = "David" ,
               message = "Code mis à jour sur GitHub." ,
               counter = 5 ,
               conversationType = ConversationTypeEnum.GROUP ,
               messageType = MessageTypeEnum.TEXT ,
               status = MessageStatusEnum.SENDING ,
               timeIndicator = "il y a 5h"
            ) ,
            Conversation(
               id = 5 ,
               selected = false ,
               online = false ,
               title = "Discussion Familiale" ,
               sender = "Maman" ,
               message = "Qui apporte quoi au dîner ?" ,
               counter = 1 ,
               conversationType = ConversationTypeEnum.GROUP ,
               messageType = MessageTypeEnum.TEXT ,
               status = MessageStatusEnum.NOT_SEND ,
               timeIndicator = "Dimanche"
            ) ,
            Conversation(
               id = 6 ,
               selected = false ,
               online = true ,
               title = "Patron" ,
               sender = "Patron" ,
               message = "N'oubliez pas la réunion demain." ,
               counter = 0 ,
               conversationType = ConversationTypeEnum.PRIVATE ,
               messageType = MessageTypeEnum.TEXT ,
               status = MessageStatusEnum.READ ,
               timeIndicator = "10:45"
            ) ,
            Conversation(
               id = 7 ,
               selected = true ,
               online = true ,
               title = "Martin" ,
               sender = "Martin" ,
               message = "Écoute ce podcast, super intéressant !" ,
               counter = 4 ,
               conversationType = ConversationTypeEnum.PRIVATE ,
               messageType = MessageTypeEnum.AUDIO ,
               status = MessageStatusEnum.RECEIVED ,
               timeIndicator = "il y a 30mn"
            ) ,
            Conversation(
               id = 8 ,
               selected = false ,
               online = false ,
               title = "Sport Club" ,
               sender = "Coach Pierre" ,
               message = "RDV samedi pour le match." ,
               counter = 2 ,
               conversationType = ConversationTypeEnum.GROUP ,
               messageType = MessageTypeEnum.TEXT ,
               status = MessageStatusEnum.SEND ,
               timeIndicator = "Samedi"
            ) ,
            Conversation(
               id = 9 ,
               selected = false ,
               online = true ,
               title = "Marie" ,
               sender = "Marie" ,
               message = "Essaie cette recette de gâteau !" ,
               counter = 3 ,
               conversationType = ConversationTypeEnum.PRIVATE ,
               messageType = MessageTypeEnum.TEXT ,
               status = MessageStatusEnum.RECEIVED ,
               timeIndicator = "il y a 45mn"
            )
         )
      )
   }
   /* val state = rememberReorderableLazyListState(onMove = { from , to ->
       conversationList =
          conversationList.toMutableList().apply { add(to.index , removeAt(from.index)) }
    })*/
   LazyColumn(
      modifier = Modifier
         .fillMaxSize()
      // .reorderable(state)
      //  .detectReorderAfterLongPress(state) ,
   ) {
      items(items = conversationList , key = { it.id }) {
         //ReorderableItem(state , key = it) { isDragging ->
         //    val elevation = animateDpAsState(if (isDragging) 16.dp else 0.dp)
         ConversationItem(
            modifier = Modifier,
               //   .shadow(elevation.value)
             //  .animateItemPlacement() ,
            selected = it.selected ,
            online = it.online ,
            title = it.title ,
            sender = it.sender ,
            message = it.message ,
            counter = it.counter ,
            conversationType = it.conversationType ,
            messageType = it.messageType ,
            status = it.status ,
            timeIndicator = it.timeIndicator ,
            onClick = {
              /* navController.navigateTo(
                  ViewEnum.CHAT ,
                  closeThisScreen = false ,
                  argument = NavigationShippingArg.Builder().addArgument(key = "Chat" , value = it)
                     .build()
               )*/
            } ,
            onLongClick = {
            }
         )
         // }

      }
   }
}
@Serializable
data class Conversation(
    val id : Int = 0,
    val selected : Boolean,
    val online : Boolean,
    val title : String,
    val sender : String,
    val message : String,
    val counter : Int,
    val conversationType : ConversationTypeEnum,
    val messageType : MessageTypeEnum,
    val status : MessageStatusEnum,
    val timeIndicator : String,
)
