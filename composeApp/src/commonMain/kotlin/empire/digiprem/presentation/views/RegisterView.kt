package empire.digiprem.presentation.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import empire.digiprem.navigation.ViewRegister
import empire.digiprem.presentation.viewmodels.RegisterViewModel
import org.koin.compose.viewmodel.koinViewModel
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
import composeApp.src.commonMain.ComposeResources.drawable.*
import empire.digiprem.presentation.components.AppButton
import empire.digiprem.presentation.components.AppCardWrapper
import empire.digiprem.presentation.components.AppIconButton
import empire.digiprem.presentation.components.AppTextField
import empire.digiprem.config.getActualWindowsSize
import empire.digiprem.navigation.login
import org.jetbrains.compose.resources.painterResource

@Composable
fun RegisterView(
    viewRegister: ViewRegister,
    navController: NavHostController,
    registerViewModel: RegisterViewModel = koinViewModel()
) {
    // val registerViewModel:RegisterViewModel = viewModel{RegisterViewModel()}
    val state by registerViewModel.state.collectAsState()
    val onSendIntent = registerViewModel::onIntentHandler

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
                            painter = painterResource(Res.drawable.compose_multiplatform),
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
                            text = "Create your account",
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
                                text = "you have an account?",
                                style = TextStyle(fontSize = 13.sp)
                            )
                            Text(
                                modifier = Modifier.clickable { navController.navigate(login) },
                                text = "login",
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
                        AppTextField(
                            label = {
                                Text("Confirm Password")
                            },
                            placeholder = "Enter your Confirm password",
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
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        AppButton(
                            onClick = { navController.navigate(login) }
                        ) {
                            Text("Sing Up", color = Color.White)
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