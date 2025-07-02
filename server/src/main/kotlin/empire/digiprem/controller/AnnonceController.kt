/*
package empire.digiprem.controller

import empire.digiprem.service.IAnnonceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/annonces")
class AnnonceController(private val annonceService: IAnnonceService) {

    @PostMapping
    fun creerAnnonce(@RequestBody request: AnnonceRequest): ResponseEntity<AnnonceDTO> {
        return ResponseEntity.ok(annonceService.creerAnnonce(request))
    }

    @PutMapping("/{id}")
    fun modifierAnnonce(
        @PathVariable id: String,
        @RequestBody request: AnnonceUpdateRequest
    ): ResponseEntity<AnnonceDTO> {
        return ResponseEntity.ok(annonceService.modifierAnnonce(id, request))
    }

    @PutMapping("/{id}/publier")
    fun changerStatutPublication(
        @PathVariable id: String,
        @RequestParam publier: Boolean
    ): ResponseEntity<AnnonceDTO> {
        return ResponseEntity.ok(annonceService.changerStatutPublication(id, publier))
    }

    @DeleteMapping("/{id}")
    fun supprimerAnnonce(@PathVariable id: String): ResponseEntity<Void> {
        annonceService.supprimerAnnonce(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/proprietaire/{proprietaireId}")
    fun getAnnoncesParProprietaire(@PathVariable proprietaireId: String): ResponseEntity<List<AnnonceDTO>> {
        return ResponseEntity.ok(annonceService.getAnnoncesParProprietaire(proprietaireId))
    }

    @PostMapping("/{id}/republier")
    fun republierAnnonce(@PathVariable id: String): ResponseEntity<AnnonceDTO> {
        return ResponseEntity.ok(annonceService.republierAnnonce(id))
    }
}
*/
