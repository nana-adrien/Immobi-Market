package empire.digiprem.repository

import empire.digiprem.models.Message
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MessageRepository : JpaRepository<Message?, UUID?>
