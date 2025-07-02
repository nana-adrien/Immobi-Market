package empire.digiprem.domain.repository

import empire.digiprem.data.local.entities.Utilisateur

interface IUserRepository {
    suspend fun saveUser(user: Utilisateur)
    suspend fun getUser(): Utilisateur?
    suspend fun deleteUser():Boolean
}

