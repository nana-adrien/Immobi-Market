package empire.digiprem.data.local.repository

import empire.digiprem.data.local.DataBaseStorage
import empire.digiprem.data.local.ILocalStorage
import empire.digiprem.data.local.LocalStorageKey
import empire.digiprem.data.local.entities.Utilisateur
import empire.digiprem.domain.repository.IUserRepository

class UserRepository(private val storage: DataBaseStorage): IUserRepository {

    companion object{
        val UserStorageKey=object : LocalStorageKey {override fun getName()="User"}
    }
    override suspend fun saveUser(user: Utilisateur) {
        storage.save( UserStorageKey,user )
    }
    override suspend fun getUser(): Utilisateur? {
      return storage.get(UserStorageKey)
    }
    override suspend fun deleteUser(): Boolean {
        return storage.delete(UserStorageKey)
    }
}