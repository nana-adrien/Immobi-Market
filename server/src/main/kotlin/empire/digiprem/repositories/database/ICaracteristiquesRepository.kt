package empire.digiprem.repositories.database

import empire.digiprem.models.database.caracteristique.Caracteristiques
import org.springframework.data.jpa.repository.JpaRepository
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface ICaracteristiquesRepository : JpaRepository<Caracteristiques, Uuid> {}