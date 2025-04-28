package empire.digiprem.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import androidx.compose.material3.MaterialTheme as Material1Theme

enum class NavigationTypeEnum {
    BOTTOM_NAVIGATION,
    NAVIGATION_RAIL
}

@Composable
fun AppRailNavigation(navigationRail: NavigationTypeEnum) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val rotationAngle by animateFloatAsState(
        targetValue = if (drawerState.isOpen) 180f else 0f,
        label = "rotation"
    )
    val drawerOffset = drawerState.offset.value
    // Animation manuelle du petit décalage
    val animatedOffset by animateDpAsState(
        targetValue = if (drawerOffset > 0f && drawerOffset < 300f) 10.dp else 0.dp,
        label = "drawer subtle offset"
    )
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        gesturesEnabled = false,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerItem(
                    selected = false,
                    onClick = {
                        scope.launch {
                            if (drawerState.isOpen) {
                                drawerState.close()
                            }
                        }
                    },
                    label = {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = null,
                            modifier = Modifier.rotate(rotationAngle)
                        )
                    }
                )
                NavigationDrawerItem(
                    selected = false,
                    onClick = { },
                    label = { Icon(Icons.Default.Message, contentDescription = null) }
                )
                NavigationDrawerItem(
                    selected = false,
                    onClick = { },
                    label = { Icon(Icons.Default.Phone, contentDescription = null) }
                )
                NavigationDrawerItem(
                    selected = false,
                    onClick = { },
                    label = { Icon(Icons.Default.Settings, contentDescription = null) }
                )
            }
        }
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            /* NavigationRail(
                modifier = Modifier.width(50.dp) ,
                containerColor = MaterialTheme.colors.primary ,
                contentColor = Color.White
             ) {
                NavigationRailItem(
                   selected = false ,
                   onClick = {
                      scope.launch {
                         if (drawerState.isClosed) {
                            drawerState.open()
                         }
                      }
                   } ,
                   icon = { Icon(Icons.Default.Menu , contentDescription = null) }
                )
                NavigationRailItem(
                   selected = false ,
                   onClick = { } ,
                   icon = { Icon(Icons.Default.Message , contentDescription = null) }
                )
                NavigationRailItem(
                   selected = false ,
                   onClick = { } ,
                   icon = { Icon(Icons.Default.Phone , contentDescription = null) }
                )
                NavigationRailItem(
                   selected = false ,
                   onClick = { } ,
                   icon = { Icon(Icons.Default.Settings , contentDescription = null) }
                )
             }*/
            val navController = rememberNavController()
            NavigationRailWithPopupDrawer() {
                WrapperPage {
                    NavigationApp(
                        navigationRail,
                        firstContent = { },
                        secondContent = { }
                    )
                }
            }

        }
    }
    //ExpandingRailDrawer(navigationRail)
}

@Composable
fun WrapperPage(
    content: @Composable() () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize().background(Material1Theme.colorScheme.primary)
            .padding(start = 3.dp, top = 3.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(10.dp, 0.dp, 0.dp, 10.dp))
        ) {
            content.invoke()
        }
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


@Composable
fun ExpandingRailDrawer(navigationRail: NavigationTypeEnum) {
    val scope = rememberCoroutineScope()
    var drawerOpen by remember { mutableStateOf(false) }

    val drawerWidth by animateDpAsState(
        targetValue = if (drawerOpen) 200.dp else 60.dp,
        label = "drawer width"
    )

    Row(Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .width(drawerWidth)
                .fillMaxHeight(),
            color = Material1Theme.colorScheme.primary,
            contentColor = Color.White,
            tonalElevation = 4.dp,
            shadowElevation = 8.dp
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Bouton d'ouverture / fermeture
                IconButton(
                    onClick = {
                        scope.launch { drawerOpen = !drawerOpen }
                    }
                ) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Contenu réduit ou étendu
                NavigationRailItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(Icons.Default.Message, contentDescription = null)
                    },
                    label = if (drawerOpen) {
                        { Text("Messages") }
                    } else null,
                    alwaysShowLabel = false
                )
                NavigationRailItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(Icons.Default.Phone, contentDescription = null)
                    },
                    label = if (drawerOpen) {
                        { Text("Appels") }
                    } else null,
                    alwaysShowLabel = false
                )
                NavigationRailItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(Icons.Default.Settings, contentDescription = null)
                    },
                    label = if (drawerOpen) {
                        { Text("Réglages") }
                    } else null,
                    alwaysShowLabel = false
                )
            }
        }

        // Zone de contenu
        val navController = rememberNavController()
        WrapperPage {
            NavigationApp(
                navigationRail,
                firstContent = {},
                secondContent = { }
            )
        }
    }
}


