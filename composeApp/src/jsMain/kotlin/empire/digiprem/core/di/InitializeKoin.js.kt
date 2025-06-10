package empire.digiprem.core.di

import empire.digiprem.domain.repository.JsTokenStorage
import empire.digiprem.domain.repository.TokenStorage
import org.koin.dsl.module

actual val initializeModules= module {
    single<TokenStorage>{ JsTokenStorage() }
}