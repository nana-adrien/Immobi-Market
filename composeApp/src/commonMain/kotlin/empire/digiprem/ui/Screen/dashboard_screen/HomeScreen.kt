package empire.digiprem.ui.Screen.dashboard_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.background_immeuble
import empire.digiprem.config.getActualWindowsSize
import empire.digiprem.config.isCompactMobilePlatform
import empire.digiprem.navigation.Home
import empire.digiprem.navigation.chat
import empire.digiprem.navigation.login
import empire.digiprem.navigation.signUp
import empire.digiprem.presentation.components.AppHeader
import empire.digiprem.presentation.components.AppIconActionButton
import empire.digiprem.presentation.components.TopBarAction
import empire.digiprem.ui.Screen.dashboard_screen.screens.notifications.Notifications
import org.jetbrains.compose.resources.painterResource
import kotlin.math.ceil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier.Companion,
    homeState: Home
) {
    // DashboardNavigation(navController)
    /*  val state = rememberScrollState(0)
      val gridState = rememberLazyGridState()

      val nestedScrollConnection = remember {
          object : NestedScrollConnection {
              override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                  return Offset.Zero
              }

              override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                  state.dispatchRawDelta(-consumed.y)
                  return Offset.Zero
              }
          }
      }
      Box(modifier = Modifier.fillMaxSize()) {
          Box(modifier = Modifier.fillMaxSize().verticalScroll(state).background(Color.LightGray.copy(alpha = 0.3f))) {
              Box(
                  modifier = Modifier.fillMaxWidth().height(600.dp).align(Alignment.TopStart)
                      .background(Color.Blue).zIndex(0.8f),
                  contentAlignment = Alignment.Center
              ) {
                  Image(
                      painter = painterResource(Res.drawable.background_immeuble),
                      null,
                      contentScale = ContentScale.FillBounds,
                      modifier = Modifier.fillMaxSize()
                  )
                  AppTopBar(modifier = Modifier.align(Alignment.TopStart).background(Color.Transparent))

                  Column(
                      modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.3f)),
                      verticalArrangement = Arrangement.spacedBy(25.dp, alignment = Alignment.CenterVertically),
                      horizontalAlignment = Alignment.CenterHorizontally
                  ) {
                      Text(
                          modifier = Modifier.width(700.dp),
                          text = "Trouvez le bien immobilier qui vous convient",
                          style = MaterialTheme.typography.titleLarge.copy(
                              color = Color.White, fontWeight = FontWeight.Bold, fontSize = 50.sp,
                              textAlign = TextAlign.Center,
                              lineHeight = 55.sp
                          )
                      )
                      Text(
                          modifier = Modifier.width(700.dp),
                          text = "En quête d’un espace qui vous ressemble ? Chambre, appart ou maison? Louez ou achetez en toute simplicité, partout au Cameroun.",
                          style = MaterialTheme.typography.titleLarge.copy(
                              color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp,
                              textAlign = TextAlign.Center,
                          )
                      )

                      FlowRow(modifier = Modifier.fillMaxWidth(),
                          horizontalArrangement = Arrangement.spacedBy(25.dp, alignment = Alignment.CenterHorizontally),
                          verticalArrangement = Arrangement.spacedBy(25.dp, alignment = Alignment.CenterVertically),
                          ) {
                          for (i in 0..5) {
                              Row(
                                  modifier = Modifier.wrapContentSize()
                                      .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
                                      .background(Color.White.copy(red = (i * 10).toFloat(), blue = (i * 7).toFloat()))
                                      .padding(5.dp),
                                  horizontalArrangement = Arrangement.spacedBy(10.dp),
                                  verticalAlignment = Alignment.CenterVertically
                              ) {
                                  Text("type de bien${i}")
                                  Icon(
                                      imageVector = Icons.Default.ArrowDropDown,
                                      contentDescription = ""
                                  )
                              }
                          }
                      }

                      Row(
                          modifier = Modifier.wrapContentSize()
                              .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
                              .background(Color.Cyan)
                              .padding(5.dp),
                          horizontalArrangement = Arrangement.spacedBy(10.dp),
                          verticalAlignment = Alignment.CenterVertically
                      ) {
                          Text("Rechercher")
                      }
                  }
              }

             */
    /* FlowRow(
                modifier = Modifier.padding(top = 600.dp).fillMaxWidth().height(100.dp).background(Color.White)
                    .align(Alignment.TopStart)
                    .zIndex(0.7f),
                horizontalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.Center
            ) {
                for (i in 0 until 7) {
                    Column(
                        modifier = Modifier.wrapContentHeight()
                            .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
                            .background(Color.White.copy(red = (i * 10).toFloat(), blue = (i * 7).toFloat()))
                            .padding(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = ""
                        )
                        Text("Maison${i}")
                    }
                }
            }*/
    /*

            ContentPage(
                modifier =
                    Modifier.padding(top = 700.dp)
                        .nestedScroll(connection = nestedScrollConnection) // Parallax ou suivi partiel
                        .align(Alignment.Center)
                        .zIndex(0f),
                state = gridState,
            )

        }
        AppVerticalScrollBar(
            modifier = Modifier.width(5.dp).align(Alignment.CenterEnd).zIndex(0.8f),
            scrollState = state
        )
    }

    */

    var activeTopBarAction by remember { mutableStateOf(TopBarAction()) }
    var lamd: (String, @Composable () -> Unit) -> Unit = { title, content ->
        if (activeTopBarAction.enabled) {
            if (activeTopBarAction.currentActionName != title) {
                activeTopBarAction = activeTopBarAction.copy(
                    currentActionName = title,
                    content = content
                )
            } else {
                activeTopBarAction = TopBarAction()
            }
        } else {
            activeTopBarAction = activeTopBarAction.copy(
                currentActionName = title,
                enabled = true,
                content = content
            )
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                AppHeader {
                    Row(
                        modifier = Modifier.padding(end = 25.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                       if (!homeState.isConnected){
                           TextButton(
                               colors = ButtonDefaults.textButtonColors().copy(contentColor = Color.White, containerColor = MaterialTheme.colorScheme.primary),
                               onClick = { navController.navigate(signUp) }) {
                               Text("Register")
                           }
                           TextButton(onClick = { navController.navigate(login) }) {
                               Text("Connexion")
                           }
                       } else{
                           AppIconActionButton(
                               selected = activeTopBarAction.currentActionName.equals("Notifications"),
                               onClick = {
                                   lamd("Notifications") {
                                       Box(
                                           modifier = Modifier.height(250.dp).width(350.dp)
                                       ) {
                                           Notifications()
                                       }
                                   }
                               },
                           ) {
                               Icon(imageVector = Icons.Outlined.Notifications, contentDescription = "")
                           }

                           AppIconActionButton(
                               selected = activeTopBarAction.currentActionName.equals("Message"),
                               onClick = {
                                   lamd("Message") {
                                       Box(modifier = Modifier.height(300.dp).width(300.dp)) {
                                           Column {
                                               Column {
                                                   Row(
                                                       modifier = Modifier.fillMaxWidth()
                                                           .padding(start = 10.dp, end = 10.dp),
                                                       verticalAlignment = Alignment.CenterVertically,
                                                       horizontalArrangement = Arrangement.SpaceBetween
                                                   ) {
                                                       Text(
                                                           text = "Message",
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
                                                       horizontalArrangement = Arrangement.spacedBy(
                                                           10.dp,
                                                           alignment = Alignment.Start
                                                       )
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
                                              /* Conversations(
                                                   navController = navController
                                               )*/
                                           }

                                       }
                                   }
                               },
                           ) {
                               Icon(imageVector = Icons.Outlined.Message, contentDescription = "")
                           }

                           AppIconActionButton(
                               selected = activeTopBarAction.currentActionName.equals("profil"),
                               onClick = {
                                   lamd("profil") {
                                       Box(modifier = Modifier.height(250.dp).width(350.dp).padding(10.dp)){
                                           Column {
                                               Row(
                                                   modifier = Modifier.fillMaxWidth().clickable { navController.navigate( chat()) },
                                                   verticalAlignment = Alignment.CenterVertically,
                                               ){
                                                   Text("Tableau de board")
                                               }
                                               HorizontalDivider()
                                               Row(
                                                   modifier = Modifier.fillMaxWidth().clickable { navController.navigate(
                                                       Home(isConnected = false)
                                                   ) },
                                                   verticalAlignment = Alignment.CenterVertically,
                                               ){
                                                   Text("Deconnexion")
                                               }
                                               HorizontalDivider()
                                           }
                                       }
                                   }
                               },
                           ) {
                               Image(
                                   modifier = Modifier.fillMaxSize(),
                                   painter = painterResource(Res.drawable.background_immeuble),
                                   contentDescription = null,
                               )
                           }
                       }
                    }
                }
            }
        ) {
           // MarketplaceScreen(navController,homeState=homeState)
        }

       /* Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            Row */
        /*(Modifier.padding(3.dp))*//*{

            }
            Row(
                modifier = Modifier.background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
            ) {

            }
        }*/

        AnimatedVisibility(
            visible = activeTopBarAction.enabled,//enabledNotification,
            modifier = Modifier.padding(top = 70.dp, end = 20.dp).wrapContentSize().align(Alignment.TopEnd)
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                    .background(Color.White)
            ) {
                activeTopBarAction.content()
            }
        }
    }

}




@Composable
fun ContentPage(modifier: Modifier = Modifier, state: LazyGridState = rememberLazyGridState()) {
    val count = when (getActualWindowsSize().widthSizeClass) {
        WindowWidthSizeClass.Expanded -> {
            4
        }

        WindowWidthSizeClass.Medium -> {
            3
        }

        else -> {
            2
        }
    }
    LazyVerticalGrid(
        modifier = Modifier.then(modifier).height((ceil(20.0 / count) * 440).dp).width(800.dp).padding(5.dp),
        state = state,
        columns = GridCells.Fixed(count),
        userScrollEnabled = false,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(20) {
           // RealEstateItem2(onClick = {})
        }
    }
}

@Composable
fun PageSection(
    title: String,
    modifier: Modifier = Modifier, state: LazyGridState = rememberLazyGridState(),
    content: @Composable () -> Unit
) {

    val isCompactSize = isCompactMobilePlatform()
    val count = when (getActualWindowsSize().widthSizeClass) {
        WindowWidthSizeClass.Expanded -> {
            4
        }

        WindowWidthSizeClass.Medium -> {
            3
        }

        else -> {
            2
        }
    }
    Column(
        modifier = Modifier.then(modifier).wrapContentHeight().fillMaxWidth().padding(horizontal = 25.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "~ $title",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = if(isCompactSize) MaterialTheme.typography.titleSmall.fontSize
                    else MaterialTheme.typography.titleLarge.fontSize
                )
            )
            TextButton(
                onClick = {}
            ) {
                Text(
                    "Voir plus ...",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.W400,
                        fontSize = if(isCompactSize) MaterialTheme.typography.bodySmall.fontSize
                        else MaterialTheme.typography.bodyMedium.fontSize
                    )
                )
            }
        }
        Box(modifier = Modifier.padding(horizontal = 20.dp)){
            content()
        }
        Spacer(Modifier.height(10.dp))
        HorizontalDivider()
    }

}


