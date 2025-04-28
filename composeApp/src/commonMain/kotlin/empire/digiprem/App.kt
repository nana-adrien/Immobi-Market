package empire.digiprem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import empire.digiprem.ui.LoginScreen
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        LoginScreen()
    }
}