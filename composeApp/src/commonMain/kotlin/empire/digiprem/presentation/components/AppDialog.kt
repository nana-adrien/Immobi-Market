package empire.digiprem.presentation.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.changedToDown
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
 fun AppScrollableDialog(
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    scrollState: ScrollState = rememberScrollState(),
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(scrollState) // << scroll actif même autour
                    .pointerInput(Unit) {
                        awaitPointerEventScope {
                            while (true) {
                                val event = awaitPointerEvent()
                                // Intercepter uniquement les clics
                                if (event.changes.any { it.changedToUp() || it.changedToDown() }) {
                                    onDismissRequest()
                                    event.changes.forEach { it.consume() }
                                }
                            }
                        }
                    }
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier.then(modifier).fillMaxWidth(0.7f).fillMaxHeight().padding(top = 100.dp)// Limite la hauteur pour permettre le scroll
                        .background(Color.White, shape = RoundedCornerShape(10.dp))
                        .pointerInput(Unit) {
                            awaitPointerEventScope {
                                while (true) {
                                    val event = awaitPointerEvent()
                                    if (event.changes.any { it.changedToUp() || it.changedToDown() }) {
                                        event.changes.forEach { it.consume() }
                                    }
                                }
                            }
                        }
                ) {
                    content()
                }
            }
            AppVerticalScrollBar(
                modifier = Modifier.align(Alignment.CenterEnd),
                scrollState = scrollState
            )
        }

    }
}
