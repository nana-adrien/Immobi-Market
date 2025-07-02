package empire.digiprem.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import empire.digiprem.data.local.DataBaseTemp.token
import empire.digiprem.data.local.ILocalStorage
import empire.digiprem.data.local.LocalStorageKey
import empire.digiprem.enums.TokenEnum
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

 class LocalStorage (private val dataSource: DataStore<Preferences>): ILocalStorage {
     override suspend fun saveData(key: LocalStorageKey, value: String) {
         dataSource.edit { preferences ->
             preferences[stringPreferencesKey(key.getName())] = value
         }
     }
     override suspend fun getData(key: LocalStorageKey): String? {
         return dataSource.data.map { preferences -> preferences[stringPreferencesKey(key.getName())] }.first()
     }
     override suspend fun delete(key: LocalStorageKey): Boolean {
         try {
             dataSource.edit { preferences ->
                 preferences.remove(stringPreferencesKey(key.getName()))
             }
             return true
         } catch (ex: Exception) {
             return false
         }
     }
 }