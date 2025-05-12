package empire.digiprem.ui.Screen.dashboard_screen

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter

fun generateFakeRealEstateListCameroon(): List<RealEstateData> {
    fun painterStub(name: String): Painter = ColorPainter(Color.Gray)

    val tagsPool = listOf(
        Tag("Workspaces", "1500m²"),
        Tag("Garage", "Garage 2 voitures"),
        Tag("Pool", "Piscine privée"),
        Tag("Pets", "Animaux autorisés"),
        Tag("Security", "Sécurité 24/7"),
        Tag("WifiProtectedSetup", "Wi-Fi gratuit"),
        Tag("Elevator", "Avec ascenseur"),
        Tag("AC", "Climatisation"),
        Tag("View", "Vue sur la ville"),
        Tag("Sun", "Exposition plein sud"),
        Tag("Balcony", "Grand balcon"),
        Tag("Eco", "Panneaux solaires")
    )

    fun randomTags() = tagsPool.shuffled().take((2..5).random())

    return listOf(
        RealEstateData(
            "1",
            "Villa moderne",
            "Bonapriso - Douala",
            "90.000.000 FCFA",
            "il y a 2 jours",
            RealEstateCategories.MAISON,
            RealEstateType.VENDRE,
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
            RealEstateCategories.STUDIO,
            RealEstateType.LOUER,
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
            RealEstateCategories.TERRAIN,
            RealEstateType.VENDRE,
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
            RealEstateCategories.APPARTEMENT,
            RealEstateType.LOUER,
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
            RealEstateCategories.BUREAU,
            RealEstateType.LOUER,
            randomTags(),
            listOf(
                "https://images.coinafrique.com/thumb_5318644_uploaded_image1_1746734091.jpg",
                "https://images.coinafrique.com/thumb_5318703_uploaded_image1_1746736745.jpg"
            )
        ),
        RealEstateData(
            "6",
            "Chambre autonome",
            "Melen - Yaoundé",
            "35.000 FCFA/mois",
            "il y a 10 heures",
            RealEstateCategories.CHAMBRE,
            RealEstateType.LOUER,
            randomTags(),
            listOf(
                "https://images.coinafrique.com/thumb_5318477_uploaded_image1_1746728231.jpg"
            )
        ),
        RealEstateData(
            "7",
            "Boutique commerciale",
            "Nkoldongo - Yaoundé",
            "8.000.000 FCFA",
            "il y a 2 semaines",
            RealEstateCategories.BOUTIQUE,
            RealEstateType.VENDRE,
            randomTags(),
            listOf(
                "https://images.coinafrique.com/thumb_5318574_uploaded_image1_1746731458.jpg"
            )
        ),
        RealEstateData(
            "8",
            "Maison avec jardin",
            "Ndogbong - Douala",
            "60.000.000 FCFA",
            "il y a 2 jours",
            RealEstateCategories.MAISON,
            RealEstateType.VENDRE,
            randomTags(),
            listOf(
                "https://images.coinafrique.com/thumb_5318414_uploaded_image1_1746726342.jpg"
            )
        ),
        RealEstateData(
            "9",
            "Terrain agricole",
            "Obala - Centre",
            "3.500 FCFA/m²",
            "il y a 1 semaine",
            RealEstateCategories.TERRAIN,
            RealEstateType.VENDRE,
            randomTags(),
            listOf(
                "https://images.coinafrique.com/thumb_5318010_uploaded_image1_1746713815.jpg"
            )
        ),
        RealEstateData(
            "10",
            "Studio neuf",
            "Logbaba - Douala",
            "75.000 FCFA/mois",
            "il y a 8 heures",
            RealEstateCategories.STUDIO,
            RealEstateType.LOUER,
            randomTags(),
            listOf(
                "https://images.coinafrique.com/thumb_5317973_uploaded_image1_1746712710.jpg"
            )
        ),
        RealEstateData(
            "11",
            "Appartement 3 pièces",
            "Essos - Yaoundé",
            "250.000 FCFA/mois",
            "il y a 2 jours",
            RealEstateCategories.APPARTEMENT,
            RealEstateType.LOUER,
            randomTags(),
            listOf(
                "https://images.coinafrique.com/thumb_5317896_uploaded_image1_1746711033.jpg"
            )
        ),
        RealEstateData(
            "12",
            "Chambre avec douche",
            "Biyem-Assi - Yaoundé",
            "45.000 FCFA/mois",
            "il y a 6 heures",
            RealEstateCategories.CHAMBRE,
            RealEstateType.LOUER,
            randomTags(),
            listOf(
                "https://images.coinafrique.com/thumb_5317764_uploaded_image1_1746707827.jpg"
            )
        ),
        RealEstateData(
            "13",
            "Boutique rénovée",
            "Deïdo - Douala",
            "5.500.000 FCFA",
            "il y a 1 jour",
            RealEstateCategories.BOUTIQUE,
            RealEstateType.VENDRE,
            randomTags(),
            listOf(
                "https://images.coinafrique.com/thumb_5317790_uploaded_image1_1746708426.jpg"
            )
        ),
        RealEstateData(
            "14",
            "Immeuble bureaux",
            "Akwa - Douala",
            "150.000.000 FCFA",
            "il y a 5 jours",
            RealEstateCategories.BUREAU,
            RealEstateType.VENDRE,
            randomTags(),
            listOf(
                "https://images.coinafrique.com/thumb_5317730_uploaded_image1_1746706898.jpg"
            )
        ),
        RealEstateData(
            "15",
            "Villa avec piscine",
            "Odza - Yaoundé",
            "100.000.000 FCFA",
            "il y a 3 jours",
            RealEstateCategories.MAISON,
            RealEstateType.VENDRE,
            randomTags(),
            listOf(
                "https://images.coinafrique.com/thumb_5317958_uploaded_image1_1746712307.jpg"
            )
        )
    )
}