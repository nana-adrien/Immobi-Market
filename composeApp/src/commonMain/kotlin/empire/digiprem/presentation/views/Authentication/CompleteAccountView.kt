package empire.digiprem.presentation.views.Authentication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import empire.digiprem.navigation.ViewCompleteAccount
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.compose.viewmodel.koinViewModel
import androidx.navigation.NavHostController
import empire.digiprem.config.*
import empire.digiprem.enums.Gender
import empire.digiprem.presentation.components.AppOutlinedButton
import empire.digiprem.presentation.components.AppTextField
import empire.digiprem.presentation.intents.CompleteAccountIntent
import empire.digiprem.presentation.viewmodels.CompleteAccountViewModel
import empire.digiprem.presentation.views.Authentication.base.AuthenticationPageWrapper
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteAccountView(
    viewCompleteAccount: ViewCompleteAccount,
    navController: NavHostController,
    completeAccountViewModel: CompleteAccountViewModel = koinViewModel{parametersOf(navController) }
) {
    // val createaccountViewModel:CreateAccountViewModel = viewModel{CreateAccountViewModel()}
    val state by completeAccountViewModel.state.collectAsState()
    val pageState by completeAccountViewModel.pageWrapperState.collectAsState()
    val onSendIntent = completeAccountViewModel::onIntentHandler

    val formPageLabelStyle:@Composable (String)->Unit =@Composable { text ->
        Text(text = text, style = MaterialTheme.typography.titleMedium.copy(
            color = MaterialTheme.colorScheme.surface
        ))
    }

    AuthenticationPageWrapper(
        navController = navController
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(7.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Complete Your Account Information",
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
                        style = TextStyle(fontSize = 12.sp)
                    )
                }
            }
            AnimatedVisibility(
                (getPlatform() == Platform.DESKTOP || getPlatform() == Platform.WEB) && pageState.errorMessage.isNotEmpty()
            ) {
                FormErrorMessageSection(
                    enabled = true,
                    errorMessage = pageState.errorMessage,
                )

            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                // .background(Color.White.copy(alpha = 0.8f))
                // .padding(30.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Nom et prénom
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    AppTextField(
                        label = { formPageLabelStyle("Nom *") },
                        modifier = Modifier.weight(1f),
                        value = state.lastNameTextField.value,
                        placeholder = "Nom",
                        onValueChange = {
                            onSendIntent(
                                CompleteAccountIntent.OnLastNameChange(
                                    it
                                )
                            )
                        }
                    )
                    AppTextField(
                        label = { formPageLabelStyle("Prenom *") },
                        modifier = Modifier.weight(1f),
                        value = state.firstNameTextField.value,
                        placeholder = "Prénom",
                        onValueChange = {
                            onSendIntent(
                                CompleteAccountIntent.OnFirstNameChange(
                                    it
                                )
                            )
                        }
                    )
                }


                // Sexe
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                    val onclick: (Gender) -> Unit = { gender ->
                        onSendIntent(CompleteAccountIntent.OnGenderSelect(gender))
                    }
                    Gender.entries.forEach { gender ->
                        Box(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .padding(5.dp)
                                .clickable {onclick(gender) },
                        ) {
                            Row(
                                //modifier = Modifier.clickable { onclick(gender) }
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = state.gender == gender,
                                    onClick = { onclick(gender) }
                                )
                                formPageLabelStyle(gender.label)
                            }
                        }
                    }
                }
                //Numéro de téléphone
                AppTextField(
                    label = { formPageLabelStyle("Numbero de telephone *") },
                    value = state.phoneTextField.value,
                    placeholder = "Numéro de téléphone",
                    leadingIcon = {
                        Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                            Text(
                                " 🇨🇲 +237 ",
                                modifier = Modifier.fillMaxHeight()
                            )
                        }

                    },
                    onValueChange = {
                        onSendIntent(
                            CompleteAccountIntent.OnPhoneChange(
                                it
                            )
                        )
                    }
                )
                // Authentification 2FA
                Column(
                    modifier = Modifier.wrapContentHeight().fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = state.twoFactorAuth,
                            onCheckedChange = {
                                onSendIntent(
                                    CompleteAccountIntent.OnTwoFactorAuthToggle(
                                        it
                                    )
                                )
                            }
                        )
                      formPageLabelStyle("Activer l'authentification à deux facteurs")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = state.enabledPrivacyPolitic,
                            onCheckedChange = {
                                onSendIntent(
                                    CompleteAccountIntent.OnTwoFactorPrivacyToggle(
                                        it
                                    )
                                )
                            }
                        )
                        TextButton(
                            onClick = { onSendIntent(CompleteAccountIntent.OnClickPolicy) },
                        ) {
                            formPageLabelStyle("Lire la politique d'assurance")
                            /*Text(
                                text = AnnotatedString("Lire la politique d'assurance"),
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    textDecoration = TextDecoration.Underline,
                                    fontSize = 14.sp
                                )
                            )*/
                        }

                    }
                }

                // Politique d'assurance

                // Boutons de navigation
                AppFormButton(
                    label = "Create my Account",
                    enabled = state.enabledSendButton,
                    enabledProgressIndicator = pageState.isLoading,
                    onClick = {
                        onSendIntent(CompleteAccountIntent.OnSubmit)
                    }
                )
                AppOutlinedButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "")
                    Text("Retour")
                }
            }
        }
    }

}