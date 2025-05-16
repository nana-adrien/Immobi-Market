package empire.digiprem.core.utils

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.*

@OptIn(ExperimentalComposeUiApi::class)
actual fun Modifier.pointerEvent(
    eventType: PointerEventType,
    pass: PointerEventPass,
    onEvent: AwaitPointerEventScope.(event: PointerEvent) -> Unit
):Modifier =this.onPointerEvent(eventType, pass,onEvent)

