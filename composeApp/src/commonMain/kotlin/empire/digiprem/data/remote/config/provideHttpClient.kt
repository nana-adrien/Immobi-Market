package empire.digiprem.data.remote.config

import empire.digiprem.data.remote.interceptor.LogHttpRequestInterceptor
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun provideHttpClient(baseUrl:String) :HttpClient=HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    prettyPrint = true
                }
            )
        }
        defaultRequest {
            url(baseUrl)
            contentType(ContentType.Application.Json)
        }
    }
    .addInterceptor(LogHttpRequestInterceptor())
    //.addInterceptor(LogHttpRequestInterceptor())






fun HttpClient.addInterceptor(block: HttpSendInterceptor):HttpClient{
   this.plugin(
        HttpSend
    ).intercept(block)
    return this
}