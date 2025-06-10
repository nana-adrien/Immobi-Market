package empire.digiprem.domain.repository

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.File

class DesktopTokenStorage: TokenStorage {
    private val file = File("token.txt")
    override suspend fun saveToken(token: String) {
      file.writeText(token)
    }
    override suspend fun getToken(): String? {
        return if (file.exists()) file.readText() else null
    }
    override suspend fun clearToken() {
        if(!file.exists()) file.delete()
    }

}