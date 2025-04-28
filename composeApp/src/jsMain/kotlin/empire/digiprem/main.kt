package empire.digiprem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.jetbrains.compose.web.renderComposable

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    renderComposable(rootElementId = "root") {
       App()
    }

}