package empire.digiprem.core.di

import empire.digiprem.domain.repository.TokenStorage
import empire.digiprem.domain.repository.WasmJsTokenStorage
import io.ktor.http.auth.*
import org.koin.dsl.module

actual val initializeModules= module {
    single<TokenStorage>{WasmJsTokenStorage()}
}