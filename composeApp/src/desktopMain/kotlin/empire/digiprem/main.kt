package empire.digiprem
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import empire.digiprem.core.di.InitializeKoin

fun main() = application {
    InitializeKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Immobi-Maket",
    ) {
        App()
    }
}