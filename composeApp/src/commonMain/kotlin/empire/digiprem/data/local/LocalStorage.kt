package empire.digiprem.data.local

import empire.digiprem.enums.TokenEnum

interface LocalStorageKey{
    abstract fun getName(): String
}

 interface ILocalStorage {
    suspend fun saveData(key: LocalStorageKey, value: String)
    suspend fun getData(key: LocalStorageKey): String?
    suspend fun delete(key: LocalStorageKey):Boolean
}
