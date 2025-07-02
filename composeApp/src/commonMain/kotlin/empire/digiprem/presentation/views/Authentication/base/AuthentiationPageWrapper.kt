package empire.digiprem.presentation.views.Authentication.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import composeApp.src.commonMain.ComposeResources.drawable.IM_Plan_de_travail_3
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.background_immeuble
import empire.digiprem.config.isCompactApplication
import empire.digiprem.config.isCompactMobilePlatform
import empire.digiprem.config.isMediumApplication
import empire.digiprem.presentation.base.color.Colors
import empire.digiprem.presentation.views.AppBox
import empire.digiprem.presentation.views.AppCardWrapperEx
import empire.digiprem.presentation.views.WebDesktopHeaderAppBar
import org.jetbrains.compose.resources.painterResource


@Composable
fun AuthenticationPageWrapper(navController: NavHostController, content: @Composable () -> Unit) {

    val isCompactOrMedium = isCompactApplication() || isMediumApplication()
    Box(
        modifier = Modifier
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(Res.drawable.background_immeuble),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                if (!isCompactMobilePlatform()) {
                    WebDesktopHeaderAppBar(navController)
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier.padding(it).fillMaxSize()
                    .background(Colors.brushVioletVertical),//   background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    AppBox {
                        AppCardWrapperEx(
                            modifier = Modifier.fillMaxSize()
                                .padding(horizontal = if (!isCompactOrMedium) 50.dp else 10.dp),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    if (!isCompactOrMedium) {
                                        Box(
                                            modifier = Modifier.weight(0.6F),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            /* Image(
                                                 painter = painterResource(Res.drawable.`homme_qui_se_renseigne_pour_l'achat_d_une_maison`),
                                                 contentDescription = ""
                                             )*/
                                        }
                                    }
                                    Column(
                                        modifier = Modifier.weight(0.5f).wrapContentHeight(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(5.dp)
                                    ) {
                                   // if (isCompactOrMedium) {
                                            Image(
                                                modifier = Modifier.width(150.dp).height(100.dp),
                                                painter = painterResource(Res.drawable.IM_Plan_de_travail_3),
                                                contentDescription = "",
                                                contentScale = ContentScale.FillBounds
                                            )
                                       // }
                                        AppCardWrapperEx(
                                            modifier = Modifier.fillMaxWidth()
                                                .padding(if (!isCompactOrMedium) 30.dp else 5.dp)
                                                .clip(RoundedCornerShape(7.dp))
                                                .background(Color.Black.copy(alpha = 0.4f))
                                                .padding(if (!isCompactOrMedium) 50.dp else 20.dp),
                                        ) {
                                            content()
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
}
