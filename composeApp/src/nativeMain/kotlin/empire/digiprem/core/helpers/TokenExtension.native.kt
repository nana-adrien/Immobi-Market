package empire.digiprem.core.helpers

actual object Base64Decoder {
    actual fun decodeBase64(encoded: String): String {
        val nsData = NSData.create(encoded, NSDataBase64DecodingIgnoreUnknownCharacters)
        return nsData?.toString(Charsets.UTF_8) ?: ""
    }
}