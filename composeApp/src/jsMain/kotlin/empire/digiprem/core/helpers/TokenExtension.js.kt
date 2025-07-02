package empire.digiprem.core.helpers

import kotlinx.browser.window

actual object Base64Decoder {
    actual fun decodeBase64(encoded: String): String {
        return window.atob(encoded)
    }
}