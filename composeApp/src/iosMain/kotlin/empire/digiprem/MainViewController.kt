package empire.digiprem

import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController { App(
    modifier = Modifier
        .fillMaxSize()
        .verticalScroll(stateVertical)
)
}