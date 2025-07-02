package empire.digiprem.utils

import empire.digiprem.dto.bien_immobilier.Chambre
import empire.digiprem.dto.bien_immobilier.Maison
import empire.digiprem.dto.bien_immobilier.get.RecuperUneOffreResponseDTO
import empire.digiprem.enums.TypeDHabitation
import empire.digiprem.enums.TypeDeBien
import empire.digiprem.model.NotificationItem
import empire.digiprem.models.database.Equipement
import kotlin.random.Random

object database {
    val offre= RecuperUneOffreResponseDTO(
        id = "12345",
        titre = "Exemple d'offre",
        description = "Description de l'offre",
        superficie = 100.0,
        utilisateurId = "User1234",
        caracteristique = Chambre(
            nombrePieces = 1,
            superficie = 0.0,
            typeDHabitation = TypeDHabitation.entries.get(Random.nextInt(TypeDHabitation.entries.size))
        ),
        // imagePrincipale = body.listeImages.first(),
        listeImages = emptyList(),
        prix = 100000,
        typeDeBien = TypeDeBien.CHAMBRE,
        // estEnLigne = true, // Par défaut, l'offre est en ligne
        // estExpire = false, // Par défaut, l'offre n'est pas expirée
    )
    val offre2= RecuperUneOffreResponseDTO(
        id = "12345",
        titre = "Exemple d'offre",
        description = "Description de l'offre",
        superficie = 100.0,
        utilisateurId = "User4321",
        caracteristique = Maison(
            superficie = 0.0,
            nombreCuisines = 0,
            nombreChambre = 6,
            nombreSalleDeBain = 2,
            typeDHabitation = TypeDHabitation.entries.get(Random.nextInt(TypeDHabitation.entries.size)) ,
        ),
        // imagePrincipale = body.listeImages.first(),
        listeImages = listOf("https://storage.googleapis.com/koutchoumi-listings-original-pictures/orig_6842f5226c42f.jpeg",
            "https://www.koutchoumi.com/fr/115348/maison-villa-a-vendre-douala-bonamoussadi-carrefour-yoro-joss-1-salon-s-3-chambre-s-2-salle-s-de-bains-80-000-000-fcfa"),
        prix = 100000,
        typeDeBien = TypeDeBien.MAISON,
        datePublication = "2023-10-01T12:00:00",
        // estEnLine = true, // Par défaut, l'offre est en ligne
        // estExpire = false, // Par défaut, l'offre n'est pas expirée

    )

   /* val equipementsTest = listOf(
        Equipement(
            id = "EQ1",
            nom = "Climatisation",
            typeDeBienAssocier = listOf(TypeDeBien.CHAMBRE, TypeDeBien.STUDIO, TypeDeBien.APPARTEMENT)
        ),
        Equipement(
            id = "EQ2",
            nom = "Chauffe-eau",
            typeDeBienAssocier = listOf(TypeDeBien.MAISON, TypeDeBien.VILLA, TypeDeBien.STUDIO)
        ),
        Equipement(
            id = "EQ3",
            nom = "Garage",
            typeDeBienAssocier = listOf(TypeDeBien.MAISON, TypeDeBien.VILLA)
        ),
        Equipement(
            id = "EQ4",
            nom = "Cuisine équipée",
            typeDeBienAssocier = listOf(TypeDeBien.STUDIO, TypeDeBien.APPARTEMENT, TypeDeBien.MAISON)
        ),
        Equipement(
            id = "EQ5",
            nom = "Système de sécurité",
            typeDeBienAssocier = listOf(TypeDeBien.VILLA, TypeDeBien.BUREAU, TypeDeBien.ESPACE_COMMERCIAL)
        ),
        Equipement(
            id = "EQ6",
            nom = "Clôture",
            typeDeBienAssocier = listOf(TypeDeBien.TERRAIN, TypeDeBien.MAISON, TypeDeBien.VILLA)
        ),
        Equipement(
            id = "EQ7",
            nom = "Générateur",
            typeDeBienAssocier = listOf(TypeDeBien.ESPACE_COMMERCIAL, TypeDeBien.BUREAU, TypeDeBien.VILLA)
        )
    )*/

    val offresImmobilieres = mutableListOf<RecuperUneOffreResponseDTO>(
        offre,
        offre2,
        offre,
        offre2,
        offre,
        offre2,
        offre,
        offre2,
        offre,
        offre2,
        offre,
        offre2,
        offre,
        offre2,
        offre,
        offre2,
    )


    val notifications = mutableListOf(
        NotificationItem(
            title = "Bienvenue sur DigiPrem !",
            body = "Votre inscription a été complétée avec succès.",
            time = "2025-06-29 08:15",
            isRead = false
        ),
        NotificationItem(
            title = "Nouvelle offre disponible",
            body = "Une nouvelle offre de studio est disponible à Bonamoussadi.",
            time = "2025-06-29 10:42",
            isRead = false
        ),
        NotificationItem(
            title = "Mot de passe changé",
            body = "Votre mot de passe a été modifié avec succès.",
            time = "2025-06-28 17:00",
            isRead = true
        ),
        NotificationItem(
            title = "Paiement reçu",
            body = "Votre paiement pour l'abonnement premium a été reçu.",
            time = "2025-06-27 14:23",
            isRead = true
        ),
        NotificationItem(
            title = "Message de l'administrateur",
            body = "Merci d’utiliser nos services. Nous restons à votre disposition.",
            time = "2025-06-26 09:00",
            isRead = false
        )
    )
    val updatedNotifiactions=notifications

}