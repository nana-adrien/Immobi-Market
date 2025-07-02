package empire.digiprem.data.remote.interceptor

import empire.digiprem.data.config.DataSourceEventCollector
import empire.digiprem.data.config.DataSourceEventHandlerDecorator
import empire.digiprem.data.local.DataBaseTemp
import empire.digiprem.data.remote.service.OAuthEndPointEndPointService
import empire.digiprem.domain.repository.TokenStorage
import empire.digiprem.dto.auth.refresh_token.RefreshTokenResponseDTO
import empire.digiprem.enums.TokenEnum
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*

class AddRequestHeaderInterceptor(
    val tokenStorage: TokenStorage,
    val oAuthService: OAuthEndPointEndPointService,
) : HttpSendInterceptor {


    override suspend fun invoke(p1: Sender, p2: HttpRequestBuilder): HttpClientCall {


        var request = p2
        val dataSourceCollector = DataSourceEventCollector()
        val urlPath = p2.url.encodedPath

        val isLoginEndpoint =
            urlPath.contains("/login") ||
                    urlPath.contains("/register") ||
                    urlPath.contains("/verify-identity")

        if (!isLoginEndpoint) {
            val accessToken = tokenStorage.getToken(TokenEnum.ACCESS_TOKEN)
            if (accessToken != null) {
               // if (tokenStorage.isValidToken(accessToken)) {
                    p2.headers.append("Authorization", "Bearer $accessToken")
                val reponse =p1.execute(p2)
                if (reponse.response.status==HttpStatusCode.Unauthorized){
                    // If the access token is invalid, try to refresh it
                    val savedRequest = p2
                    val refreshToken = tokenStorage.getToken(TokenEnum.REFRESH_TOKEN)
                    if (refreshToken != null && tokenStorage.isValidToken(refreshToken)) {
                        dataSourceCollector.collectEvent(
                            oAuthService.refreshToken(refreshToken),
                            object : DataSourceEventHandlerDecorator<RefreshTokenResponseDTO>() {
                                override suspend fun onSuccessConnexion(key: Any?, data: RefreshTokenResponseDTO) {
                                    tokenStorage.saveToken(TokenEnum.ACCESS_TOKEN, data.tokensResult.accessToken)
                                    tokenStorage.saveToken(TokenEnum.REFRESH_TOKEN, data.tokensResult.refreshToken)
                                    request = savedRequest
                                    request.headers.append(
                                        "Authorization",
                                        "Bearer ${data.tokensResult.accessToken}"
                                    )
                                }
                            }
                        )
                    }
                } else{
                    return reponse
                }
               // }
              /*  else {
                    var savedRequest = p2
                    val refreshToken = tokenStorage.getToken(TokenEnum.REFRESH_TOKEN)
                    if (refreshToken != null && tokenStorage.isValidToken(refreshToken)) {
                        dataSourceCollector.collectEvent(
                            oAuthService.refreshToken(refreshToken),
                            object : DataSourceEventHandlerDecorator<RefreshTokenResponseDTO>() {
                                override suspend fun onSuccessConnexion(key: Any?, data: RefreshTokenResponseDTO) {
                                    tokenStorage.saveToken(TokenEnum.ACCESS_TOKEN, data.tokensResult.accessToken)
                                    tokenStorage.saveToken(TokenEnum.ACCESS_TOKEN, data.tokensResult.refreshToken)
                                    request = savedRequest
                                    request.headers.append(
                                        "Authorization",
                                        "Bearer ${data.tokensResult.accessToken}"
                                    )
                                }
                            }
                        )
                    }
                }*/
            }
        }

        return p1.execute(request)
    }
}