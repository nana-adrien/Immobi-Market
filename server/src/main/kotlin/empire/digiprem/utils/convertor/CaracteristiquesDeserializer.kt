package empire.digiprem.utils.convertor

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import empire.digiprem.dto.bien_immobilier.*
import empire.digiprem.dto.bien_immobilier.create.EnregisterUneOffreReqDTO
import empire.digiprem.enums.TypeDeBien
import empire.digiprem.models.database.OffreImmobiliere
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
