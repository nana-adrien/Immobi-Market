package empire.digiprem.data.local

import kotlinx.browser.window

class LocalStorage:ILocalStorage {
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