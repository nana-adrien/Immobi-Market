package empire.digiprem.presentation.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.background_immeuble
import empire.digiprem.navigation.EnumView
import empire.digiprem.navigation.ViewPropertyAddProperty
import empire.digiprem.presentation.base.AppAnimations.slideInStart
import empire.digiprem.presentation.base.AppAnimations.slideOutStart
import empire.digiprem.presentation.components.AppScrollableDialog
import empire.digiprem.presentation.components.AppVerticalScrollBar
import empire.digiprem.presentation.components.app.RealEstateItem2
import empire.digiprem.presentation.components.app.RealEstateType
import empire.digiprem.presentation.components.app.equipmentes
import empire.digiprem.presentation.components.app.generateFakeRealEstateListCameroon
import empire.digiprem.presentation.components.wrapper.PageWrapperState
import empire.digiprem.presentation.components.wrapper.WebDesktopPageWrapper
import empire.digiprem.presentation.intents.HomeIntent
import empire.digiprem.presentation.viewmodels.PropertyAddPropertyViewModel
import empire.digiprem.ui.Screen.DetailRealEstateItemScreen
import empire.digiprem.ui.Screen.dashboard_screen.PageSection
import empire.digiprem.ui.Screen.dashboard_screen.screens.mes_annonces.BienImmobilierViewModel
import empire.digiprem.ui.Screen.dashboard_screen.screens.mes_annonces.Etape1_InfoGenerales
import empire.digiprem.ui.Screen.dashboard_screen.screens.mes_annonces.Etape2_Localisation
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PropertyAddPropertyView(
    viewPropertyAddProperty: ViewPropertyAddProperty,
    navController: NavHostController,
    addpropertyViewModel: PropertyAddPropertyViewModel = koinViewModel()
) {
    // val addpropertyViewModel:PropertyAddPropertyViewModel = viewModel{PropertyAddPropertyViewModel()}
    val state by addpropertyViewModel.state.collectAsState()
    val onSendIntent = addpropertyViewModel::onIntentHandler
    val scrollState = rememberScrollState(0)
    WebDesktopPageWrapper(
        modifier = Modifier,
        view = EnumView.ViewPropertyAddProperty,
        state = PageWrapperState(isSuccess = true)
    ){
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .verticalScroll(scrollState), contentAlignment = _root_ide_package_.androidx.compose.ui.Alignment.Center
            ) {
                var stapes: List<Stape> by remember {
                    mutableStateOf(
                        listOf(
                            Stape(title = "bonjour") { Etape1_InfoGenerales() },
                            Stape(title = "bonsoir") { Etape2_Localisation(BienImmobilierViewModel()) },
                            Stape(title = "apres") { Etape1_InfoGenerales() },
                            Stape(title = "encore") { Etape1_InfoGenerales() },
                            Stape(title = "tout") { Etape1_InfoGenerales() },
                            Stape(title = "toutf") { Etape1_InfoGenerales() },
                        )
                    )
                }
                val pagerState = rememberPagerState(0) { stapes.size }
                var currentStape: Int by remember {
                    mutableStateOf(
                        0
                    )
                }
                val scope = rememberCoroutineScope()

                Column(
                    modifier = Modifier.width(900.dp).height(600.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(25.dp)
                ) {
                    Text(
                        text = if (currentStape <= stapes.size - 1) stapes[currentStape].title else "Terminer",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    StapeComponent(
                        currentStape = currentStape,
                        stapes = stapes,
                        onClick = {
                            scope.launch {
                                currentStape = it
                                pagerState.scrollToPage(currentStape)
                            }
                        }
                    )
                    HorizontalPager(
                        modifier = Modifier
                            .fillMaxSize(),
                        state = pagerState
                    ) {
                        Column(
                            modifier = Modifier.wrapContentSize(),
                            verticalArrangement = Arrangement.spacedBy(
                                15.dp
                            )
                        ) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                stapes.get(it).content()
                            }
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                                    .padding(top = 16.dp),
                            ) {
                                Box(
                                    modifier = Modifier.weight(
                                        1f
                                    ), contentAlignment = Alignment.CenterStart
                                ) {
                                    if (currentStape > 0) {
                                        Button(
                                            modifier = Modifier.align(
                                                Alignment.CenterStart
                                            ),
                                            onClick = {
                                                scope.launch {
                                                    stapes = stapes.mapIndexed { index, state ->
                                                        if (index == currentStape) {
                                                            state.copy(hasCompleted = false)
                                                        } else {
                                                            state
                                                        }
                                                    }
                                                    currentStape--
                                                    pagerState.scrollToPage(currentStape)
                                                }


                                            }) {
                                            Text("Précédent")
                                        }
                                    }
                                }
                                Box(
                                    modifier = Modifier.weight(
                                        1f
                                    ), contentAlignment = Alignment.CenterEnd
                                ) {
                                    if (currentStape < stapes.size) {
                                        Button(onClick = {
                                            scope.launch {
                                                stapes = stapes.mapIndexed { index, state ->
                                                    if (index == currentStape) {
                                                        state.copy(hasCompleted = true)
                                                    } else {
                                                        state
                                                    }
                                                }
                                                currentStape++
                                                pagerState.scrollToPage(currentStape)
                                            }
                                        }) {
                                            Text("Suivant")
                                        }
                                    } else {
                                        Button(
                                            onClick = {
                                                // TODO: Envoyer les données
                                            }) {
                                            Text("Soumettre")
                                        }
                                    }
                                }


                            }
                        }
                    }
                }
            }
            AppVerticalScrollBar(
                modifier = Modifier.align(Alignment.CenterEnd),
                scrollState = scrollState,
            )
        }
    }
}

