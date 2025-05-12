package empire.digiprem

import androidx.compose.ui.ExperimentalComposeUiApi
import empire.digiprem.core.di.InitializeKoin
import org.jetbrains.compose.web.renderComposable

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    renderComposable(rootElementId = "root") {
        InitializeKoin()
       App(

       )

    }

}