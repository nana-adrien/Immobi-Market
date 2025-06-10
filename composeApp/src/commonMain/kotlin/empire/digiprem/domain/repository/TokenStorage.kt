package empire.digiprem.domain.repository

import okio.Path.Companion.toPath

interface TokenStorage {
    suspend fun saveToken(token: String)
    suspend  fun getToken(): String?
    suspend fun clearToken()
}
