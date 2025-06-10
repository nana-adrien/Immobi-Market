package empire.digiprem.repository

import empire.digiprem.models.UserReceivedMessage
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface UserReceivedMessageRepository : JpaRepository<UserReceivedMessage?, UUID?>
