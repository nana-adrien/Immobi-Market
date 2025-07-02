package empire.digiprem.models.database

import empire.digiprem.enums.EtatDeL_Offre
import empire.digiprem.enums.TypeDeBien
import empire.digiprem.enums.TypeDoffre
import empire.digiprem.models.database.caracteristique.Caracteristiques
import empire.digiprem.utils.convertor.EtatDeL_OffreConverter
import empire.digiprem.utils.convertor.TypeDeBienConverter
import empire.digiprem.utils.convertor.TypeDoffreConverter
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Représente une offre immobilière dans l'application.
 *
 * @property id Identifiant unique de l'offre.
 * @property titre Titre de l'offre.
 * @property description Description détaillée de l'offre.
 * @property superficie Superficie du bien en mètres carrés.
 * @property imagePrincipale URL de l'image principale de l'offre.
 * @property listeImages Liste des URLs des images supplémentaires de l'offre.
 * @property prix Prix du bien en unités monétaires.
 * @property typeDeBien Type de bien immobilier (ex: appartement, maison, etc.).
 * @property estEnLigne Indique si l'offre est actuellement en ligne.
 * @property estExpire Indique si l'offre a expiré.
 * @property dateCreation Date de création de l'offre.
 * @property datePublication Date de publication de l'offre.
 * @property dateExpiration Date d'expiration de l'offre.
 * @property typeDoffre Type d'offre (ex: à vendre, à louer).
 */

@Entity
data class OffreImmobiliere @OptIn(ExperimentalUuidApi::class) constructor(

    @Id val id: UUID = UUID.randomUUID(),
    val titre: String,
    val description: String,
    val superficie: Double,
    @Convert(converter = TypeDeBienConverter::class)
    val typeDeBien: TypeDeBien,
    @Convert(converter = EtatDeL_OffreConverter::class)
    val etat:EtatDeL_Offre,
    @Convert(converter = TypeDoffreConverter::class)
    val typeDoffre: TypeDoffre,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    val listeImages: List<Image>,
    val prix: Int,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "caracteristique_id", nullable = false)
    val caracteristique: Caracteristiques,
  //  val caracteristique_id: UUID = UUID.randomUUID(),
    @ManyToOne
    @JoinColumn(name = "createur_id", nullable = false)
    val createur: User,
 //   val createur_id: UUID = UUID.randomUUID(),
    val dateCreation: LocalDateTime,
    val datePublication: LocalDateTime,
    val dateExpiration: LocalDateTime,
)
