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
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
    topBarModifier: Modifier= Modifier.height(80.dp),
    view: EnumView?=null,
    state: PageWrapperState,
    enabledTobAppBar: Boolean = true,
    containerColor: Color = Color.Transparent,
    contentColor: Color = contentColorFor(containerColor),
    actions: @Composable() (RowScope.() -> Unit) = {},
    customTopAppBar: @Composable() ((TopAppBarScrollBehavior?) -> Unit)? =null,
    secondTopBar: @Composable() () -> Unit={},
    content: @Composable() (PaddingValues) -> Unit
) {
   // val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.padding(top = 50.dp).then(modifier),//.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = containerColor,
        contentColor = contentColor,
        topBar = {
            if(enabledTobAppBar){
               customTopAppBar?.let {
                 //  it(scrollBehavior)
               }?: TopAppBar(
                   modifier =Modifier.padding(vertical = 20.dp).then(topBarModifier) ,
                    windowInsets = WindowInsets(0.dp),
                   //scrollBehavior = scrollBehavior,
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
        }
    ) {
        Column(modifier=Modifier.fillMaxSize().padding(it), verticalArrangement = Arrangement.Top){
            secondTopBar()
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (state.isLoading) {
                    //   CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    LoadingScreen()
                } else {
                    if (state.isSuccess && !state.isFailure) {
                        Column(Modifier.fillMaxSize()){
                            content.invoke(PaddingValues(0.dp))
                        }

                    }
                    androidx.compose.animation.AnimatedVisibility(!state.isSuccess && state.isFailure) {
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
}