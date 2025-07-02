package empire.digiprem.domain.repository.remote

import de.jensklingenberg.ktorfit.http.GET
import empire.digiprem.data.remote.dto.oauth.GetRealEstateResponse
import empire.digiprem.dto.profile.GetProfileResponse
import empire.digiprem.model.ApiResponse2

interface IRealStateEndPointRepository {
    @GET("v2/offre-immobiliere/get-all")
    suspend fun getAllRealEstate(): ApiResponse2<List<GetRealEstateResponse>>
    @GET("v2/offre-immobiliere/get/")
    suspend fun getRealEstateById(): ApiResponse2<GetRealEstateResponse>

}