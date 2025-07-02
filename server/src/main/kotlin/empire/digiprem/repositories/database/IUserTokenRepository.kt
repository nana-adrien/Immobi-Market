package empire.digiprem.repositories.database

import empire.digiprem.models.database.User
import empire.digiprem.models.database.UserToken
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface IUserTokenRepository:JpaRepository<UserToken, UUID> {
    fun findByUserAndId(user: User, id: UUID): Optional<UserToken>
    fun findByUser(user: User):Optional<List<UserToken>>
}