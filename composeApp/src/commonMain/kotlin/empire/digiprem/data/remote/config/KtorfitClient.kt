package empire.digiprem.data.remote.config

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import empire.digiprem.domain.servives.IJwtTokenService
import io.ktor.client.plugins.*
import kotlinx.serialization.json.Json

class KtorfitServiceCreator(val baseUrl: String,val httpClient:HttpClient, private val jwtTokenService: IJwtTokenService) {

    fun HttpRequestBuilder.addHeader(header: String, value: String) {
        if (this.headers[header].isNullOrEmpty()) {
            this.headers.append(header, value)
        } else if (this.headers[header] != value) {
            this.headers.remove(header)
            this.headers.append(header, value)
        }

    }







    private fun getKtorfit(): Ktorfit {
        return Ktorfit.Builder()
            .baseUrl(baseUrl)
            .httpClient(httpClient)
            .converterFactories(ApiResponseConverterFactory())
            .converterFactories(ApiResponse2ConverterFactory()).build()
    }

    fun apiClient() = getKtorfit()
}