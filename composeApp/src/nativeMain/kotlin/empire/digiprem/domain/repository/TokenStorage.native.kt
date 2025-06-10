package empire.digiprem.domain.repository

import io.ktor.http.ContentDisposition.Companion.File

class IOSTokenStorage:TokenStorage{
    override suspend fun saveToken(token: String) {

    }

    override suspend fun getToken(): String? {

    }

    override suspend fun clearToken() {

    }

}