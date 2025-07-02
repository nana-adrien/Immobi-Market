package empire.digiprem.presentation.views.Authentication

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composeApp.src.commonMain.ComposeResources.drawable.*
import empire.digiprem.config.*
import empire.digiprem.navigation.ViewLogin
import empire.digiprem.presentation.components.*
import empire.digiprem.presentation.components.wrapper.PageWrapperState
import empire.digiprem.presentation.components.wrapper.WebDesktopPageWrapper
import empire.digiprem.presentation.intents.RegisterIntent
import empire.digiprem.presentation.viewmodels.RegisterViewModel.Companion.CONFIRM_PASSWORD_TEXTFIELD
import empire.digiprem.presentation.viewmodels.RegisterViewModel.Companion.EMAIL_TEXTFIELD
import empire.digiprem.presentation.viewmodels.RegisterViewModel.Companion.PASSWORD_TEXTFIELD
import empire.digiprem.presentation.views.AppBox
import empire.digiprem.presentation.views.AppCardWrapperEx
import empire.digiprem.presentation.views.Authentication.base.AuthenticationPageWrapper
import empire.digiprem.presentation.views.WebDesktopHeaderAppBar
import org.jetbrains.compose.resources.painterResource
import org.koin.core.parameter.parametersOf


@OptIn(ExperimentalMaterial3Api::class)
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

    AuthenticationPageWrapper(
        navController = navController
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(25.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(7.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Creer un compte",
                    color=MaterialTheme.colorScheme.surface,
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
                        text = "J'ai deja un compte?",
                        color=MaterialTheme.colorScheme.surface,
                        style = TextStyle(fontSize = 13.sp)
                    )
                    Text(
                        modifier = Modifier.clickable { navController.navigate(ViewLogin()) },
                        text = "je me connecte",
                        color = MaterialTheme.colorScheme.secondary,
                        textDecoration = TextDecoration.Underline,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            AnimatedVisibility(
                (getPlatform() == Platform.DESKTOP || getPlatform() == Platform.WEB) && pageState.errorMessage.isNotEmpty()
            ) {
                FormErrorMessageSection(
                    enabled =true,
                    errorMessage = pageState.errorMessage,
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                //Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                AppTextField(
                    label = {
                        Text(
                            text = "Email",
                            color=MaterialTheme.colorScheme.surface,
                            style = TextStyle(fontSize = 13.sp)
                        )
                    },
                    placeholder = "Entrez votre email",
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
                        onSendIntent(
                            RegisterIntent.onChangeTextField(
                                EMAIL_TEXTFIELD,
                                it
                            )
                        )
                    },
                )
                AppTextField(
                    label = {
                        Text(
                            text = "Mot de passe",
                            color=MaterialTheme.colorScheme.surface,
                            style = TextStyle(fontSize = 13.sp)
                        )
                    },
                    placeholder = "Entrez votre mot de passe",
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
                                enablePasswordVisualTransformation =
                                    !enablePasswordVisualTransformation
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
                        onSendIntent(
                            RegisterIntent.onChangeTextField(
                                PASSWORD_TEXTFIELD,
                                it
                            )
                        )
                    }
                )

                AppTextField(
                    label = {
                        Text(
                            text = "Mot de passe de confirmation",
                            color=MaterialTheme.colorScheme.surface,
                            style = TextStyle(fontSize = 13.sp)
                        )
                    },
                    placeholder = "Entrez le mot de passe de confirmation",
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
                        onSendIntent(
                            RegisterIntent.onChangeTextField(
                                CONFIRM_PASSWORD_TEXTFIELD,
                                it
                            )
                        )
                    }
                )
                // }
            }

            AppFormButton(
                label = "S'inscrire",
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
                    Text("ou", color = Color.LightGray)
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

@Composable
fun FormErrorMessageSection(enabled: Boolean, errorMessage: String) {
    if (enabled) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .heightIn(max =60.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Black.copy(alpha = 0.2f))
                ,
            contentAlignment = Alignment.Center
        ) {
            Row(modifier = Modifier.fillMaxWidth().wrapContentHeight(), horizontalArrangement = Arrangement.Start,  verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .width(7.dp)
                        .fillMaxHeight().background(Color.Red),
                )
                Spacer(Modifier.width(5.dp))
                Text(
                    errorMessage,
                    color = Color.White,
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
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.surface),
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