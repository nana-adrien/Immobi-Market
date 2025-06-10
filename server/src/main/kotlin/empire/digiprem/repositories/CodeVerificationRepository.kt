package empire.digiprem.repositories

import empire.digiprem.models.CodeVerification
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime
import java.util.*


interface CodeVerificationRepository : CrudRepository<CodeVerification, UUID> {
    fun findByExpiresAtBefore(now: LocalDateTime?): List<CodeVerification?>?
    fun getCodeVerificationsByIdentifier(identifier: String): Optional<CodeVerification>
}
