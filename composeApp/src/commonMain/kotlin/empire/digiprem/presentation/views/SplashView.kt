package empire.digiprem.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.background_immeuble
import empire.digiprem.config.isCompactMobilePlatform
import empire.digiprem.navigation.*
import empire.digiprem.presentation.components.AppButton
import empire.digiprem.presentation.components.AppSecondaryButton
import empire.digiprem.presentation.viewmodels.SplashViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashView(
    viewSplash: ViewSplash,
    navController: NavHostController,
    splashViewModel: SplashViewModel = koinViewModel()
) {
    // val splashViewModel:SplashViewModel = viewModel{SplashViewModel()}
    val state by splashViewModel.state.collectAsState()
    val onSendIntent = splashViewModel::onIntentHandler
    val scrollState= rememberScrollState(0)
    val isCompactSize = isCompactMobilePlatform()
    Box(modifier = Modifier.fillMaxSize().verticalScroll(scrollState), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier.fillMaxSize()
                .align(Alignment.TopStart).zIndex(0.8f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(Res.drawable.background_immeuble),
                null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier.matchParentSize().background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(0.8f),
                    verticalArrangement = Arrangement.spacedBy(25.dp, alignment = Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "IMMOBI-Market",
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 35.sp,
                        )
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "En quête d’un espace qui vous ressemble ? Chambre, appart ou maison? Louez ou achetez en toute simplicité, partout au Cameroun.",
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.White, fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Center,
                        )
                    )

                    Column(modifier = Modifier.padding(top = 30.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ){
                        AppButton(onClick = {
                            navController.navigate(ViewLogin())
                        }){
                            Text("se connecter", color = it)
                        }
                        AppSecondaryButton(
                            onClick = { navController.navigate(ViewRegister())}
                        ){
                            Text("s'inscrire", color = it)
                        }
                       /* TextButton(
                            colors = ButtonDefaults.textButtonColors().copy(
                                contentColor =MaterialTheme.colorScheme.primary,
                                containerColor =MaterialTheme.colorScheme.surfaceVariant,
                            ),
                            onClick = {}) {
                        }
                        TextButton(
                            colors = ButtonDefaults.textButtonColors().copy(
                                contentColor = Color.White,
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            onClick = { navController.navigate(ViewRegister()) }) {

                        }*/
                    }
                }
                Box(modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 30.dp)){
                    TextButton(onClick = {
                        navController.navigate(ViewMobileDashBoard())
                    }){
                        Text("Pass Authentication" , color = Color.White)
                        Icon( Icons.Default.KeyboardDoubleArrowRight,"",tint=Color.White)
                    }
                }
            }
        }
    }
}
