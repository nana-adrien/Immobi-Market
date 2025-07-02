package empire.digiprem.core.helpers

//import kotlin.io.encoding.Base64
import java.util.Base64

actual object Base64Decoder {
    actual fun decodeBase64(encoded: String): String {
        return Base64.getDecoder().decode(encoded).decodeToString()
    }
}