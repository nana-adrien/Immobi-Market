package empire.digiprem.schedule

import empire.digiprem.repositories.database.UserAwaitingVerificationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class UserAwaitingVerificationScheduled {
    @Autowired
    private val userAwaitingVerificationRepository: UserAwaitingVerificationRepository? = null

    @Scheduled(fixedRate = 60000)
    fun deleteExpiredCodes() {
        val now = LocalDateTime.now()
        val expiredCodes = userAwaitingVerificationRepository!!.findByExpiresAtBefore(now)

        if (!expiredCodes.isEmpty()) {
            userAwaitingVerificationRepository.deleteAll(expiredCodes)
            println("Codes expirés supprimés : " + expiredCodes.size)
        }
    }
}