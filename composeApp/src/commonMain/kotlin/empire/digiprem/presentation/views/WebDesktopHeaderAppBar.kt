package empire.digiprem.presentation.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import composeApp.src.commonMain.ComposeResources.drawable.IM_Plan_de_travail_1
import composeApp.src.commonMain.ComposeResources.drawable.IM_Plan_de_travail_3
import composeApp.src.commonMain.ComposeResources.drawable.Res
import empire.digiprem.config.isCompactApplication
import empire.digiprem.navigation.*
import empire.digiprem.presentation.base.color.Colors
import empire.digiprem.presentation.components.AppHeader
import empire.digiprem.presentation.components.AppIconActionButton
import empire.digiprem.presentation.components.CustomExpensiveNavItem
import empire.digiprem.presentation.components.TopBarAction
import empire.digiprem.presentation.viewmodels.componenet.WebDesktopHeaderViewModel
import empire.digiprem.ui.Screen.dashboard_screen.screens.notifications.Notifications
import octopusfx.client.mobile.core.ui.theme.LocalSessionManager
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WebDesktopHeaderAppBar(
    navController: NavHostController,
    navigationIcon: @Composable () -> Unit = {},
) {
    val sessionManager = LocalSessionManager.current
    val isConnected by sessionManager.isStarted.collectAsState()
    val utilisateur by sessionManager.utilisateur.collectAsState()
    val enabledLogOutDialog by sessionManager.enabledLogOutDialog.collectAsState()
    val viewModel: WebDesktopHeaderViewModel = viewModel { WebDesktopHeaderViewModel(isConnected) }
    val state by viewModel.state.collectAsState()
    var activeTopBarAction by remember { mutableStateOf(TopBarAction()) }
    val pages by remember {
        mutableStateOf(
            mapOf(
                "Accueil" to ViewHome(),
                "Tous les biens immobiliers" to ViewAllRealEstate(),
                "Owner Certifier " to ViewOwnerCertifier(),
                "Signaler un problème" to ViewCompleteAccount()
            )
        )
    }
    var selectedPage by remember { mutableStateOf(pages.entries.first().key) }

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

    AppBox(
        modifier = Modifier.background(Colors.brushVioletLinear)
    ) {
        AppHeader(
            modifier = Modifier.wrapContentSize().padding(vertical = 10.dp),
            navigationIcon = navigationIcon,
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier.width(if (isCompactApplication()) 80.dp else 150.dp)
                            .clickable { navController.navigate(ViewHome()) }
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(if (isCompactApplication()) Res.drawable.IM_Plan_de_travail_1 else Res.drawable.IM_Plan_de_travail_3),
                            contentDescription = "Logo",
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    if (isCompactApplication()) {
                        AppIconActionButton(
                            selected = activeTopBarAction.currentActionName.equals("Menu") && activeTopBarAction.enabled,
                            onClick = {
                                lamd("Menu") {
                                    pages.forEach {
                                        DropdownMenuItem(
                                            onClick = {
                                                navController.navigate(it.value)
                                                selectedPage = it.key
                                            },
                                            text = {
                                                Text(
                                                    it.key,
                                                    style = MaterialTheme.typography.titleMedium.copy(
                                                        color = if (selectedPage == it.key) MaterialTheme.colorScheme.secondary else Color.Black,
                                                        textDecoration = if (selectedPage == it.key) TextDecoration.Underline else TextDecoration.None,
                                                        fontSize = if (selectedPage == it.key) 17.sp else 16.sp,
                                                        fontWeight = if (selectedPage == it.key) FontWeight.W600 else FontWeight.W500
                                                    )
                                                )
                                            }
                                        )
                                    }
                                }
                            },
                        ) {
                            Icon(imageVector = Icons.Outlined.Menu, contentDescription = "", tint = it)
                        }

                    } else {
                        FlowRow(
                            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            pages.forEach {
                                TextButton(
                                    onClick = {
                                        navController.navigate(it.value)
                                        selectedPage = it.key
                                    }
                                ) {
                                    Text(
                                        it.key,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = if (selectedPage == it.key) MaterialTheme.colorScheme.secondary else Color.White,
                                            textDecoration = if (selectedPage == it.key) TextDecoration.Underline else TextDecoration.None,
                                            // fontSize = if (selectedPage == it.key) 14.sp else 14.sp,
                                            fontWeight = if (selectedPage == it.key) FontWeight.W600 else FontWeight.W500
                                        )
                                    )
                                }
                            }
                        }
                    }

                }
            },
            actions = {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (isConnected) {
                        BadgedBox(
                            badge = {
                                androidx.compose.animation.AnimatedVisibility(visible = state.notifications.isNotEmpty()) {
                                    Badge(
                                        containerColor = Color.Red,
                                        contentColor = Color.White
                                    ) {
                                        Text("${state.notifications.size}")
                                    }
                                }
                            }
                        ) {
                            AppIconActionButton(
                                selected = activeTopBarAction.currentActionName.equals("Notifications") && activeTopBarAction.enabled,
                                onClick = {
                                    lamd("Notifications") {
                                        Box(
                                            modifier = Modifier.height(250.dp).width(350.dp)
                                        ) {
                                            Notifications(state.notifications)
                                        }
                                    }
                                },
                            ) {
                                Icon(imageVector = Icons.Outlined.Notifications, contentDescription = "", tint = it)
                            }
                        }

                        AppIconActionButton(
                            selected = activeTopBarAction.currentActionName.equals("Message") && activeTopBarAction.enabled,
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
                                            /*  Conversations(
                                                  navController = navController
                                              )*/
                                        }

                                    }
                                }
                            },
                        ) {
                            Icon(imageVector = Icons.AutoMirrored.Outlined.Message, contentDescription = "", tint = it)
                        }
                        AppIconActionButton(
                            selected = activeTopBarAction.currentActionName.equals("profil") && activeTopBarAction.enabled,
                            onClick = { lamd("profil") { ProfilMenu(navController) {
                                activeTopBarAction = activeTopBarAction.copy(enabled = false)
                            }
                            } },
                        ) {
                            utilisateur?.let {
                                AsyncImage(
                                    model = it.photo,
                                    modifier = Modifier.fillMaxSize(),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                            } ?: Icon(
                                imageVector = Icons.Default.Person, "",
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                    } else {
                        AppButtonEx(
                            onClick = { navController.navigate(ViewRegister()) }
                        ) {
                            Text("S'inscrire", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                        }
                        AppOutinedButtonEx(onClick = { navController.navigate(ViewLogin()) }) {
                            Text("Se connecter", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                        }
                    }
                }



                DropdownMenu(
                    shadowElevation = 0.dp,
                    tonalElevation = 0.dp,
                    containerColor = Color.Transparent,
                    modifier = Modifier.padding(top = 10.dp, end = 20.dp).wrapContentSize()
                        .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                        .background(Color.White),
                    expanded = activeTopBarAction.enabled && isConnected,
                    onDismissRequest = {
                        activeTopBarAction = activeTopBarAction.copy(enabled = false)
                    }
                ) {
                    activeTopBarAction.content()
                }
            }
        )

    }

    AnimatedVisibility(
        enabledLogOutDialog
    ) {
        Dialog(
            onDismissRequest = { },
        ) {
            Column(
                modifier = Modifier.height(350.dp).width(300.dp).padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(7.dp)).background(Color.White).padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text("Session expirée", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(
                        "Votre session a expiré pour des raisons de sécurité.\n" +
                                "Veuillez vous reconnecter pour continuer ou quitter la session encoure.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row( modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    TextButton(
                        onClick = {
                           sessionManager.diseableDialogSessionExpirateMessage()
                            navController.navigate(ViewHome())
                        }
                    ) {
                        Text("Quitter")
                    }
                    TextButton(
                        onClick = {
                            sessionManager.diseableDialogSessionExpirateMessage()
                            navController.navigate(ViewLogin())
                        }
                    ) {
                        Text("Se reconnecter")
                    }
                }
            }
        }
    }

}


