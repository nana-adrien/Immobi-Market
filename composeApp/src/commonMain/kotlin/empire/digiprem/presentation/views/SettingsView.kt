package empire.digiprem.presentation.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import empire.digiprem.navigation.ViewSettings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.compose.viewmodel.koinViewModel
import androidx.navigation.NavHostController
import empire.digiprem.navigation.EnumView
import empire.digiprem.presentation.components.wrapper.PageWrapperState
import empire.digiprem.presentation.components.wrapper.WebDesktopPageWrapper
import empire.digiprem.presentation.viewmodels.SettingsViewModel
import empire.digiprem.presentation.intents.SettingsIntent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsView(
    viewSettings: ViewSettings,
    navController: NavHostController,
    settingsViewModel: SettingsViewModel = koinViewModel()
) {
    // val settingsViewModel:SettingsViewModel = viewModel{SettingsViewModel()}
    val state by settingsViewModel.state.collectAsState()
    val onSendIntent = settingsViewModel::onIntentHandler

    WebDesktopPageWrapper(
        modifier = Modifier,
        view = EnumView.ViewSettings,
        state = PageWrapperState(isSuccess = true)
    ) {
        SettingsScreen()
    }

}


@Composable
fun SettingsScreen(
    onToggleDarkMode: (Boolean) -> Unit = {},
    isDarkModeEnabled: Boolean = false,
    onNotificationToggle: (Boolean) -> Unit = {},
    areNotificationsEnabled: Boolean = true,
    onChangePasswordClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        AppCard {
            SettingToggleItem(
                title = "Dark Mode",
                subtitle = "Enable dark theme",
                checked = isDarkModeEnabled,
                onCheckedChange = onToggleDarkMode
            )
        }

        Divider(Modifier.padding(vertical = 8.dp))
        AppCard {
            SettingToggleItem(
                title = "Notifications",
                subtitle = "Push notifications",
                checked = areNotificationsEnabled,
                onCheckedChange = onNotificationToggle
            )
        }
        Divider(Modifier.padding(vertical = 8.dp))
        AppCard {
            SettingActionItem(
                title = "Change Password",
                subtitle = "Update your account password",
                onClick = onChangePasswordClick
            )
        }
        Divider(Modifier.padding(vertical = 8.dp))
        AppCard{
            SettingActionItem(
                title = "Logout",
                subtitle = "Sign out of your account",
                onClick = onLogoutClick,
                textColor = Color.Red
            )
        }

    }
}

@Composable
 fun AppCard( content:@Composable () -> Unit) {
    Card(
        colors = CardDefaults.cardColors().copy(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        content()
    }
}

@Composable
fun SettingToggleItem(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.SemiBold)
            Text(subtitle, fontSize = 12.sp, color = Color.Gray)
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@Composable
fun SettingActionItem(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    textColor: Color = Color.Unspecified
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Text(title, fontWeight = FontWeight.SemiBold, color = textColor)
        Text(subtitle, fontSize = 12.sp, color = Color.Gray)
    }
}
