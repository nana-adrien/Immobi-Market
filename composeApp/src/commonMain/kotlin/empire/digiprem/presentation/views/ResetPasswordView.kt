package empire.digiprem.presentation.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import empire.digiprem.navigation.ViewResetPassword
import empire.digiprem.presentation.viewmodels.ResetPasswordViewModel
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.compose_multiplatform
import empire.digiprem.config.Platform
import empire.digiprem.config.getActualWindowsSize
import empire.digiprem.config.getPlatform
import empire.digiprem.navigation.login
import empire.digiprem.navigation.signUp
import empire.digiprem.presentation.components.AppButton
import empire.digiprem.presentation.components.AppCardWrapper
import empire.digiprem.presentation.components.AppOutlinedButton
import empire.digiprem.presentation.components.AppTextField
import org.jetbrains.compose.resources.painterResource

@Composable
fun ResetPasswordView(
    viewResetPassword: ViewResetPassword,
    navController: NavHostController,
    resetpasswordViewModel: ResetPasswordViewModel = koinViewModel()
) {
    // val resetpasswordViewModel:ResetPasswordViewModel = viewModel{ResetPasswordViewModel()}
    val state by resetpasswordViewModel.state.collectAsState()
    val onSendIntent = resetpasswordViewModel::onIntentHandler

    var isError by remember { mutableStateOf(false) }
    var enabledVirtualTransformation1 by remember { mutableStateOf(true) }
    var enabledVirtualTransformation2 by remember { mutableStateOf(true) }
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
                            text = "Reset your Password ?",
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
                                text = "Enter your new password below to complete the reset.",
                                style = TextStyle(fontSize = 12.sp)
                            )
                        }
                    }
                    AnimatedVisibility(
                        isError && (getPlatform() == Platform.DESKTOP || getPlatform() == Platform.WEB)
                    ) {
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
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        var value1 by remember { mutableStateOf("") }
                        var value2 by remember { mutableStateOf("") }
                        AppTextField(
                            isError = if (getPlatform() == Platform.DESKTOP || getPlatform() == Platform.WEB) false else isError,
                            label = {
                                Text("Password")
                            },
                            placeholder = "Enter new password",
                            value = value1,
                            visualTransformation = if (enabledVirtualTransformation1) PasswordVisualTransformation() else VisualTransformation.None,
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = "",
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        enabledVirtualTransformation1 = !enabledVirtualTransformation1
                                    }
                                ) {
                                    Icon(
                                        if (enabledVirtualTransformation1) {
                                            Icons.Default.ArrowForward
                                        } else Icons.Default.ArrowBack,
                                        contentDescription = "",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                            },
                            onValueChange = {
                                value1 = it
                            },
                        )
                        AppTextField(
                            isError = if (getPlatform() == Platform.DESKTOP || getPlatform() == Platform.WEB) false else isError,
                            value = value2,
                            label = {
                                Text("Confirm password")
                            },
                            visualTransformation = PasswordVisualTransformation(),
                            placeholder = "Confirm password",
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Lock,
                                    contentDescription = "",
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            onValueChange = {
                                value2 = it
                            },
                        )

                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        AppButton(
                            onClick = {
                                isError = true
                                navController.navigate(signUp)
                            }
                        ) {
                            Text("Reset", color = Color.White)
                        }

                        AppOutlinedButton(
                            onClick = {
                                isError = false
                                navController.navigate(login)
                            }
                        ) {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                            )
                            Text(
                                "Back to Login",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                        }
                    }

                }
            }

        }
    }
}