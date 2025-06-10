package empire.digiprem.domain.servives

import empire.digiprem.model.TokensResult


interface IJwtTokenService {
   suspend fun saveCurrentRequest(url:String)
   suspend fun getGetCurrentRequestKey(): String?
   suspend fun saveSubscriptionKey(key: String)
   suspend fun getSubscriptionKey(): String?
   suspend fun saveToken(tokenResult: TokensResult)
   suspend fun getToken(): TokensResult?
   suspend fun isTokenExist():Boolean
   suspend fun getRefreshToken():String
   suspend fun saveTenantId(tenantId:String)
   suspend fun getTenantId(): String?
   suspend fun refreshToken(refreshToken : String)
   suspend fun accessTokenExpired(token : String) : Boolean
   suspend fun removeTokens()
}