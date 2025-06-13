package empire.digiprem.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import composeApp.src.commonMain.ComposeResources.drawable.*
import empire.digiprem.config.getActualWindowsSize
import empire.digiprem.config.isCompactMobilePlatform
import empire.digiprem.navigation.*
import empire.digiprem.presentation.components.AppButton
import empire.digiprem.presentation.components.AppCardWrapper
import empire.digiprem.presentation.components.AppIconButton
import empire.digiprem.presentation.components.AppTextField
import empire.digiprem.presentation.viewmodels.LoginViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginView(
    viewLogin: ViewLogin,
    navController: NavHostController,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    // val loginViewModel:LoginViewModel = viewModel{LoginViewModel()}
    val state by loginViewModel.state.collectAsState()
    val onSendIntent = loginViewModel::onIntentHandler

    AppCardWrapper(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(
            modifier = Modifier.wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (getActualWindowsSize().widthSizeClass != WindowWidthSizeClass.Compact) {
                    Box(
                        modifier = Modifier.weight(0.4F),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.femme_sur_son_telephone_qui_cherche_une_maison),
                            contentDescription = ""
                        )
                    }
                }
                Column(
                    modifier = Modifier.weight(0.6f)
                        .background(Color.White.copy(alpha = 0.8f)).padding(30.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(7.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Login to your account",
                            style = TextStyle(
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                5.dp,
                                alignment = Alignment.CenterHorizontally
                            )
                        ) {
                            Text(
                                text = "Don't have an account?",
                                style = TextStyle(fontSize = 13.sp)
                            )
                            Text(
                                modifier = Modifier.clickable { navController.navigate(ViewRegister()) },
                                text = "Sign Up",
                                color = MaterialTheme.colorScheme.primary,
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp).padding(6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Cependant, pour une gestion plus avancée et optimisée des layouts adaptatifs, ",
                            color = Color.Red,
                            style = TextStyle(fontSize = 13.sp)
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        var value1 by remember { mutableStateOf("") }
                        var value2 by remember { mutableStateOf("") }

                        //Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        AppTextField(
                            label = {
                                Text("Email")
                            },
                            placeholder = "Enter your email",
                            value = value1,
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = "",
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            onValueChange = {
                                value1 = it
                            },
                        )
                        AppTextField(
                            label = {
                                Text("Password")
                            },
                            placeholder = "Enter your password",
                            value = value2,
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Lock,
                                    contentDescription = "",
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            onValueChange = {
                                value2 = it
                            }
                        )
                        // }

                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp)
                                .padding(top = 5.dp, bottom = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Row(
                                modifier = Modifier.weight(0.5f),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Checkbox(
                                    modifier = Modifier.size(10.dp),
                                    checked = true,
                                    onCheckedChange = {

                                    }
                                )
                                Text("Remember me", style = TextStyle(fontSize = 12.sp))
                            }
                            Row(
                                modifier = Modifier.weight(0.5f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(
                                    "Mot de passe oublier ?",
                                    modifier = Modifier.clickable { navController.navigate(ViewForgotPassword()) },
                                    style = TextStyle(fontSize = 12.sp),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        val isCompactMobilePlatform=isCompactMobilePlatform()
                        AppButton(
                            onClick = {
                                navController.navigate(
                                    if (isCompactMobilePlatform) {
                                        ViewMobileDashBoard(0, isConnected = true)
                                    } else {
                                        ViewHome(isConnected = true)
                                    }
                                )
                            }
                        ) {
                            Text("Login with email", color = Color.White)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            HorizontalDivider(modifier = Modifier.weight(0.4f))
                            Box(
                                modifier = Modifier.weight(0.2f),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Or", color = Color.Gray)
                            }
                            HorizontalDivider(modifier = Modifier.weight(0.4f))
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            AppIconButton(
                                painter = painterResource(Res.drawable.google),
                                contentDescription = "",
                                onclick = {

                                })
                            AppIconButton(
                                painter = painterResource(Res.drawable.facebook),
                                contentDescription = "",
                                onclick = {

                                })
                            AppIconButton(
                                painter = painterResource(Res.drawable.apple),
                                contentDescription = "",
                                onclick = {

                                })
                        }
                    }

                }
            }

        }
    }

}