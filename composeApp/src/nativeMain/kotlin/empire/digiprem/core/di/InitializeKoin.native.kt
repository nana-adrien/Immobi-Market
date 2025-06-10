package empire.digiprem.core.di

import empire.digiprem.domain.repository.IOSTokenStorage
import empire.digiprem.domain.repository.TokenStorage
import org.koin.dsl.module

actual val initializeModules= module {
    single<TokenStorage>{ IOSTokenStorage() }
}