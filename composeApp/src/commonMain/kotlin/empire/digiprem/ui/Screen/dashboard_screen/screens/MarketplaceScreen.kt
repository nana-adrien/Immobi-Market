package empire.digiprem.ui.Screen.dashboard_screen.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
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
import empire.digiprem.ui.Screen.DetailRealEstateItemScreen
import empire.digiprem.navigation.Home
import empire.digiprem.ui.Screen.dashboard_screen.*
import empire.digiprem.presentation.components.AppScrollableDialog
import empire.digiprem.presentation.components.AppVerticalScrollBar
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketplaceScreen(navigationController: NavHostController, homeState: Home) {

    //val route=navigationController.currentBackStackEntryAsState().value?.toRoute<Produits>()

    var enabledPageDetail by remember { mutableStateOf(false) }
    val state = rememberScrollState(homeState.scrollPosition)
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

        Box(modifier = Modifier.fillMaxSize().verticalScroll(state), contentAlignment = Alignment.Center) {
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

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
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

            Column(
                Modifier.padding(top = 700.dp).fillMaxWidth(0.7f).align(Alignment.Center).zIndex(0f),
            ) {
                PageSection(
                    title = "Les plus visites",
                    modifier =
                        Modifier.nestedScroll(connection = nestedScrollConnection), // Parallax ou suivi partiel
                    state = gridState,
                ) {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        val liste = generateFakeRealEstateListCameroon().subList(0, 5)
                        liste.forEach {
                            RealEstateItem2(
                                location = it.location,
                                postedAgo = it.postedAgo,
                                price = it.price,
                                title = it.title,
                                image = it.images.first(),
                                tags = it.tags,
                                onClick = {
                                    navigationController.navigate(
                                        homeState.copy(
                                            productItem = it.id,
                                            scrollPosition = state.value
                                        )
                                    )
                                }
                            )
                        }
                    }
                }

                PageSection(
                    title = "Juste a quelque Metre",
                    modifier =
                        Modifier.nestedScroll(connection = nestedScrollConnection), // Parallax ou suivi partiel
                    state = gridState,
                ) {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        val liste = generateFakeRealEstateListCameroon().subList(6, 10)
                        liste.forEach {
                            RealEstateItem2(
                                location = it.location,
                                postedAgo = it.postedAgo,
                                price = it.price,
                                title = it.title,
                                image = it.images.first(),
                                tags = it.tags,
                                onClick = {
                                    navigationController.navigate(
                                        homeState.copy(
                                            productItem = it.id,
                                            scrollPosition = state.value
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
                val realEstates = generateFakeRealEstateListCameroon()
                RealEstateCategories.values().forEach { category ->
                    val filteredList = realEstates.filter { it.categorie == category }
                    if (filteredList.isNotEmpty()) {
                        val sectionTitle = when (category) {
                            RealEstateCategories.CHAMBRE,
                            RealEstateCategories.STUDIO -> "Chambre / Studio"

                            else -> category.name.lowercase().replaceFirstChar { it.uppercase() }
                        }

                        PageSection(
                            title = sectionTitle,
                            modifier = Modifier.nestedScroll(connection = nestedScrollConnection), // Parallax ou suivi partiel
                            state = gridState,
                        ) {
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                filteredList.forEach {
                                    RealEstateItem2(
                                        location = it.location,
                                        postedAgo = it.postedAgo,
                                        price = it.price,
                                        title = it.title,
                                        image = it.images.first(),
                                        tags = it.tags,
                                        onClick = {
                                            navigationController.navigate(
                                                homeState.copy(
                                                    productItem = it.id,
                                                    scrollPosition = state.value
                                                )
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }

                    /*PageSection(
                    title = "Maison/Appartement",
                    modifier = Modifier.nestedScroll(connection = nestedScrollConnection),// Parallax ou suivi partiel
                    state = gridState,
                    onClickItem = {
                        // enabledPageDetail = true

                    }
                )
                PageSection(
                    title = "Bureau / Boutique",
                    modifier = Modifier.nestedScroll(connection = nestedScrollConnection), // Parallax ou suivi partiel
                    state = gridState,
                    onClickItem = {
                        //enabledPageDetail = true
                    }
                )
                PageSection(
                    title = "Terrain",
                    modifier = Modifier.nestedScroll(connection = nestedScrollConnection), // Parallax ou suivi partiel
                    state = gridState,
                    onClickItem = {
                        //  enabledPageDetail = true
                    }
                )*/
                }
            }
        }

        AppVerticalScrollBar(
            modifier = Modifier.width(5.dp).align(Alignment.CenterEnd).zIndex(0.8f),
            scrollState = state
        )


        AnimatedVisibility(homeState.productItem.isNotEmpty()) {
            AppScrollableDialog(
                onDismissRequest = {
                    navigationController.navigateUp()
                },
            ) {
                Text("bonjour le monde")
                DetailRealEstateItemScreen(
                    navController = navigationController,
                    generateFakeRealEstateListCameroon().filter { it.id==homeState.productItem }.first(),
                    onClose = {
                        navigationController.navigateUp()
                    })
            }
        }


    }
}