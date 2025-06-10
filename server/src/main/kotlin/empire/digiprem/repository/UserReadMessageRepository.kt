package empire.digiprem.repository

import empire.digiprem.models.UserReadMessage
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserReadMessageRepository : JpaRepository<UserReadMessage?, UUID?>
