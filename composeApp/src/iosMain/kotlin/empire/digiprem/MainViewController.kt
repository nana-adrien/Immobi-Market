package empire.digiprem

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import empire.digiprem.core.di.InitializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = { InitializeKoin() }
) { App(
    modifier = Modifier
        .fillMaxSize()
        .verticalScroll(stateVertical)
)
}