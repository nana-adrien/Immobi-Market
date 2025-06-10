package empire.digiprem.repository

import empire.digiprem.models.Attachment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface AttachmentRepository : JpaRepository<Attachment?, UUID?>
