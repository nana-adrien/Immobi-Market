
package empire.digiprem.core.helpers

import empire.digiprem.app.config.Log
import kotlinx.serialization.json.Json
import empire.digiprem.model.TokensResult
import kotlin.io.encoding.ExperimentalEncodingApi

fun TokensResult.Companion.getAccessTokenClaims(encodeToken: String = "", claims: String): String {
    return try {
        decodeToken(encodeToken)
            .replace("\"", "")
            .replace("{", "")
            .replace("}", "")
            .split(",")
            .filter { it.contains("$claims:") }
            .first()
            .replace("$claims:", "")
    } catch (error: Exception) {
        ""
    }.toString()
}

@OptIn(ExperimentalEncodingApi::class)
 fun TokensResult.Companion.decodeToken(encodeToken: String = ""): String{
   return  try {
        val payload = encodeToken.split(".")[1]
        val decoded = Base64Decoder.decodeBase64(payload)
       Log.e("TokensResult.decodeToken","${decoded.toString()},encodeToken =$encodeToken")
        decoded.toString()
    } catch (e: Exception) {
       Log.e("TokensResult.decodeToken","$e")
        ""
    }
}

expect object Base64Decoder {
    fun decodeBase64(encoded: String): String
}

fun String.deSerializeTokenstoJson(): TokensResult {
    var tokensResult = TokensResult()
    try {
        tokensResult = Json.decodeFromString<TokensResult>(this)
    } catch (error: Exception) {
        ""
    }
    return tokensResult
}