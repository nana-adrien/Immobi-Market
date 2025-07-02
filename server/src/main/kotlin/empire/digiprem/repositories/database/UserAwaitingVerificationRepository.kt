package empire.digiprem.repositories.database

import empire.digiprem.models.database.UserAwaitingVerification
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime
import java.util.*

interface UserAwaitingVerificationRepository : CrudRepository<UserAwaitingVerification, UUID> {
    fun findByExpiresAtBefore(now: LocalDateTime): List<UserAwaitingVerification>
    fun getUserAwaitingVerificationByIdentifier(identifier: String): Optional<UserAwaitingVerification>
    fun getUserAwaitingVerificationByCode(code: String?): UserAwaitingVerification
}
