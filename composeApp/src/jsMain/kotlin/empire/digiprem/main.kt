package empire.digiprem

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import org.jetbrains.compose.web.renderComposable

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    renderComposable(rootElementId = "root") {
       App(

       )

    }

}