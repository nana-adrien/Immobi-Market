package empire.digiprem.repositories.database

import empire.digiprem.models.database.Equipement
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface  EquipementRepository: JpaRepository<Equipement, UUID>