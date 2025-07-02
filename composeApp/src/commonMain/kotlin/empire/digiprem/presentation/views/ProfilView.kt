package empire.digiprem.presentation.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import empire.digiprem.config.Platform
import empire.digiprem.config.getPlatform
import empire.digiprem.config.isCompactMobilePlatform
import empire.digiprem.data.local.DataBaseTemp
import empire.digiprem.enums.Gender
import empire.digiprem.model.AppFile
import empire.digiprem.navigation.*
import empire.digiprem.presentation.components.*
import empire.digiprem.presentation.components.app.*
import empire.digiprem.presentation.components.forms.PickStorageFile
import empire.digiprem.presentation.components.forms.TakePicture
import empire.digiprem.presentation.components.wrapper.PageWrapperState
import empire.digiprem.presentation.components.wrapper.WebDesktopPageWrapper
import empire.digiprem.presentation.intents.CompleteAccountIntent
import empire.digiprem.presentation.intents.HomeIntent
import empire.digiprem.presentation.intents.ProfileIntent
import empire.digiprem.presentation.models.HomeModel
import empire.digiprem.presentation.viewmodels.HomeViewModel
import empire.digiprem.presentation.viewmodels.ProfilViewModel
import empire.digiprem.presentation.views.Authentication.AppFormButton
import empire.digiprem.presentation.views.Authentication.FormErrorMessageSection
import empire.digiprem.ui.Screen.DetailRealEstateItemScreen
import empire.digiprem.ui.Screen.dashboard_screen.PageSection
import empire.digiprem.ui.Screen.dashboard_screen.screens.notifications.Notifications
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import kotlin.reflect.KFunction1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilView(
    viewProfil: ViewProfil,
    navController: NavHostController,
    profilViewModel: ProfilViewModel = koinViewModel()
) {
    // val profilViewModel:ProfilViewModel = viewModel{ProfilViewModel()}
    val state by profilViewModel.state.collectAsState()
    val pageState by profilViewModel.pageWrapperState.collectAsState()
    val onSendIntent = profilViewModel::onIntentHandler

    val image = remember { mutableStateOf<AppFile?>(null) }
    val pickeFile = PickStorageFile(
        input = "image/*",
        onResult = { appFile ->
            if (appFile != null) {
                image.value = appFile
            }
        }
    )
    val formPageLabelStyle: @Composable (String) -> Unit = @Composable { text ->
        Text(
            text = text, style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.surface
            )
        )
    }
    WebDesktopPageWrapper(
        modifier = Modifier,
        view = EnumView.ViewProfil,
        state = PageWrapperState(isSuccess = true)
    ) {
        Column(
            Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            Text("Welcome, Amanda", fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
            Text("Tue, 07 June 2022", color = Color.Gray, fontSize = 14.sp)

            Row(verticalAlignment = Alignment.CenterVertically) {
                image.value?.byteArray?.let {
                    Image(
                        bitmap = it.decodeToImageBitmap(),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } ?: AsyncImage(
                    model = state.profileUrl.url,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        "${DataBaseTemp.userProfile?.name} ${DataBaseTemp.userProfile?.firstName}",
                        fontWeight = FontWeight.Bold
                    )
                    Text(DataBaseTemp.userProfile?.email ?: "", color = Color.Gray)
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = { pickeFile.execute() }) {
                    Text("Edit")
                }
            }
            uploadImage(
                modifier = Modifier.size(100.dp),
                fileUploading = state.profileUrl,
                onChangeProfile = {
                    onSendIntent(ProfileIntent.OnProfilePictureChange(it))
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
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

            }
            AnimatedVisibility(
                (getPlatform() == Platform.DESKTOP || getPlatform() == Platform.WEB) && pageState.errorMessage.isNotEmpty()
            ) {
                FormErrorMessageSection(
                    enabled = true,
                    errorMessage = pageState.errorMessage,
                )

            }

            TitleCard(
                "Personal information"
            ) {
                SpacerComponent(
                    {
                        AppTextField(
                            label = { formPageLabelStyle("Nom *") },
                            modifier = Modifier.weight(1f),
                            value = state.lastNameTextField.value,
                            placeholder = "Nom",
                            onValueChange = {
                                onSendIntent(
                                    ProfileIntent.OnLastNameChange(
                                        it
                                    )
                                )
                            }
                        )
                    },
                    {
                        AppTextField(
                            label = { formPageLabelStyle("Prenom *") },
                            modifier = Modifier.weight(1f),
                            value = state.firstNameTextField.value,
                            placeholder = "Prénom",
                            onValueChange = {
                                onSendIntent(
                                    ProfileIntent.OnFirstNameChange(
                                        it
                                    )
                                )
                            }
                        )
                    }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val onclick: (Gender) -> Unit = { gender ->
                        onSendIntent(ProfileIntent.OnGenderSelect(gender))
                    }
                    Gender.entries.forEach { gender ->
                        Box(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .padding(5.dp)
                                .clickable { onclick(gender) },
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
                            ProfileIntent.OnPhoneChange(
                                it
                            )
                        )
                    }
                )
            }
            /*   Column(
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
                                   ProfileIntent.OnLastNameChange(
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
                                   ProfileIntent.OnFirstNameChange(
                                       it
                                   )
                               )
                           }
                       )
                   }


                   // Sexe
                   Row(
                       modifier = Modifier.fillMaxWidth(),
                       horizontalArrangement = Arrangement.spacedBy(10.dp),
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       val onclick: (Gender) -> Unit = { gender ->
                           onSendIntent(ProfileIntent.OnGenderSelect(gender))
                       }
                       Gender.entries.forEach { gender ->
                           Box(
                               modifier = Modifier
                                   .wrapContentWidth()
                                   .wrapContentHeight()
                                   .padding(5.dp)
                                   .clickable { onclick(gender) },
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
                               ProfileIntent.OnPhoneChange(
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
                                       ProfileIntent.OnTwoFactorAuthToggle(
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
                                       ProfileIntent.OnTwoFactorPrivacyToggle(
                                           it
                                       )
                                   )
                               }
                           )
                           TextButton(
                               onClick = { onSendIntent(ProfileIntent.OnClickPolicy) },
                           ) {
                               formPageLabelStyle("Lire la politique d'assurance")
                               *//*Text(
                                        text = AnnotatedString("Lire la politique d'assurance"),
                                        style = TextStyle(
                                            color = MaterialTheme.colorScheme.primary,
                                            textDecoration = TextDecoration.Underline,
                                            fontSize = 14.sp
                                        )
                                    )*//*
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
                                onSendIntent(ProfileIntent.OnSubmit)
                            }
                        )
                        AppOutlinedButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "")
                            Text("Retour")
                        }
                    }*/
        }
    }
}

@Composable
fun ProfileField(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(label, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        OutlinedTextField(
            value = value,
            onValueChange = {},
            placeholder = { Text("Your First Name") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
    }
}

@Composable
fun uploadImage(
    modifier: Modifier = Modifier,
    fileUploading: AppFileUploading,
    enabledModification: Boolean = true,
    onChangeProfile: (AppFile) -> Unit
) {
    val isCompactMobilePlatform = isCompactMobilePlatform()
    var enableSelectMenu by remember { mutableStateOf(false) }
    val takePicture = TakePicture { file -> file?.let { onChangeProfile(it) } }
    val pickFile = PickStorageFile { file -> file?.let { onChangeProfile(it) } }
    Box(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            modifier=Modifier.padding(10.dp),
            model = fileUploading.url,
            contentScale = ContentScale.Crop,
            contentDescription = "Profile",
        )
        if (fileUploading.isloading) {
          CircularProgressIndicator(Modifier.align(Alignment.Center).size(24.dp))
        }
        if (fileUploading.isError) {
            IconButton(
                modifier = Modifier.align(Alignment.Center),
                onClick = { fileUploading.onUploadingFailure() },
                colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.error)
            ) {
                Icon(Icons.Default.Try, contentDescription = "")
            }
        }
        if (enabledModification) {
            IconButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = {
                    //  if(isCompactMobilePlatform){
                    enableSelectMenu = !enableSelectMenu
                    // } else{
                    //   pickFile.execute()
                    //  }
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.background
                )
            ) {
                Icon(Icons.Default.PhotoCamera, contentDescription = "")

                if (enableSelectMenu) {
                    DropdownMenu(
                        containerColor = MaterialTheme.colorScheme.background,
                        expanded = enableSelectMenu,
                        onDismissRequest = { enableSelectMenu = !enableSelectMenu }
                    ) {
                        Row(
                            modifier = Modifier.height(50.dp).clickable { enableSelectMenu = !enableSelectMenu; takePicture.execute() }.padding(5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Box(
                                modifier = Modifier.size(50.dp).clip(RoundedCornerShape(5.dp)).background(MaterialTheme.colorScheme.surface)
                                    .padding(5.dp), contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.PhotoCamera, contentDescription = "")
                            }
                            Text("Camera", style = MaterialTheme.typography.titleMedium)
                        }
                        Row(
                            modifier = Modifier.height(50.dp).clickable { enableSelectMenu = !enableSelectMenu; pickFile.execute() }.padding(5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Box(
                                modifier = Modifier.size(50.dp).clip(RoundedCornerShape(5.dp)).background(MaterialTheme.colorScheme.surface)
                                    .padding(5.dp), contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Image, contentDescription = "")
                            }
                            Text("Gallery", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }

            }
        }

    }
}

@Composable
fun ProfileDropdown(label: String, value: String) {
    ProfileField(label, value) // Tu peux remplacer par un vrai Dropdown si besoin
}

data class AppFileUploading(
    val isloading: Boolean = false,
    val isError: Boolean = false,
    val url: String = "",
    val onUploadingFailure: () -> Unit = {},
)

@Composable
fun SpacerText(firstText: String, secondText: String) {
    SpacerComponent(
        { Text(firstText, style = MaterialTheme.typography.titleMedium.copy(Color.Gray)) },
        { Text(secondText, style = MaterialTheme.typography.titleMedium.copy(MaterialTheme.colorScheme.onBackground)) }
    )
}

@Composable
fun SpacerComponent(first: @Composable () -> Unit, second: @Composable () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        first()
        second()
    }
}

@Composable
fun TitleCard(title: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.wrapContentSize()) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        AppCardWrapperEx(
            modifier = Modifier.padding(start = 10.dp).clip(RoundedCornerShape(10.dp)).background(MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.wrapContentSize(), verticalArrangement = Arrangement.spacedBy(10.dp)){
                content()
            }
        }
    }
}