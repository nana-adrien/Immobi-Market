package empire.digiprem.domain.repository

import empire.digiprem.enums.TokenEnum

interface TokenStorage {
    suspend fun saveToken(key: TokenEnum, token: String)
    suspend  fun getToken(key:TokenEnum): String?
    suspend  fun isValidToken(token: String): Boolean
    suspend fun clearToken(key:TokenEnum)
}


