package empire.digiprem.app.model.components

enum class UserRole { DEMANDEUR, PROPRIETAIRE, ADMIN }

data class User(
    val id: String,
    val nom: String,
    val email: String,
    val roles: List<UserRole> = listOf(UserRole.DEMANDEUR)
)

data class ProprieteDemande(
    val userId: String,
    val justificatifs: List<String>,
    val status: DemandeStatus = DemandeStatus.EN_ATTENTE
)

enum class DemandeStatus { EN_ATTENTE, ACCEPTEE, REFUSEE }