@Composable
fun PageSectionEx(
    title: String,
    modifier: Modifier = Modifier,
    onMoreClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.then(modifier).wrapContentHeight().fillMaxWidth().padding(horizontal = 25.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "~ $title",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
            )
            onMoreClick?.let {
                TextButton(
                    onClick = it
                ) {
                    Text(
                        "Voir plus ...",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.W400
                        )
                    )
                }
            }

        }
        content()
        Spacer(Modifier.height(10.dp))
        HorizontalDivider()
    }

}

@Composable
fun AppTopBar(modifier: Modifier) {
    Box(
        modifier = Modifier.then(modifier)
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text("Documentation Compose", color = Color.White, style = MaterialTheme.typography.bodyMedium)
    }
}


@Composable
fun TopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1E88E5))
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text("Documentation Compose", color = Color.White, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun Sidebar(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(Color(0xFFE0E0E0)).padding(12.dp)
    ) {
        Text("Navigation", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Accueil")
        Text("Guide")
        Text("Référence")
    }
}

@Composable
fun Title(text: String) {
    Text(text, style = MaterialTheme.typography.titleMedium, color = Color(0xFF212121))
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun Subtitle(text: String) {
    Text(text, style = MaterialTheme.typography.titleSmall, color = Color(0xFF424242))
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun Paragraph(text: String) {
    Text(text, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF616161))
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun BulletList(items: List<String>) {
    Column {
        items.forEach {
            Row {
                Text("•", modifier = Modifier.padding(end = 8.dp))
                Text(it)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun CodeBlock(code: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEEEEEE))
            .padding(12.dp)
    ) {
        Text(code, style = MaterialTheme.typography.titleMedium, color = Color(0xFF37474F))
    }
}