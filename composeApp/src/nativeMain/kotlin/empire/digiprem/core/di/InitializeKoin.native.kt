package empire.digiprem.core.di

import empire.digiprem.data.local.ILocalStorage
import empire.digiprem.data.local.LocalStorage
import empire.digiprem.domain.repository.IOSTokenStorage
import empire.digiprem.domain.repository.TokenStorage
import org.koin.dsl.module

actual val initializeModules= module {
    single<DataStore<Preferences>> {createDataStore(androidContext())}
    single<TokenStorage>{ IOSTokenStorage() }
    single<ILocalStorage>{ LocalStorage(get()) }
}