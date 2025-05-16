package empire.digiprem.core.utils

import androidx.compose.ui.Modifier

actual fun androidx.compose.ui.Modifier.pointerEvent(
    eventType: androidx.compose.ui.input.pointer.PointerEventType,
    pass: androidx.compose.ui.input.pointer.PointerEventPass,
    onEvent: androidx.compose.ui.input.pointer.AwaitPointerEventScope.(event: androidx.compose.ui.input.pointer.PointerEvent) -> kotlin.Unit
) : Modifier =this
