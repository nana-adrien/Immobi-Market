package empire.digiprem.models.database

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.util.*


@Entity
data class UserAddress(
    @Id val id: UUID = UUID.randomUUID(),
    val latitude: Double,
    val longitude: Double,
    @ManyToOne @JoinColumn(name = "rue_id")
    val rue: Rue
)

@Entity
data class Pays(
    @Id val id: UUID = UUID.randomUUID(),
    val nom: String
)


@Entity
data class Region(
    @Id val id: UUID = UUID.randomUUID(),
    val nom: String,

    @ManyToOne @JoinColumn(name = "pays_id")
    val pays: Pays
)

@Entity
data class Departement(
    @Id val id: UUID = UUID.randomUUID(),
    val nom: String,

    @ManyToOne @JoinColumn(name = "region_id")
    val region: Region
)

@Entity
data class Ville(
    @Id val id: UUID = UUID.randomUUID(),
    val nom: String,

    @ManyToOne @JoinColumn(name = "departement_id")
    val departement: Departement
)

@Entity
data class Arrondissement(
    @Id val id: UUID = UUID.randomUUID(),
    val nom: String,

    @ManyToOne @JoinColumn(name = "ville_id")
    val ville: Ville
)

@Entity
data class Quartier(
    @Id val id: UUID = UUID.randomUUID(),
    val nom: String,

    @ManyToOne @JoinColumn(name = "arrondissement_id")
    val arrondissement: Arrondissement
)

@Entity
data class Rue(
    @Id val id: UUID = UUID.randomUUID(),
    val nom: String,

    @ManyToOne @JoinColumn(name = "quartier_id")
    val quartier: Quartier
)