data class Stape(
    val title: String,
    val hasCompleted: Boolean = false,
    val content: @Composable () -> Unit
)

@Composable
private fun StapeComponent(
    currentStape: Int,
    stapes: List<Stape>,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().width(500.dp),
        verticalAlignment = _root_ide_package_.androidx.compose.ui.Alignment.CenterVertically,
        horizontalArrangement = _root_ide_package_.androidx.compose.foundation.layout.Arrangement.Center
    ) {
        stapes.forEachIndexed { index, stape ->
            var textColor = _root_ide_package_.androidx.compose.ui.graphics.Color.White
            val color = if (index == currentStape) {
                textColor =
                    _root_ide_package_.androidx.compose.ui.graphics.Color.White; _root_ide_package_.androidx.compose.ui.graphics.Color.Blue
            } else {
                if (stape.hasCompleted) _root_ide_package_.androidx.compose.ui.graphics.Color.Green else {
                    textColor =
                        _root_ide_package_.androidx.compose.ui.graphics.Color.Black; _root_ide_package_.androidx.compose.ui.graphics.Color.Gray.copy(
                        alpha = 0.5f
                    )
                }
            }
            _root_ide_package_.androidx.compose.foundation.layout.Row(
                modifier = _root_ide_package_.androidx.compose.ui.Modifier.wrapContentSize(),
                verticalAlignment = _root_ide_package_.androidx.compose.ui.Alignment.CenterVertically,
                horizontalArrangement = _root_ide_package_.androidx.compose.foundation.layout.Arrangement.Center
            ) {
                if (index != 0) {
                    _root_ide_package_.androidx.compose.material3.HorizontalDivider(
                        modifier = _root_ide_package_.androidx.compose.ui.Modifier.width(
                            80.dp
                        ).height(5.dp).background(color)
                    )
                }
                Box(
                    modifier = _root_ide_package_.androidx.compose.ui.Modifier.size(50.dp)
                        .clip(_root_ide_package_.androidx.compose.foundation.shape.CircleShape).background(color)
                        .clickable(enabled = stape.hasCompleted) { onClick(index) },
                    contentAlignment = _root_ide_package_.androidx.compose.ui.Alignment.Center
                ) {
                    _root_ide_package_.androidx.compose.material3.Text("${index + 1}", color = textColor)
                }
            }
        }
    }
}