@Composable
fun NavigationRailWithPopupDrawer(
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    var isPopupOpen by remember { mutableStateOf(false) }
    val targetWidth = if (isPopupOpen) 200.dp else 50.dp
    val width by animateDpAsState(
        targetValue = targetWidth,
        animationSpec = tween(durationMillis = 500),
    )
    var selected by remember { mutableStateOf(false) }
    Box(Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.background(Material1Theme.colorScheme.primary.copy(alpha = 0.7f))
        ) {

            AppNavigationRail {
                CustomNavigationRailItem(
                    icon = Icons.Outlined.Menu,
                ) {
                    isPopupOpen = !isPopupOpen
                }
                CustomNavigationRailItem(
                    selected = true,
                    icon = Icons.Outlined.Message,
                    badgeContent = "3"
                ) {

                }
                CustomNavigationRailItem(
                    icon = Icons.Outlined.Phone,
                    badgeContent = ""
                ) {

                }
                CustomNavigationRailItem(
                    icon = Icons.Outlined.Settings,
                    badgeContent = "35+"
                ) {

                }
                CustomNavigationRailItem(
                    icon = Icons.Outlined.Settings,
                    badgeContent = "35"
                ) {

                }
            }
            Box(
                Modifier.fillMaxSize().clickable(
                enabled = isPopupOpen,
                role = Role.Button
            ) { isPopupOpen = false }.indication(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
            ) {
                content()
            }
        }
        if (width > 50.dp) {
            AppNavigationRail(
                modifier = Modifier.width(width),
                containerColor = Material1Theme.colorScheme.primary
            ) {
                CustomNavigationRailItem(
                    icon = Icons.Outlined.Menu
                ) {
                    isPopupOpen = !isPopupOpen
                }
                CustomExpensiveNavItem(
                    icon = Icons.Outlined.Message,
                    selected = true,
                    label = "Discussion",
                    badgeContent = "3"
                ) {
                    isPopupOpen = !isPopupOpen
                }
                CustomExpensiveNavItem(
                    icon = Icons.Outlined.Phone,
                    label = "Contact ",
                    badgeContent = ""
                ) {
                    isPopupOpen = !isPopupOpen
                }
                CustomExpensiveNavItem(
                    icon = Icons.Outlined.Settings,
                    label = "Settings",
                    badgeContent = "35+"
                ) {
                    isPopupOpen = !isPopupOpen
                }
            }
            /*   NavigationRail(
                  modifier = Modifier.offset(x = 0.dp) // Juste à droite du rail
                     .width(width)
                     .fillMaxHeight() ,
                  containerColor = Material1Theme.colorScheme.primary.copy(alpha = 0.9f) ,
                  contentColor = Color.White
               ) {
                  Column(
                     modifier = Modifier.padding(horizontal = 4.5.dp , vertical = 4.dp) ,
                     horizontalAlignment = Alignment.Start ,
                     verticalArrangement = Arrangement.spacedBy(5.dp)
                  ) {
                     *//* Row(
                   modifier = Modifier.size(35.dp).clip(RoundedCornerShape(5.dp))
                      .background(Color.Gray.copy(0.5f)).clickable { isPopupOpen = !isPopupOpen } ,
                   verticalAlignment = Alignment.CenterVertically
                ) {
                   Divider(
                      modifier = Modifier.height(15.dp).width(3.dp).clip(RoundedCornerShape(2.dp))
                         .background(Color.Green)
                   )
                   Box(
                      modifier = Modifier.fillMaxSize().padding(end = 3.dp) ,
                      contentAlignment = Alignment.Center
                   ) {
                      BadgedBox(
                         badge = {

                         }
                      ) {
                         Icon(
                            Icons.Default.Menu ,
                            contentDescription = null ,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                         )
                      }
                   }
                }*//*
               CustomNavigationRailItem(
                  icon = Icons.Outlined.Menu
               ) {
                  isPopupOpen = !isPopupOpen
               }
               CustomExpensiveNavItem(
                  icon = Icons.Outlined.Message ,
                  selected = true ,
                  label = "Discussion" ,
                  badgeContent = "3"
               ) {
                  isPopupOpen = !isPopupOpen
               }
               CustomExpensiveNavItem(
                  icon = Icons.Outlined.Phone ,
                  label = "Contact " ,
                  badgeContent = ""
               ) {
                  isPopupOpen = !isPopupOpen
               }
               CustomExpensiveNavItem(
                  icon = Icons.Outlined.Settings ,
                  label = "Settings" ,
                  badgeContent = "35+"
               ) {
                  isPopupOpen = !isPopupOpen
               }

            }
         }*/
            /*
             Column(
                modifier = Modifier
                   .offset(x = 0.dp) // Juste à droite du rail
                   .width(width)
                   .fillMaxHeight()
                   .background(Material3Theme.colorScheme.primary.copy(alpha = 0.9f))
             ) {

                Row(modifier = Modifier.clip(RoundedCornerShape(5.dp)).background(Color.Gray.copy(0.5f)).clickable{isPopupOpen = !isPopupOpen }, verticalAlignment = Alignment.CenterVertically){
                   Divider(modifier = Modifier.height(15.dp).width(3.dp).clip( RoundedCornerShape(2.dp)).background(Color.Green))
                   Row(modifier = Modifier.padding(5.dp)){
                      Icon(Icons.Default.Menu , contentDescription = null, modifier = Modifier.size(20.dp))
                   }
                   Spacer(modifier = Modifier.width( 25.dp))
                   Text("bonjour le monde ")
                }

                NavigationDrawerItem(
                   selected = false ,
                   onClick = {  isPopupOpen = !isPopupOpen } ,
                   icon = { Icon(Icons.Default.Menu , contentDescription = null)
                   } ,
                   label = { "null" }
                )
                NavigationDrawerItem(
                   selected = false ,
                   onClick = { } ,
                   icon = { Icon(Icons.Default.Message , contentDescription = null) } ,
                   label = { "null" }
                )
                NavigationDrawerItem(
                   selected = false ,
                   onClick = { } ,
                   icon = { Icon(Icons.Default.Phone , contentDescription = null) } ,
                   label = { null }
                )
             }*/

        }
    }
}

