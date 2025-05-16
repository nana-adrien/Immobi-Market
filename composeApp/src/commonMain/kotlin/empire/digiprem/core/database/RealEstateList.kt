package empire.digiprem.core.database

import androidx.compose.runtime.mutableStateOf
import empire.digiprem.presentation.components.app.generateFakeRealEstateListCameroon

object AppDataBase {
   var RealEstateList= mutableStateOf(generateFakeRealEstateListCameroon())
}