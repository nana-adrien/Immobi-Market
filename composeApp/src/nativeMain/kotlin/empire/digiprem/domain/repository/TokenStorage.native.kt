package empire.digiprem.domain.repository

import empire.digiprem.enums.TokenEnum
import io.ktor.http.ContentDisposition.Companion.File
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class IOSTokenStorage(private val dataSource: DataStore<Preferences>):TokenStorage{
    override suspend fun saveToken(key: TokenEnum, token: String) {
        dataSource.edit { preferences ->
            preferences[stringPreferencesKey(key.name)] = token
        }
    }
    override suspend fun getToken(key: TokenEnum): String? {
        return dataSource.data.map { preferences -> preferences[stringPreferencesKey(key.name)] }.first()
    }
    override suspend fun isValidToken(token: String): Boolean {
        TODO("Not yet implemented")
    }
    override suspend fun clearToken(key: TokenEnum) {
        dataSource.edit { preferences ->
            preferences.remove(stringPreferencesKey(key.name))
        }
    }

}