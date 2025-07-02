package empire.digiprem.repositories.database

import empire.digiprem.models.database.Arrondissement
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface IArrondissementRepository : JpaRepository<Arrondissement, UUID>