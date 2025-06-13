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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composeApp.src.commonMain.ComposeResources.drawable.*
import empire.digiprem.config.getActualWindowsSize
import empire.digiprem.navigation.ViewLogin
import empire.digiprem.navigation.login
import empire.digiprem.presentation.components.*
import empire.digiprem.presentation.components.wrapper.PageWrapperState
import empire.digiprem.presentation.components.wrapper.WebDesktopPageWrapper
import empire.digiprem.presentation.intents.RegisterIntent
import empire.digiprem.presentation.viewmodels.RegisterViewModel.Companion.CONFIRM_PASSWORD_TEXTFIELD
import empire.digiprem.presentation.viewmodels.RegisterViewModel.Companion.EMAIL_TEXTFIELD
import empire.digiprem.presentation.viewmodels.RegisterViewModel.Companion.PASSWORD_TEXTFIELD
import org.jetbrains.compose.resources.painterResource
import org.koin.core.parameter.parametersOf


@Composable
fun RegisterView(
    viewRegister: ViewRegister,
    navController: NavHostController,
    registerViewModel: RegisterViewModel = koinViewModel { parametersOf(navController) }
) {
    // val registerViewModel:RegisterViewModel = viewModel{RegisterViewModel()}
    val state by registerViewModel.state.collectAsState()
    val pageState by registerViewModel.pageWrapperState.collectAsState()
    val onSendIntent = registerViewModel::onIntentHandler
    var enablePasswordVisualTransformation by remember { mutableStateOf(true) }

    WebDesktopPageWrapper(
        state = PageWrapperState(isSuccess = true),
        customTopAppBar = {
            AppHeader {

            }
        }
    ){
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
                                painter = painterResource(Res.drawable.`homme_qui_se_renseigne_pour_l'achat_d_une_maison`),
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
                                    modifier = Modifier.clickable { navController.navigate(ViewLogin()) },
                                    text = "login",
                                    color = MaterialTheme.colorScheme.primary,
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }
                        val message=pageState.errorMessage
                        val enabled=!pageState.errorMessage.equals("")
                        FormErrorMessageSection(
                            enabled= enabled,
                            errorMessage = message,
                        )

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            //Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            AppTextField(
                                label = {
                                    Text("Email")
                                },
                                placeholder = "Enter your email",
                                value = state.emailTextField.value,
                                isError = state.emailTextField.isError,
                                errorMessage = state.emailTextField.errorMessage,
                                leadingIcon = {
                                    Icon(
                                        Icons.Default.Person,
                                        tint = Color.Gray,
                                        contentDescription = "",
                                        modifier = Modifier.size(20.dp)
                                    )
                                },
                                onValueChange = {
                                    onSendIntent(RegisterIntent.onChangeTextField(EMAIL_TEXTFIELD, it))
                                },
                            )
                            AppTextField(
                                label = {
                                    Text("Password")
                                },
                                placeholder = "Enter your password",
                                value = state.passwordTextField.value,
                                isError = state.passwordTextField.isError,
                                errorMessage = state.passwordTextField.errorMessage,
                                leadingIcon = {
                                    Icon(
                                        Icons.Default.Lock,
                                        tint = Color.Gray,
                                        contentDescription = "",
                                        modifier = Modifier.size(20.dp)
                                    )
                                },
                                visualTransformation = if (enablePasswordVisualTransformation) PasswordVisualTransformation() else VisualTransformation.None,
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                            enablePasswordVisualTransformation = !enablePasswordVisualTransformation
                                        }
                                    ) {
                                        Icon(
                                            if (enablePasswordVisualTransformation) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                            contentDescription = "",
                                            tint = Color.Gray,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }

                                },
                                onValueChange = {
                                    onSendIntent(RegisterIntent.onChangeTextField(PASSWORD_TEXTFIELD, it))
                                }
                            )

                            AppTextField(
                                label = {
                                    Text("Confirm Password")
                                },
                                placeholder = "Enter your Confirm password",
                                value = state.confirmPasswordTextField.value,
                                isError = state.confirmPasswordTextField.isError,
                                errorMessage = state.confirmPasswordTextField.errorMessage,
                                leadingIcon = {
                                    Icon(
                                        Icons.Default.Lock,
                                        contentDescription = "",
                                        tint = Color.Gray,
                                        modifier = Modifier.size(20.dp)
                                    )
                                },
                                visualTransformation = PasswordVisualTransformation(),
                                onValueChange = {
                                    onSendIntent(RegisterIntent.onChangeTextField(CONFIRM_PASSWORD_TEXTFIELD, it))
                                }
                            )
                            // }
                        }
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            AppFormButton(
                                label = "Sign Up",
                                enabled = state.enableSendButton,
                                enabledProgressIndicator = pageState.isLoading,
                                onClick = {
                                    onSendIntent(RegisterIntent.OnSendForm)
                                }
                            )
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

}

@Composable
 fun FormErrorMessageSection(enabled: Boolean, errorMessage: String) {
    if (enabled) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Black.copy(alpha = 0.2f))
                .height(50.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize().padding(start = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    errorMessage,
                    color = Color.Red,
                    style = TextStyle(fontSize = 13.sp)
                )
            }
        }
    }
}

@Composable
fun AppFormButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
    label: String,
    enabledProgressIndicator: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    onClick: () -> Unit = {},
) {
    AppButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick
    ) {
        Text(label, style = textStyle)
        if (enabledProgressIndicator) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.size(20.dp),
                strokeWidth = 2.dp
            )
        }
    }
}