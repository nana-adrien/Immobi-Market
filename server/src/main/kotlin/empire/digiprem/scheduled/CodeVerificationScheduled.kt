package empire.digiprem.scheduled

import empire.digiprem.repositories.CodeVerificationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class CodeVerificationScheduled {
    @Autowired
    private lateinit var codeVerificationRepository: CodeVerificationRepository

    @Scheduled(fixedRate = 60000)
    fun deleteExpiredCodes() {
        val now = LocalDateTime.now()
        val expiredCodes = codeVerificationRepository.findByExpiresAtBefore(now)

        if (!expiredCodes!!.isEmpty()) {
            codeVerificationRepository.deleteAll(expiredCodes)
            println("Codes expirés supprimés : " + expiredCodes.size)
        }
    }
}
