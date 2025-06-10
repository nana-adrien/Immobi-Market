package empire.digiprem.data.remote.interceptor

import empire.digiprem.app.config.Log
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

class HttpRequestInterceptor:HttpSendInterceptor {
    override suspend fun invoke(p1: Sender, p2: HttpRequestBuilder): HttpClientCall {
        Log.i("HttpRequestInterceptor",
            "Request URL: ${p2.url}\n" +
                " Request body: ${p2.body}\n "+
                    "Request headers: ${p2.headers.entries().joinToString("\n") { "${it.key}: ${it.value}" }}")

      return  p1.execute(p2)
    }

}