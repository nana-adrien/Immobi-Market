package empire.digiprem.data.local

import empire.digiprem.app.config.Log
import kotlinx.serialization.json.Json


class DataBaseStorage(val storage: ILocalStorage) {
    suspend inline fun <reified T> save(key: LocalStorageKey, value: T) {
        storage.saveData(key, Json.encodeToString(value))
    }
    suspend inline fun <reified T> get(key: LocalStorageKey): T? {
        try {
            return storage.getData(key)?.let { return Json.decodeFromString<T>(it) }
        } catch (e: Exception) {
            Log.e("DataBaseStorage", e.toString())
            return null
        }
    }

    suspend fun delete(key: LocalStorageKey): Boolean {
        return storage.delete(key)
    }

}