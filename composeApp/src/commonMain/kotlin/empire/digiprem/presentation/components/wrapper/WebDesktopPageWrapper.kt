package empire.digiprem.presentation.components.wrapper

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import empire.digiprem.navigation.EnumView
import empire.digiprem.presentation.components.AppIconActionButton
import empire.digiprem.presentation.components.LoadingScreen


data class PageWrapperState(
    val isLoading: Boolean=false,
    val isSuccess: Boolean=false,
    val isFailure: Boolean=false,
    val errorMessage: String="",
    val onRefresh: () -> Unit = {},
    val onNavigateTo:(Any)->Unit = {}
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebDesktopPageWrapper(
    modifier: Modifier = Modifier,
    view: EnumView?=null,
    state: PageWrapperState,
    containerColor: Color = Color.Transparent,
    contentColor: Color = contentColorFor(containerColor),
    actions: @Composable() (RowScope.() -> Unit) = {},
    content: @Composable() (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = Modifier.then(modifier).padding(30.dp),
        containerColor = containerColor,
        contentColor = contentColor,
        topBar = {
            TopAppBar(
                windowInsets = WindowInsets(0.dp),
                colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.Transparent),
                title = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        if (view != null) {}
                        view?.let{
                            Text(
                                "${view.getPageTitle()}",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                            )
                            TextButton(
                                onClick = {

                                }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(1.dp),
                                ) {
                                    Icon(
                                        Icons.Filled.ArrowBackIos,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                    Text(
                                        "Dashboard",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    )
                                }

                            }
                        }
                    }
                },
                actions = actions
                 /*   {

                    AppIconActionButton(
                        onClick = {
                            //navController.navigate(ViewHome(isConnected = true))
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterAlt,
                            contentDescription = "Home",
                        )
                    }
                    AppIconActionButton(
                        onClick = {
                            //  navController.navigate(ViewHome(isConnected = true))
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Home",
                        )
                    }
                    AppIconActionButton(
                        onClick = {
                            // navController.navigate(ViewHome(isConnected = true))
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Home",
                        )
                    }
                }*/
            )
        }
    ) {
        Box(modifier = Modifier.padding(it).padding(top = 30.dp).fillMaxSize(), contentAlignment = Alignment.Center) {
            if (state.isLoading) {
                //   CircularProgressIndicator(modifier = Modifier.size(50.dp))
                LoadingScreen()
            } else {
                if (state.isSuccess && !state.isFailure) {
                    content.invoke(PaddingValues(0.dp))
                }
                AnimatedVisibility(!state.isSuccess && state.isFailure) {
                    Column(
                        modifier = Modifier.width(400.dp).height(500.dp)
                            .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp)).background(Color.White),
                    ) {
                        Box(
                            modifier = Modifier.weight(0.6f).fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                modifier = Modifier.fillMaxSize(0.7f),
                                imageVector = Icons.Filled.BreakfastDining,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        Box(modifier = Modifier.weight(0.4f), contentAlignment = Alignment.Center) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                                    verticalArrangement = Arrangement.spacedBy(5.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        "Ooops une erreur est survenue lors du chargement de la page\n cause probable :",
                                        style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center)
                                    )
                                    Text(
                                        "<<${state.errorMessage}>>",
                                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.error)
                                    )
                                }
                                Button(
                                    onClick = state.onRefresh

                                ) {
                                    Text("Retry")
                                    Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}