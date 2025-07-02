package empire.digiprem.models.database

import empire.digiprem.enums.Gender
import empire.digiprem.utils.convertor.GenderConverter
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*
import kotlin.uuid.ExperimentalUuidApi

@Entity
data class UserProfile constructor(
    @OneToOne
    @MapsId("user_id")
    val user: User,
    @Id val user_id: UUID,
    val nom: String = "",
    val prenom: String = "",
    @Column(columnDefinition = "text")
    val urlProfil: String = "",
    val dateNaissance: LocalDateTime? = null,
    val numeroTelephone: String = "",
    @Convert(converter = GenderConverter::class)
    val genre: Gender? = null,

    @OneToOne
    @JoinColumn(name = "telephoneUtilisateur_id", nullable = false)
    val telephoneUtilisateur: UserPhoneNumber? = null,
    //val telephoneUtilisateur_id: UUID = UUID.randomUUID(),
    val telephoneVerifie: Boolean = false,
    val profilComplet: Boolean = false,
    val enLigne: Boolean = false,
    val dateCreation: LocalDateTime = LocalDateTime.now(),
    var dernierTempsConnexion: LocalDateTime? = null,
)