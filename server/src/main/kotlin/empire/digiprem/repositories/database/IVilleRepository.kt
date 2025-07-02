package empire.digiprem.repositories.database

import empire.digiprem.models.database.Ville
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface IVilleRepository : JpaRepository<Ville, UUID>