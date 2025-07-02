package empire.digiprem.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import empire.digiprem.app.config.Log
import empire.digiprem.data.config.DataSourceEventCollector
import empire.digiprem.data.config.DataSourceEventHandlerDecorator
import empire.digiprem.data.remote.dto.oauth.GetRealEstateResponse
import empire.digiprem.data.remote.service.RealStateService
import empire.digiprem.presentation.components.app.*
import empire.digiprem.presentation.intents.HomeIntent
import empire.digiprem.presentation.models.HomeModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import empire.digiprem.presentation.views.RealEstateFilterData
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : AbstractViewModel<HomeModel, HomeIntent>(defaultState = HomeModel()) {
    val realStateService = RealStateService()

    init {
        onIntentHandler(HomeIntent.OnInit)
    }

    override fun onRefreshPage() {
        super.onRefreshPage()
        onIntentHandler(HomeIntent.OnInit)
    }
    override fun onIntentHandler(intent: HomeIntent) {
        viewModelScope.launch {
            when (intent) {
                is HomeIntent.OnFilterRealEstates -> {
                    _mutableState.update {
                        it.copy(
                            realEstates = realEstateFilter(intent.realEstateFilter)
                        )
                    }
                }

                is HomeIntent.OnFilterRealEstatesType -> {
                    _mutableState.update {
                        it.copy(
                            realEstates = generateFakeRealEstateListCameroon().filter { if (intent.type == RealEstateType.ALL) true else it.type == intent.type }
                        )
                    }
                }

                HomeIntent.OnInit -> getAllRealState()
            }
        }
    }

    private suspend fun getAllRealState() {
        collectDataSourceEvent(
            realStateService.getAllRealEstate(),
            object : DataSourceEventHandlerDecorator<List<GetRealEstateResponse>>() {
                override suspend fun onSuccessConnexion(key: Any?, data: List<GetRealEstateResponse>) {
                    super.onSuccessConnexion(key, data)
                    Log.i("getAllRealState","$data")
                    _mutableState.update {
                        it.copy(
                            realEstates = data.map { item ->
                                RealEstateData(
                                    id = item.id,
                                    title = item.titre,
                                    location = "",
                                    price = "${item.prix}",
                                    type = RealEstateType.getType(item.typeDeBien),
                                    categories = RealEstateCategories.getCategories(item.typeDoffre) ,
                                    images = item.listeImages,
                                    postedAgo = "${item.datePublication}",
                                    equipment = item.listEquipementId.map {equipement->Equipment(iconName = "",value = equipement.nom) }
                                )
                            }
                        )
                    }
                }
            }
        )
    }

    private fun realEstateFilter(realEstateFilterData: RealEstateFilterData?): List<RealEstateData> {
        val realEstateDataList = generateFakeRealEstateListCameroon()
        return if (realEstateFilterData == null) realEstateDataList else realEstateDataList.filter { it.type == realEstateFilterData.type }
    }

}