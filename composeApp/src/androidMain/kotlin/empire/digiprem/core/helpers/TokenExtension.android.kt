package empire.digiprem.core.helpers

import android.os.Build
import androidx.annotation.RequiresApi

actual object Base64Decoder {
    @RequiresApi(Build.VERSION_CODES.O)
    actual fun decodeBase64(encoded: String): String {
        return String(java.util.Base64.getDecoder().decode(encoded))
    }
}