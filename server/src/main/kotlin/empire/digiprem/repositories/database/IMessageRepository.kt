package empire.digiprem.repositories.database//.database

import empire.digiprem.models.database.Message
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface IMessageRepository: JpaRepository<Message, UUID> {

}