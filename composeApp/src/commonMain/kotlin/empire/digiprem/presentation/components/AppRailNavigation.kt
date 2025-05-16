package empire.digiprem.presentation.components

import androidx.compose.animation.*
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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import empire.digiprem.config.getActualWindowsSize
import empire.digiprem.core.utils.pointerEvent
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
            /* NavigationRailWithPopupDrawer() {
                 WrapperPage {
                     NavigationApp(
                         navigationRail,
                         firstContent = { },
                         secondContent = { }
                     )
                 }
             }*/

        }
    }
    //ExpandingRailDrawer(navigationRail)
}

@Composable
fun WrapperPage(
    enabled: Boolean,
    content: @Composable () -> Unit,
) {
    val modifier1 = if (enabled) Modifier.fillMaxSize().background(Material1Theme.colorScheme.surfaceVariant)
        .padding(start = 3.dp, top = 3.dp) else Modifier.fillMaxSize().background(Material1Theme.colorScheme.background)

    Box(
        modifier = modifier1
    ) {
        val modifier = if (enabled) Modifier.fillMaxSize()
            .clip(RoundedCornerShape(10.dp, 0.dp, 0.dp, 10.dp)) else Modifier.fillMaxSize()
        Box(
            modifier = modifier
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
fun HoverTooltip() {
    var showTooltip by remember { mutableStateOf(false) }
    var cursorOffset by remember { mutableStateOf(Offset.Zero) }

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
        WrapperPage(true) {
            NavigationApp(
                navigationRail,
                firstContent = {},
                secondContent = { }
            )
        }
    }
}


data class NavigationItem(
    val label: String,
    val selected: Boolean = false,
    val enableExpenciveItem: Boolean = true,
    val modifier: Modifier = Modifier,
    val icon: ImageVector,
    val badgeContent: String? = null,
    val contentColor: Color = Color.White,
    val badgeColor: Color = Color.Black,
    val textStyle: TextStyle = TextStyle(color = contentColor, fontWeight = FontWeight.Bold),
    val badgeTextStyle: TextStyle = TextStyle(
        color = badgeColor,
        fontWeight = FontWeight.Bold,
        fontSize = if (!badgeContent.isNullOrEmpty()) 9.sp else 4.sp,
        textAlign = TextAlign.Center
    ),
    val subNavigationItem: List<NavigationItem> = emptyList(),
    val onClick: () -> Unit
)

@Composable
fun NavigationRailWithPopupDrawer(
    enableNavRail: Boolean = true,
    enabledExpensiveMenu: Boolean = false,
    isPopupOpen: Boolean = false,
    navigationItems: List<NavigationItem>,
    onClickFloatingActionButton: () -> Unit = {},
    topBar: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    // var isPopupOpen by remember { mutableStateOf(false) }
    val targetWidth = if (isPopupOpen) 150.dp else 50.dp
    val width by animateDpAsState(
        targetValue = targetWidth,
        animationSpec = tween(durationMillis = 500),
    )

    val windowSizeClass = getActualWindowsSize().widthSizeClass

    val appContent = @Composable {
        Box(
            Modifier.fillMaxSize().background(Material1Theme.colorScheme.background)/*.clickable(
                        enabled = isPopupOpen,
                        role = Role.Button
                    ) { isPopupOpen = false }*/.indication(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
        ) {
            content()
        }
    }

    Column(Modifier.fillMaxSize()) {
        when (windowSizeClass) {
            WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
                var showPopup by remember { mutableStateOf(false) }
                var buttonPosition by remember { mutableStateOf(Offset.Zero) }
                var popupData by remember { mutableStateOf(PopupData()) }
                var popupContent by remember { mutableStateOf<List<NavigationItem>>(emptyList()) }
                var popupTitle by remember { mutableStateOf("") }

                Row /*(Modifier.padding(3.dp))*/{
                    if (enableNavRail) {
                        topBar.invoke()
                    }
                }

                Box(modifier = Modifier.wrapContentSize()) {
                    Row(
                        modifier = Modifier//.background(Material1Theme.colorScheme.primary.copy(alpha = 0.7f))
                    ) {
                        Box(modifier = Modifier.wrapContentSize()) {
                            if (enableNavRail) {
                                AppNavigationRail {
                                    navigationItems.forEach {
                                        var offset = Offset.Zero
                                        CustomNavigationRailItem(
                                            modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                                                offset = layoutCoordinates.localToWindow(Offset.Zero)
                                            }.pointerEvent(PointerEventType.Enter) { ti ->
                                                popupData = popupData.copy(
                                                    enable = true,
                                                    offset = Offset(x = offset.x + 60, y = offset.y - 80),
                                                    content = {
                                                        Column(modifier = Modifier.fillMaxWidth()) {
                                                            Text(
                                                                it.label, style = TextStyle(
                                                                    color = Material1Theme.colorScheme.primary.copy(
                                                                        alpha = 0.8f
                                                                    ),
                                                                    fontWeight = FontWeight.Bold
                                                                )
                                                            )
                                                            if (it.subNavigationItem.isNotEmpty()) {
                                                                HorizontalDivider()
                                                                it.subNavigationItem.forEach { item ->
                                                                    CustomExpensiveNavItem(
                                                                        modifier = item.modifier,
                                                                        label = item.label,
                                                                        selected = item.selected,
                                                                        icon = item.icon,
                                                                        badgeContent = item.badgeContent,
                                                                        // contentColor = item.contentColor,
                                                                        badgeColor = item.badgeColor,
                                                                        badgeTextStyle = item.badgeTextStyle,
                                                                        // textStyle = item.textStyle,
                                                                        onClick = {
                                                                            item.onClick()
                                                                            popupData = popupData.copy(
                                                                                enable = false,
                                                                            )
                                                                        }
                                                                    )

                                                                }
                                                            }
                                                        }
                                                    }
                                                )
                                            }.pointerEvent(PointerEventType.Exit) {
                                                popupData = popupData.copy(
                                                    enable = false,
                                                )
                                            },
                                            selected = if (it.subNavigationItem.isNotEmpty()) it.subNavigationItem.any { it.selected } else it.selected,
                                            icon = it.icon,
                                            badgeContent = it.badgeContent,
                                            // contentColor = it.contentColor,
                                            badgeColor = it.badgeColor,
                                            badgeTextStyle = it.badgeTextStyle,
                                            onClick = it.onClick,
                                        )
                                    }
                                }
                                if (width > 50.dp) {
                                    AppNavigationRail(
                                        modifier = Modifier.width(width)
                                    ) {
                                        navigationItems.forEach {
                                            if (!it.enableExpenciveItem) {
                                                CustomNavigationRailItem(
                                                    modifier = it.modifier,
                                                    selected = if (it.subNavigationItem.isNotEmpty()) it.subNavigationItem.any { it.selected } else it.selected,
                                                    icon = it.icon,
                                                    badgeContent = it.badgeContent,
                                                    //contentColor = it.contentColor,
                                                    badgeColor = it.badgeColor,
                                                    badgeTextStyle = it.badgeTextStyle,
                                                    onClick = it.onClick
                                                )
                                            } else {
                                                CustomExpensiveNavItem(
                                                    modifier = it.modifier,
                                                    label = it.label,
                                                    selected = if (it.subNavigationItem.isNotEmpty()) it.subNavigationItem.any { it.selected } else it.selected,
                                                    icon = it.icon,
                                                    badgeContent = it.badgeContent,
                                                    // contentColor = it.contentColor,
                                                    // badgeColor = it.badgeColor,
                                                    badgeTextStyle = it.badgeTextStyle,
                                                    //textStyle = it.textStyle,
                                                    onClick = it.onClick,
                                                    subNavItem = if (it.subNavigationItem.isEmpty()) null else {
                                                        {
                                                            it.subNavigationItem.forEach { item ->
                                                                CustomExpensiveNavItem(
                                                                    modifier = item.modifier,
                                                                    isSubMenuItem = true,
                                                                    label = item.label,
                                                                    selected = item.selected,
                                                                    icon = item.icon,
                                                                    badgeContent = item.badgeContent,
                                                                    // contentColor = item.contentColor,
                                                                    badgeColor = item.badgeColor,
                                                                    badgeTextStyle = item.badgeTextStyle,
                                                                    // textStyle = item.textStyle,
                                                                    onClick = item.onClick
                                                                )
                                                            }
                                                        }
                                                    }
                                                )
                                            }

                                        }
                                    }
                                }
                            }
                        }
                        WrapperPage(
                            enabled = enableNavRail
                        ) {
                            appContent()
                        }
                    }

                    popupTitle = ""
                    popupContent = emptyList()
                    showPopup = false
                    val content = @Composable {

                    }
                    val offset = Offset(x = buttonPosition.x + 60, y = buttonPosition.y - 80)

                    val onPointerEnterEvent = {}
                    val onPointerExitEvent = {}

                    AppPopup(
                        popupData.enable,
                        offset = popupData.offset,
                        color = Color.White,
                        onPointerEnterEvent = {
                            popupData = popupData.copy(enable = true)
                        },
                        onPointerExitEvent = {
                            popupData = popupData.copy(enable = false)
                        },
                        content = popupData.content
                    )
                }

            }

            WindowWidthSizeClass.Compact -> {
                Scaffold(
                    topBar = {
                        if (enableNavRail) {
                            topBar.invoke()
                        }
                    },
                    bottomBar = {
                        if (enableNavRail) {
                            Box(Modifier.fillMaxWidth()){
                                FloatingActionButton(
                                    modifier = Modifier.padding(bottom =40.dp),
                                    onClick = onClickFloatingActionButton
                                ) {
                                    Icon(
                                        Icons.Default.ShoppingCart, ""
                                    )
                                }
                                Box(modifier=Modifier.fillMaxWidth().align(Alignment.BottomCenter)){
                                    AppBottomBar {
                                        navigationItems.forEach {
                                            Column(
                                                modifier = Modifier.weight(1f).height(60.dp)
                                                    .background(if (it.selected) Material1Theme.colorScheme.primary else Color.Transparent)
                                                    .clickable { it.onClick() }.padding(5.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                Icon(
                                                    modifier = Modifier.size(16.dp),
                                                    imageVector = it.icon,
                                                    contentDescription = "",
                                                    tint = if (it.selected) Material1Theme.colorScheme.surfaceVariant else Material1Theme.colorScheme.primary,
                                                )
                                                Text(
                                                    it.label.split(" ").last(),
                                                    style = Material1Theme.typography.bodySmall.copy(
                                                        color = if (it.selected) Material1Theme.colorScheme.surfaceVariant else Color.Black,
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }

                            }

                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                    floatingActionButton = {
                        if (enableNavRail) {

                        }
                    }
                ) {
                    appContent()
                }
            }

        }
    }
}


data class PopupData(
    val enable: Boolean = false,
    val offset: Offset = Offset.Zero,
    val backgroundColor: Color = Color.White,
    val onPointerEnterEvent: () -> Unit = {},
    val onPointerExitEvent: () -> Unit = {},
    val content: @Composable () -> Unit = {},
)

@Composable
fun AppPopup(
    enabled: Boolean,
    offset: Offset,
    color: Color,
    onPointerEnterEvent: () -> Unit,
    onPointerExitEvent: () -> Unit,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        enabled,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut(animationSpec = tween(durationMillis = 300, delayMillis = 500)) +
                slideOutVertically(animationSpec = tween(durationMillis = 300, delayMillis = 2000))
    ) {
        val density = LocalDensity.current
        val xDp = with(density) { offset.x.toDp() }
        val yDp = with(density) { offset.y.toDp() }

        Box(
            modifier = Modifier.absoluteOffset(x = xDp, y = yDp)
                .wrapContentHeight()
                .width(150.dp)
                .background(color, shape = RoundedCornerShape(8.dp))
                .padding(5.dp).pointerEvent(PointerEventType.Enter) { ti ->
                    onPointerEnterEvent()
                }.pointerEvent(PointerEventType.Exit) {
                    onPointerExitEvent()
                }
        ) {
            content()
        }
    }
}


@Composable
private fun AppBottomBar(
    content: @Composable RowScope.() -> Unit,
) {
    Column {
        HorizontalDivider()
        Row(
            modifier = Modifier.wrapContentHeight().fillMaxWidth().background(Material1Theme.colorScheme.background),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            content()
        }
    }

}

@Composable
private fun AppNavigationRail(
    containerColor: Color = Material1Theme.colorScheme.surfaceVariant,
    contentColor: Color = Color.Black,
    containerModifier: Modifier = Modifier.padding(3.dp),
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(5.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable () -> Unit,
) {
    // Box(modifier=Modifier.wrapContentSize().background(Color.Gray).padding(top = 1.dp, end = 1.dp)){
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
    //}
}

@Composable
fun CustomNavigationRailItem(
    selected: Boolean = false,
    modifier: Modifier = Modifier,
    icon: ImageVector,
    badgeContent: String? = null,
    contentColor: Color = if (selected) Color.White else Material1Theme.colorScheme.primary,
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
        modifier = Modifier.then(modifier).height(35.dp).width(40.dp).clip(RoundedCornerShape(5.dp))
            .background(if (selected) Material1Theme.colorScheme.primary /*Color.Gray.copy(0.5f)*/ else Color.Transparent)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.compose.animation.AnimatedVisibility(selected) {
            VerticalDivider(
                modifier = Modifier.height(15.dp).width(3.dp).clip(RoundedCornerShape(2.dp))
                    .background(if (selected) Material1Theme.colorScheme.background else Color.Transparent)
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
    isSubMenuItem: Boolean = false,
    label: String,
    badgeContent: String? = null,
    icon: ImageVector,
    selected: Boolean = false,
    contentColor: Color = if (selected) Color.White else Material1Theme.colorScheme.primary,
    badgeColor: Color = Color.Black,
    textStyle: TextStyle = TextStyle(
        color = if (selected) contentColor else contentColor.copy(alpha = 0.5f),
        fontWeight = FontWeight.Bold
    ),
    badgeTextStyle: TextStyle = TextStyle(color = contentColor, fontSize = 10.sp),
    onClick: () -> Unit,
    subNavItem: @Composable (() -> Unit)? = null,
) {
    var enabledSubNavItem by remember { mutableStateOf(false) }
    val onClickItem = {
        if (subNavItem != null) {
            enabledSubNavItem = !enabledSubNavItem
        } else onClick()
    }
    Column {
        Row(
            modifier = Modifier.padding(start = if (isSubMenuItem) 10.dp else 0.dp).height(35.dp).width(200.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(if (selected) Material1Theme.colorScheme.primary.copy(alpha = if (isSubMenuItem) 0.5f else 1f) /*Color.Gray.copy(0.5f)*/ else Color.Transparent)
                .clickable(onClick = onClickItem).then(modifier),
            verticalAlignment = Alignment.CenterVertically
        ) {
            androidx.compose.animation.AnimatedVisibility(selected) {
                VerticalDivider(
                    modifier = Modifier.height(15.dp).width(3.dp).clip(RoundedCornerShape(2.dp))
                        .background(if (selected) Material1Theme.colorScheme.background else Color.Transparent)
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
            if (subNavItem != null) {
                Row(
                    modifier = Modifier.size(35.dp).padding(end = 3.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (enabledSubNavItem) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropUp,
                        contentDescription = ""
                    )
                }
            }
        }
        androidx.compose.animation.AnimatedVisibility(subNavItem != null && enabledSubNavItem == true) {
            Column(
                modifier = Modifier.width(200.dp)
                    .background(Material1Theme.colorScheme.surfaceVariant.copy(alpha = 0.8f)).padding(end = 10.dp)
                    .padding(vertical = 10.dp)/*.background(Color.Black.copy(alpha = 0.3f))*/
            ) {
                subNavItem?.invoke()
            }
        }
    }

}
