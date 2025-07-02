package empire.digiprem.controller.offre_immobiliere

import empire.digiprem.dto.bien_immobilier.*
import empire.digiprem.dto.bien_immobilier.create.EnregisterUneOffreReqDTO
import empire.digiprem.dto.bien_immobilier.create.EnregisterUneOffreResponseDTO
import empire.digiprem.dto.bien_immobilier.get.RecuperUneOffreResponseDTO
import empire.digiprem.enums.TypeDeBien
import empire.digiprem.extension.success
import empire.digiprem.model.ApiResponse2
import empire.digiprem.utils.database
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi

@Tag(name = "offres immobilières", description = "Gestion des offres immobilières")
@RestController
@RequestMapping("/api/v2/offre-immobiliere")
class OffreImmobiliereController {


    @GetMapping("/get-offre-details")
    fun recupererLeTypeEtLesEquipementDeLoffreACreer(@RequestParam typeDeBien: TypeDeBien):ApiResponse2<GetBienDetailResponseDTO>{
        val caracteristiqueOffreImmobilier=when(typeDeBien){
            TypeDeBien.NONE ->null
            TypeDeBien.CHAMBRE -> Chambre()
           // TypeDeBien.STUDIO -> TODO()
         //   TypeDeBien.APPARTEMENT -> TODO()
            TypeDeBien.MAISON -> Maison()
           // TypeDeBien.VILLA -> TODO()
          //  TypeDeBien.TERRAIN -> TODO()
           // TypeDeBien.BUREAU -> TODO()
           // TypeDeBien.ESPACE_COMMERCIAL -> TODO()
            else -> throw IllegalArgumentException("Type de bien inconnu")
        }
        return ApiResponse2.success(
            GetBienDetailResponseDTO(
                caracteristiques =caracteristiqueOffreImmobilier ,
                //equipements = database.equipementsTest.filter { it.typeDeBienAssocier.contains(typeDeBien) },
            )
        )
    }

    @OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)
    @PostMapping("/create")
    fun enregistrerUneOffreImmobiliere(@RequestBody body: EnregisterUneOffreReqDTO): ApiResponse2<EnregisterUneOffreResponseDTO> {
        // Logique pour enregistrer une offre immobilière
        // Cette méthode sera implémentée plus tard
        val offre = RecuperUneOffreResponseDTO(
            id = "Offre${Random.nextInt(0, 1000000)}",
            titre = body.titre,
            description = body.description,
            superficie = body.superficie,
            caracteristique = body.caracteristique,
            typeDoffre = body.typeDoffre,
            typeDeBien = body.typeDeBien,
            utilisateurId="User111",
            // imagePrincipale = body.listeImages.firstOrNull() ?: "",
            listeImages = body.listeImages,
            prix = body.prix,
            dateCreation = Clock.System.now().toString(),
         //   listEquipementId =database.equipementsTest.filter {/*body.listEquipementId.contains(it)*/}
        )
        database.offresImmobilieres.add(offre)
        return ApiResponse2.success(EnregisterUneOffreResponseDTO(idOffre = offre.id, " opperation reussit"))
    }

    @OptIn(ExperimentalTime::class)
    @PostMapping("/publier")
    fun publierUneOffre(@RequestBody body: PublierUneOffreReqDTO): ApiResponse2<PublierUneOffreResponseDTO> {
        var offre = database.offresImmobilieres.first { it.id == body.idOffre }
        if (offre.datePublication != "") {
            return ApiResponse2.success(PublierUneOffreResponseDTO("L'offre est déjà publiée."))
        }
        offre = offre.copy(datePublication = Clock.System.now().toString())
        database.offresImmobilieres.removeIf { it.id == body.idOffre }.also { database.offresImmobilieres.add(offre) }
        return ApiResponse2.success(PublierUneOffreResponseDTO(" opperation reussit"))
    }

    @GetMapping("/get-all")
    fun getAllOffresImmobilieres(): ApiResponse2<List<RecuperUneOffreResponseDTO>> {
        // Logique pour récupérer toutes les offres immobilières
        // Cette méthode sera implémentée plus tard
        return ApiResponse2.success(database.offresImmobilieres.filter { it.datePublication != "" })
    }

    @GetMapping("/get")
    fun getById(@RequestParam numeroDuBien: String): ApiResponse2<RecuperUneOffreResponseDTO> {
        // Logique pour récupérer toutes les offres immobilières
        // Cette méthode sera implémentée plus tard
        return ApiResponse2.success(database.offresImmobilieres.filter { it.id == numeroDuBien && it.datePublication != "" }
            .first())
    }
    @GetMapping("/get/utilisateurId")
    fun getByUser(@RequestParam utilisateurId: String): ApiResponse2<List<RecuperUneOffreResponseDTO>> {
        // Logique pour récupérer toutes les offres immobilières
        // Cette méthode sera implémentée plus tard
        return ApiResponse2.success(database.offresImmobilieres.filter { it.utilisateurId ==utilisateurId})
    }
}

