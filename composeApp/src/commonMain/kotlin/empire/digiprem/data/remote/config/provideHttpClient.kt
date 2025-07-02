package empire.digiprem.data.remote.config

import empire.digiprem.data.remote.dto.oauth.Caracteristique
import empire.digiprem.data.remote.dto.oauth.CaracteristiqueChambre
import empire.digiprem.data.remote.dto.oauth.CaracteristiqueMaison
import empire.digiprem.data.remote.interceptor.AddRequestHeaderInterceptor
import empire.digiprem.data.remote.interceptor.LogHttpRequestInterceptor
import empire.digiprem.data.remote.service.OAuthEndPointEndPointService
import empire.digiprem.domain.repository.TokenStorage
import empire.digiprem.domain.servives.remote.IOAuthEndPointService
import empire.digiprem.model.Chambre
import empire.digiprem.model.Maison
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

fun provideHttpClient(baseUrl:String,tokenStorage: TokenStorage) :HttpClient=HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    classDiscriminator = "typeDeBien"
                    serializersModule = SerializersModule {
                        polymorphic(Caracteristique::class,CaracteristiqueMaison::class,CaracteristiqueMaison.serializer())
                        polymorphic(Caracteristique::class,CaracteristiqueChambre::class,CaracteristiqueChambre.serializer())
                    }
                }
            )
        }
        defaultRequest {
            url(baseUrl)
            contentType(ContentType.Application.Json)
        }
    }
    .addInterceptor(AddRequestHeaderInterceptor(tokenStorage,OAuthEndPointEndPointService()))
    .addInterceptor(LogHttpRequestInterceptor())






fun HttpClient.addInterceptor(block: HttpSendInterceptor):HttpClient{
   this.plugin(
        HttpSend
    ).intercept(block)
    return this
}