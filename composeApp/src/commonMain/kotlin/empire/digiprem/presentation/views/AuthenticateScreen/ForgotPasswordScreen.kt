package empire.digiprem.presentation.views.AuthenticateScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.compose_multiplatform
import empire.digiprem.config.Platform
import empire.digiprem.config.getActualWindowsSize
import empire.digiprem.config.getPlatform
import empire.digiprem.presentation.components.AppButton
import empire.digiprem.presentation.components.AppCardWrapper
import empire.digiprem.presentation.components.AppOutlinedButton
import empire.digiprem.presentation.components.AppTextField
import empire.digiprem.navigation.login
import empire.digiprem.navigation.verifyIdentity
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(navController: NavHostController) {


    var isError by remember { mutableStateOf(false) }
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
                            text = "Forgot your Password ?",
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
                                text = "Enter your email address below and we'll send you a PIN to reset your password.",
                                style = TextStyle(fontSize = 12.sp)
                            )
                        }
                    }
                    AnimatedVisibility(
                        isError &&  (getPlatform()==Platform.DESKTOP||getPlatform()==Platform.WEB)){
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
                        AppTextField(
                            isError = if (getPlatform()==Platform.DESKTOP||getPlatform()==Platform.WEB) false else isError,
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

                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        AppButton(
                            onClick = {
                                isError=true
                                navController.navigate(verifyIdentity)
                            }
                        ) {
                            Text("Reset My Password", color = Color.White)
                        }
                        AppOutlinedButton(
                            onClick = {
                                isError=false
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


