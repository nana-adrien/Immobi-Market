package com.octopusfx.mymessenger.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.compose_multiplatform
import empire.digiprem.navigation.ViewChat
import empire.digiprem.presentation.components.ImageSection
import empire.digiprem.presentation.components.NavigationTypeEnum
import empire.digiprem.presentation.components.app.RealEstateData
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavHostController,
    navigationRail: NavigationTypeEnum,
    conversation: Conversation?,
    viewChat: ViewChat
) {
    val realEstateData by remember { mutableStateOf( if (viewChat!=null) Json.decodeFromString<RealEstateData>(viewChat.content) else null) }
    var textfield by remember { mutableStateOf(viewChat?.message ?: "") }
    Scaffold(
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
                                    navController.popBackStack()
                                }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = ""
                                    )
                                }
                            }
                            ImageSection(
                                online =
                                    true, painter = painterResource(Res.drawable.compose_multiplatform),
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
            Row(
                modifier = Modifier.padding(bottom = 12.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    modifier = Modifier
                        .weight(4f)
                        .fillMaxWidth()
                        //.height(65.dp)
                        .padding(horizontal = 10.dp, vertical = 1.dp)
                        .clip(RoundedCornerShape(size = 15.dp))
                        .background(Color.LightGray.copy(0.3f))
                ) {
                    Box(
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
                                    .weight(4f)
                                    .background(Color.LightGray.copy(0.5f))
                                    .padding(start = 10.dp),
                                verticalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text =realEstateData?.title?: "Projet Travail(Nouvelle musique à écouter)",
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
                                        text =  realEstateData?.location?:  "Tu peux maintenant utiliser l'URL de l'image récupérée pour l'afficher dans ton interface utilisateur. Utilise Coil pour charger et afficher l'image dans un composant Image.\n" +
                                                "\n" +
                                                "Voici un exemple pour afficher l'image dans un composable Jetpack Compose :",
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
                               if (realEstateData==null){
                                   Image(
                                       modifier = Modifier.fillMaxSize(),
                                       contentScale = ContentScale.Crop,
                                       painter = painterResource(Res.drawable.compose_multiplatform),
                                       contentDescription = ""
                                   )
                               } else{
                                   AsyncImage(
                                       modifier = Modifier.fillMaxSize(),
                                       model = Modifier.fillMaxSize(),
                                       contentScale = ContentScale.Crop,
                                       contentDescription = ""
                                   )
                               }

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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom
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
                        Column(
                            modifier = Modifier
                                .weight(3.5f)
                                .padding(7.dp),
                            // contentAlignment = Alignment.Top
                        ) {
                            BasicTextField(
                                textStyle = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Start),
                                maxLines = 5,
                                value = textfield,
                                onValueChange = {
                                    textfield = it
                                })
                            Divider(
                                modifier = Modifier
                                    .height(2.dp)
                                    .padding(bottom = 12.dp),
                                color = Color.Gray
                            )
                        }
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
                                    imageVector = Icons.Filled.AttachFile,
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                    Divider(
                        modifier = Modifier
                            .height(2.dp)
                            .padding(horizontal = 13.dp),
                        color = Color.LightGray
                    )
                }
                Box(
                    modifier = Modifier.weight(0.7f)
                ) {
                    FloatingActionButton(
                        shape = CircleShape,
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    ChatItem()
                }
            }
        }
    }
}

