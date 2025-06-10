package empire.digiprem.app.service


import empire.digiprem.core.helpers.getAccessTokenClaims
import empire.digiprem.domain.repository.TokenStorage
import empire.digiprem.domain.servives.IJwtTokenService
import empire.digiprem.model.TokenHelper
import empire.digiprem.model.TokensResult
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json

class JwtTokenService(private val tokenStorage: TokenStorage) : IJwtTokenService {
    override suspend fun saveCurrentRequest(url: String) {
    }
    override suspend fun getGetCurrentRequestKey(): String? {
        return tokenStorage.getToken()
    }
    override suspend fun saveSubscriptionKey(key: String) {

    }
    override suspend fun getSubscriptionKey(): String? {
        return null
    }
    override suspend fun saveToken(tokenResult: TokensResult) {
        tokenStorage.saveToken(Json.encodeToString(tokenResult))
    }
    override suspend fun getToken(): TokensResult? {
        try {
            return tokenStorage.getToken()?.let {Json.decodeFromString<TokensResult>(it)}
        }catch (e: Exception){
            e.printStackTrace()
            return null
        }
    }
    override suspend fun isTokenExist(): Boolean {
       return getToken() != null
    }
    override suspend fun getRefreshToken(): String {
        return ""
    }
    override suspend fun saveTenantId(tenantId: String) {

    }
    override suspend fun getTenantId(): String? {
        return null
    }
    override suspend fun refreshToken(refreshToken: String) {

    }
    override suspend fun accessTokenExpired(token: String): Boolean {
        try {
            getToken()?.let {
             val tokenExpirationTime=TokensResult.getAccessTokenClaims(
                    encodeToken = token,
                    TokenHelper.EXPIRATION_TIME
                ).toLong()
                val currentTime = Clock.System.now().toEpochMilliseconds()
                currentTime > tokenExpirationTime
            }
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return true
        }
    }
    override suspend fun removeTokens() {
         tokenStorage.clearToken()
    }
}