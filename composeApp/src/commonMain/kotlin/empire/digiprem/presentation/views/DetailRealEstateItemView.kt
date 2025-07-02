package empire.digiprem.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.background_Immobi_Marcket
import composeApp.src.commonMain.ComposeResources.drawable.background_immeuble
import empire.digiprem.navigation.ViewDetailRealEstateItem
import empire.digiprem.navigation.chat
import empire.digiprem.presentation.components.AppTextField
import empire.digiprem.presentation.components.app.RealEstateData
import empire.digiprem.presentation.viewmodels.DetailRealEstateItemViewModel
import empire.digiprem.ui.Screen.dashboard_screen.PageSection
import empire.digiprem.ui.Screen.dashboard_screen.PageSectionEx
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailRealEstateItemView(
    viewDetailRealEstateItem: ViewDetailRealEstateItem,
    navController: NavHostController,
    realEstateData: RealEstateData? = null,
    detailrealestateitemViewModel: DetailRealEstateItemViewModel = koinViewModel(),
    onClose: (() -> Unit)? = null,
) {
    // val detailrealestateitemViewModel:DetailRealEstateItemViewModel = viewModel{DetailRealEstateItemViewModel()}
    val state by detailrealestateitemViewModel.state.collectAsState()
    val onSendIntent = detailrealestateitemViewModel::onIntentHandler

    val pagerState = rememberPagerState(initialPage = 0) { realEstateData?.images?.size?:0 }
    val scope = rememberCoroutineScope()
    LazyColumn(modifier =Modifier.fillMaxSize()) {
        realEstateData?.images?.let {
            item {
                Box(modifier = Modifier.height(500.dp).fillMaxWidth()) {
                    HorizontalPager(
                        modifier = Modifier.fillMaxSize(),
                        state = pagerState,
                    ) {
                        AsyncImage(
                            model = realEstateData?.images?.get(it),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    IconButton(
                        modifier = Modifier.padding(20.dp).align(Alignment.TopEnd),
                        colors = IconButtonDefaults.iconButtonColors()
                            .copy(containerColor = Color.White, contentColor = Color.Black),
                        onClick = { onClose?.invoke() },
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = ""
                        )
                    }
                    IconButton(
                        modifier = Modifier.padding(20.dp).align(Alignment.CenterStart),
                        colors = IconButtonDefaults.iconButtonColors()
                            .copy(containerColor = Color.White, contentColor = Color.Black),
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        },
                    ) {
                        Icon(
                            Icons.Default.ArrowLeft,
                            contentDescription = ""
                        )
                    }
                    IconButton(
                        modifier = Modifier.padding(20.dp).align(Alignment.CenterEnd),
                        colors = IconButtonDefaults.iconButtonColors()
                            .copy(containerColor = Color.White, contentColor = Color.Black),
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        },
                    ) {
                        Icon(
                            Icons.Default.ArrowRight,
                            contentDescription = ""
                        )
                    }

                    Row(
                        modifier = Modifier.padding(bottom = 20.dp).fillMaxWidth().align(Alignment.BottomCenter),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
                    ) {
                        realEstateData?.let {
                            it.images.forEachIndexed { index, item ->
                                val selectedItem = index == pagerState.currentPage
                                Box(
                                    modifier = Modifier.size(if (selectedItem) 75.dp else 60.dp)
                                        .clip(RoundedCornerShape(5.dp))
                                        .background(if (selectedItem) MaterialTheme.colorScheme.primary else Color.White)
                                        .padding(2.dp).clickable {
                                            scope.launch {
                                                pagerState.animateScrollToPage(index)
                                            }
                                        }
                                ) {

                                    AsyncImage(
                                        model = item,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )

                                    /* Image(
                                         modifier = Modifier.fillMaxSize(),
                                         painter = item,
                                         contentDescription = "",
                                         contentScale = ContentScale.Crop
                                     )*/
                                }
                            }
                        }

                    }
                }
            }
        }
        item {
            PageSectionEx(
                title = "Detail du bien",
            ) {
                Box(modifier = Modifier.height(300.dp).fillMaxWidth().background(Color.Red)) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth().clickable {},
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.background_immeuble),
                                    contentDescription = "Photo de profil",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clip(CircleShape)
                                        .border(1.dp, Color.Gray, CircleShape)
                                )
                                Column(
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.Top
                                ) {
                                    Text(
                                        text = "userName",
                                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                                    )
                                    Text(
                                        text = "~20yrs",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 10.sp,
                                            color = Color.Gray
                                        )
                                    )
                                }
                            }
                            // Icon(if (expandedText) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown, null)
                        }
                        Text(
                            modifier = Modifier.padding(start = 20.dp),
                            text = "Voici la description de l’article publié par l’utilisateur. Elle peut être un peu longue.Voici la description de l’article publié par l’utilisateur. Elle peut être un peu longue.Voici la description de l’article publié par l’utilisateur. Elle peut être un peu longue.",
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = Int.MAX_VALUE,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth().height(30.dp).padding(2.dp),
                        horizontalArrangement = Arrangement.spacedBy(7.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier.weight(1f).shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
                                .background(Color.White).padding(2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                Icons.Default.Handshake,
                                modifier = Modifier.padding(2.dp),
                                contentDescription = null,
                            )
                            Text("0", style = MaterialTheme.typography.bodyMedium)
                        }
                        Row(
                            modifier = Modifier.weight(1f).shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
                                .background(Color.White).padding(2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                Icons.Default.Favorite,
                                modifier = Modifier.padding(2.dp),
                                contentDescription = null,
                            )
                            Text("0", style = MaterialTheme.typography.bodyMedium)
                        }
                        Row(
                            modifier = Modifier.weight(1f).shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
                                .background(Color.White).padding(2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text("more...", style = MaterialTheme.typography.bodyMedium)
                        }
                    }

                }
            }
        }
        item {
            PageSectionEx(
                title = "Localisation",
            ) {

            }

        }
        item {
            PageSectionEx(
                title = "Informations sur le proprietaire",
            ) {
                Column(modifier = Modifier.wrapContentHeight().fillMaxWidth()) {
                    var value by remember { mutableStateOf("Bonjour Alain, cette maison est-il toujours disponible  ?") }
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth().clickable {},
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Image(
                                    painter = painterResource(Res.drawable.background_immeuble),
                                    contentDescription = "Photo de profil",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clip(CircleShape)
                                        .border(1.dp, Color.Gray, CircleShape)
                                )
                                Column(
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.Top
                                ) {
                                    Text(
                                        text = "userName",
                                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        repeat(5) {
                                            Icon(
                                                imageVector = Icons.Default.Star,
                                                contentDescription = "",
                                                modifier = Modifier.size(18.dp).padding(2.dp),
                                                tint = MaterialTheme.colorScheme.primary,
                                            )
                                        }
                                        Text(
                                            text = "~20yrs",
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 10.sp,
                                                color = Color.Gray
                                            )
                                        )
                                    }

                                }
                            }
                            // Icon(if (expandedText) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown, null)
                        }
                        Text(
                            modifier = Modifier.padding(start = 20.dp),
                            text = "Voici la description de l’article publié par l’utilisateur. Elle peut être un peu longue.Voici la description de l’article publié par l’utilisateur. Elle peut être un peu longue.Voici la description de l’article publié par l’utilisateur. Elle peut être un peu longue.",
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = Int.MAX_VALUE,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(
                        modifier = Modifier.padding(10.dp).width(500.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
                    ) {
                        AppTextField(
                            label = {
                                Text("Envoyer un message au proprietaire du bien ")
                            },
                            modifier = Modifier.height(60.dp).fillMaxWidth(0.8f),
                            singleLine = false,
                            value = value,
                            onValueChange = {
                                value = it
                            }
                        )
                        FloatingActionButton(
                            // modifier = Modifier.,
                            onClick = {
                                navController.navigate(
                                    chat(
                                        message = value,
                                        content = Json.encodeToString(realEstateData)
                                    )
                                )
                            }
                        ) {
                            Icon(Icons.Default.Send, contentDescription = "")
                        }

                    }


                }

            }

        }
    }
}