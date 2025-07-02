package empire.digiprem.data.remote.repository

import empire.digiprem.data.remote.config.KtorfitServiceCreator
import empire.digiprem.data.remote.dto.oauth.GetRealEstateResponse
import empire.digiprem.domain.repository.remote.IRealStateEndPointRepository
import empire.digiprem.model.ApiResponse2
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RealStateEndPointRepository : IRealStateEndPointRepository,KoinComponent {

    val ktorfitService:KtorfitServiceCreator by inject()

    override suspend fun getAllRealEstate(): ApiResponse2<List<GetRealEstateResponse>> {
        return ktorfitService.apiClient().create<IRealStateEndPointRepository>().getAllRealEstate()
    }

    override suspend fun getRealEstateById(): ApiResponse2<GetRealEstateResponse> {
        TODO("Not yet implemented")
    }
}