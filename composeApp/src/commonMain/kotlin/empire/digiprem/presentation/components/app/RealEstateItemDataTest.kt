package empire.digiprem.presentation.components.app

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter

fun generateFakeRealEstateListCameroon(): List<RealEstateData> {
    fun painterStub(name: String): Painter = ColorPainter(Color.Gray)

    val tagsPools = listOf(
        Equipment("Workspaces", "1500m²"),
        Equipment("Garage", "Garage 2 voitures"),
        Equipment("Pool", "Piscine privée"),
        Equipment("Pets", "Animaux autorisés"),
        Equipment("Security", "Sécurité 24/7"),
        Equipment("WifiProtectedSetup", "Wi-Fi gratuit"),
        Equipment("Elevator", "Avec ascenseur"),
        Equipment("AC", "Climatisation"),
        Equipment("View", "Vue sur la ville"),
        Equipment("Sun", "Exposition plein sud"),
        Equipment("Balcony", "Grand balcon"),
        Equipment("Eco", "Panneaux solaires")
    )

    fun randomTags() = tagsPools.shuffled().take((2..5).random())


    return listOf(
        RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateType.MAISON,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/12/studio-meuble-Biyem-Assi33.jpeg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        ),
        RealEstateData(
            "2",
            "Studio meublé",
            "Bastos - Yaoundé",
            "120.000 FCFA/mois",
            "il y a 4 heures",
            RealEstateType.STUDIO,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg",
                "https://www.booking.com/hotel/cm/studio-meuble-bastos-yaounde1.html",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "3",
            "Terrain constructible",
            "Makepe - Douala",
            "15.000 FCFA/m²",
            "il y a 3 jours",
            RealEstateType.TERRAIN,
            state = RealEstateState.ON_HOLD,
            RealEstateCategories.A_VENDRE,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-7-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-9-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "4",
            "Appartement 2 chambres",
            "Elig-Essono - Yaoundé",
            "200.000 FCFA/mois",
            "il y a 1 jour",
            RealEstateType.APPARTEMENT,
            state = RealEstateState.ACTIVE,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-3-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg"
            )
        ),
        RealEstateData(
            "5",
            "Bureau équipé",
            "Bonanjo - Douala",
            "400.000 FCFA/mois",
            "il y a 5 jours",
            RealEstateType.BUREAU,
            state = RealEstateState.EXPIRED,
            RealEstateCategories.A_LOUER,
            randomTags(),
            listOf(
                "https://sacrevoyage.com/wp-content/uploads/2021/10/Studio-meuble-Bastos-Yaounde-11-scaled-1.jpg",
                "https://sacrevoyage.com/wp-content/uploads/2021/11/studio-golf-blanc-27-2-1.jpeg"
            )
        )
    )

}