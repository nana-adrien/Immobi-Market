package empire.digiprem.repository

import empire.digiprem.models.Messages
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MessageRepository : JpaRepository<Messages?, UUID?>
