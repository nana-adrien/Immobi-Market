package empire.digiprem.domain.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AndroidTokenStorage(val dataSource:DataStore<Preferences>):TokenStorage{

    private val tokenKey= stringPreferencesKey("Token")
    override suspend fun saveToken(token: String) {
        dataSource.edit { preferences ->
            preferences[tokenKey] = token
        }
    }

    override suspend fun getToken(): String? {
        return dataSource.data.map { preferences -> preferences[tokenKey] }.first()
    }

    override suspend fun clearToken() {
        dataSource.edit { preferences ->
            preferences.remove(tokenKey)
        }
    }

}