@Composable
fun ProfilMenu(navController: NavHostController, onClickItem: () -> Unit={}) {
    val sessionManager = LocalSessionManager.current
    val utilisateur by sessionManager.utilisateur.collectAsState()
    var enabledLogOutDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.wrapContentHeight().width(300.dp).padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(100.dp).shadow(2.dp, shape = MaterialTheme.shapes.medium)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                    shape = MaterialTheme.shapes.medium
                ).background(MaterialTheme.colorScheme.background)
        ) {
            ListItem(
                modifier = Modifier.fillMaxHeight(),
                leadingContent = {
                    Box(modifier = Modifier.size(50.dp).clip(CircleShape)) {
                        utilisateur?.let {
                            AsyncImage(
                                model = it.photo,
                                modifier = Modifier.fillMaxSize(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        } ?: Icon(
                            imageVector = Icons.Default.Person, "",
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                },
                headlineContent = { Text("${utilisateur?.nom}") },
                supportingContent = { Text("${utilisateur?.email}") }
            )
            IconButton(
                modifier = Modifier.padding(top = 10.dp, end = 10.dp).size(30.dp).align(Alignment.TopEnd),
                onClick = {
                    navController.navigate(ViewStatistics())
                }
            ) {
                Icon(
                    imageVector = Icons.Default.LightMode,
                    contentDescription = ""
                )
            }

        }

        Box(
            modifier = Modifier.fillMaxWidth().wrapContentHeight().shadow(2.dp, shape = MaterialTheme.shapes.medium)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                    shape = MaterialTheme.shapes.medium
                ).background(MaterialTheme.colorScheme.background)
        ) {
            CustomExpensiveNavItem(
                selected = true,
                modifier = Modifier.fillMaxWidth().height(50.dp),
                label = "Publier un bien",
                icon = Icons.Default.Publish,
                onClick = {
                    onClickItem()
                    navController.navigate(ViewPropertyAddProperty())
                }
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth().wrapContentHeight()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CustomExpensiveNavItem(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Mon Profile",
                    icon = Icons.Default.Person,
                    onClick = {
                        onClickItem()
                        navController.navigate(ViewProfil())
                    }
                )
                CustomExpensiveNavItem(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Tableau de board",
                    icon = Icons.Default.Dashboard,
                    onClick = {
                        onClickItem()
                        navController.navigate(ViewStatistics())
                    }
                )
                CustomExpensiveNavItem(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Settings",
                    icon = Icons.Default.Settings,
                    onClick = {
                        onClickItem()
                        navController.navigate(ViewStatistics())
                    }
                )
                HorizontalDivider()
                CustomExpensiveNavItem(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Deconnexion",
                    icon = Icons.AutoMirrored.Filled.Logout,
                    onClick = {
                        onClickItem()
                        enabledLogOutDialog = !enabledLogOutDialog
                    }
                )
            }
        }

    }

    AnimatedVisibility(
        enabledLogOutDialog
    ) {
        Dialog(
            onDismissRequest = { enabledLogOutDialog = false },
        ) {
            Column(
                modifier = Modifier.height(250.dp).width(300.dp).padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(7.dp)).background(Color.White).padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text("Deconnection", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text("Vous allez etre deconnecter voulez vous continuer l'operation ? ?", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    TextButton(
                        onClick = {
                            enabledLogOutDialog = false
                        }
                    ){
                        Text("Annuler")
                    }
                    TextButton(
                        onClick = {
                            enabledLogOutDialog = false
                            sessionManager.logOut()
                            navController.navigate(ViewHome())}
                    ){
                        Text("Continuer")
                    }
                }
            }
        }
    }
}