@Composable
private fun AppNavigationRail(
    containerColor: Color = Material1Theme.colorScheme.primary,
    contentColor: Color = Color.White,
    containerModifier: Modifier = Modifier.padding(3.dp),
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(5.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable () -> Unit,
) {
    NavigationRail(
        modifier = Modifier.then(modifier).offset(x = 0.dp)
            .width(45.dp)
            .fillMaxHeight(),
        containerColor = containerColor,
        contentColor = contentColor
    ) {
        Column(
            modifier = containerModifier,
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = verticalArrangement
        ) {
            content.invoke()
        }
    }
}

@Composable
private fun CustomNavigationRailItem(
    selected: Boolean = false,
    modifier: Modifier = Modifier,
    icon: ImageVector,
    badgeContent: String? = null,
    contentColor: Color = Color.White,
    badgeColor: Color = Color.Black,
    badgeTextStyle: TextStyle = TextStyle(
        color = badgeColor,
        fontWeight = FontWeight.Bold,
        fontSize = if (!badgeContent.isNullOrEmpty()) 9.sp else 4.sp,
        textAlign = TextAlign.Center
    ),
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier.height(35.dp).width(40.dp).clip(RoundedCornerShape(5.dp))
            .background(if (selected) Color.Gray.copy(0.5f) else Color.Transparent)
            .clickable(onClick = onClick).then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.compose.animation.AnimatedVisibility(selected) {
            Divider(
                modifier = Modifier.height(15.dp).width(3.dp).clip(RoundedCornerShape(2.dp))
                    .background(if (selected) Color.Green else Color.Transparent)
            )
        }
        AppBadgeBox(
            badgeContent = badgeContent,
            color = badgeColor,
            textStyle = badgeTextStyle,
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
private fun AppBadgeBox(
    badgeContent: String? = null,
    enable: Boolean = badgeContent != null,
    containerColor: Color = Color.Green,
    color: Color = Color.Black,
    textStyle: TextStyle = TextStyle(
        color = color,
        fontWeight = FontWeight.Bold,
        fontSize = if (!badgeContent.isNullOrEmpty()) 9.sp else 4.sp,
        textAlign = TextAlign.Center
    ),
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize().padding(end = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        content.invoke()
        if (enable) {
            Box(
                modifier = Modifier.fillMaxSize()/*.padding(start = 10.dp , bottom = 20.dp, top = 2.dp)*/,
                contentAlignment = Alignment.TopEnd
            ) {
                AppBadge(badgeContent, containerColor, color)
            }

        }

    }
}

@Composable
private fun AppBadge(
    badgeContent: String?,
    containerColor: Color,
    color: Color = Color.Black,
    textStyle: TextStyle = TextStyle(
        color = color,
        fontWeight = FontWeight.Bold,
        fontSize = if (!badgeContent.isNullOrEmpty()) 9.sp else 4.sp,
        textAlign = TextAlign.Center
    ),
) {
    if (!badgeContent.isNullOrEmpty() && badgeContent.length > 1) {
        Box(
            modifier = Modifier.padding(start = 10.dp, top = 4.dp).clip(CircleShape)
                .background(containerColor).padding(horizontal = 2.dp),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = badgeContent,
                style = textStyle,
                maxLines = 1
            )
        }

    } else {
        Box(
            modifier = Modifier.padding(start = 8.5.dp, top = 4.dp)
                .size(if (!badgeContent.isNullOrEmpty()) 14.dp else 8.dp).clip(CircleShape)
                .background(Color.Green).padding(0.5.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                //modifier = Modifier.fillMaxHeight() ,
                text = badgeContent.toString(),
                style = textStyle,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun CustomExpensiveNavItem(
    modifier: Modifier = Modifier,
    label: String,
    badgeContent: String? = null,
    icon: ImageVector,
    selected: Boolean = false,
    contentColor: Color = Color.White,
    badgeColor: Color = Color.Black,
    textStyle: TextStyle = TextStyle(color = contentColor, fontWeight = FontWeight.Bold),
    badgeTextStyle: TextStyle = TextStyle(color = contentColor, fontSize = 10.sp),
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier.height(35.dp).width(200.dp).clip(RoundedCornerShape(5.dp))
            .background(if (selected) Color.Gray.copy(0.5f) else Color.Transparent)
            .clickable(onClick = onClick).then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.compose.animation.AnimatedVisibility(selected) {
            Divider(
                modifier = Modifier.height(15.dp).width(3.dp).clip(RoundedCornerShape(2.dp))
                    .background(if (selected) Color.Green else Color.Transparent)
            )
        }
        Row(
            modifier = Modifier.size(35.dp).padding(end = 3.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(18.dp)
            )
        }
        Row(
            modifier = Modifier.width(150.dp).padding(end = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = label, maxLines = 1, style = textStyle, overflow = TextOverflow.Ellipsis)

            if (badgeContent != null) {
                AppBadge(
                    badgeContent = badgeContent,
                    containerColor = Color.Green
                )
            }
        }
    }
}
