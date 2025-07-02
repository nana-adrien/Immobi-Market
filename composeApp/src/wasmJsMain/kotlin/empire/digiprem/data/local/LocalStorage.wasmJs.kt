package empire.digiprem.data.local

import empire.digiprem.data.local.DataBaseTemp.token
import empire.digiprem.data.local.ILocalStorage
import empire.digiprem.data.local.LocalStorageKey
import empire.digiprem.domain.repository.getExpirateTime
import empire.digiprem.enums.TokenEnum
import kotlinx.browser.document
import kotlinx.browser.window

 class LocalStorage: ILocalStorage {
     override suspend fun saveData(key: LocalStorageKey, value: String) {
         window.localStorage.setItem(key.getName(), value)
     }

     override suspend fun getData(key: LocalStorageKey): String? {
       return  window.localStorage.getItem(key.getName())
     }

     override suspend fun delete(key: LocalStorageKey): Boolean {
        try {
            window.localStorage.removeItem (key.getName())
            return true
        }catch (e:Exception){
         return false
        }
     }
 }