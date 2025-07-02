package empire.digiprem.presentation.views.Authentication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import empire.digiprem.config.Platform
import empire.digiprem.config.getPlatform
import empire.digiprem.navigation.ViewVerifyIdentity
import empire.digiprem.presentation.components.*
import empire.digiprem.presentation.intents.VerifyIdentityIntent
import empire.digiprem.presentation.viewmodels.VerifyIdentityViewModel
import empire.digiprem.presentation.views.Authentication.base.AuthenticationPageWrapper
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun VerifyIdentityView(
    viewVerifyIdentity: ViewVerifyIdentity,
    navController: NavHostController,
    verifyidentityViewModel: VerifyIdentityViewModel = koinViewModel{ parametersOf(navController) }
) {
    // val verifyidentityViewModel:VerifyIdentityViewModel = viewModel{VerifyIdentityViewModel()}
    val state by verifyidentityViewModel.state.collectAsState()
    val onSendIntent = verifyidentityViewModel::onIntentHandler
    val pageState by  verifyidentityViewModel.pageWrapperState.collectAsState()
    //var isError by remember { mutableStateOf(false) }
    AuthenticationPageWrapper(
        navController = navController
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(7.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Verify identity ",
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
                        text = "Please enter the 6-digit code sent to your email address to verify your identity.",
                        color=MaterialTheme.colorScheme.surface,
                        style = TextStyle(fontSize = 12.sp)
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

                AppTextField(
                    enabled = false,
                    value = viewVerifyIdentity.email,
                    onValueChange = {},
                )
                Spacer(Modifier.height(30.dp))

                AppPinCodeTextField(
                    isError = !(getPlatform() == Platform.DESKTOP || getPlatform() == Platform.WEB) && pageState.errorMessage.isNotEmpty(),
                    values = state.pinCode.value.toList().map{it.toString()},
                    codeSize = 6,
                    onComplete = {
                        //  value2 = it
                    },
                    onValueChange = {
                        onSendIntent(
                            VerifyIdentityIntent.OnPinCodeChange(it)
                        )
                    }
                )

            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                /*AppButton(
                    enabled = state.enableSendButton .isNotEmpty(),
                    onClick = {
                        isError = true
                        navController.navigate(resetPassword)
                    }
                ) {
                    Text("Verify", color = Color.White)
                }*/

                AppFormButton(
                    label = "Verify",
                    enabled = state.enableSendButton,
                    enabledProgressIndicator = pageState.isLoading,
                    onClick = {
                        onSendIntent(VerifyIdentityIntent.OnSendForm(viewVerifyIdentity.email,viewVerifyIdentity.operationType))
                    }
                )

                AppOutlinedButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
                    )
                    Text(
                        "Back",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.surface
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