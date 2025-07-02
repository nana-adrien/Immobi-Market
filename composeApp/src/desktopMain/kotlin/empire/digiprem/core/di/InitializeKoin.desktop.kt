package empire.digiprem.core.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import empire.digiprem.data.local.ILocalStorage
import empire.digiprem.data.local.LocalStorage
import empire.digiprem.data.local.config.createDataStore
import empire.digiprem.domain.repository.DesktopTokenStorage
import empire.digiprem.domain.repository.TokenStorage
import org.koin.dsl.module

actual val initializeModules= module {
    single<DataStore<Preferences>>{createDataStore()}
    single<TokenStorage>{ DesktopTokenStorage(get()) }
    single<ILocalStorage>{ LocalStorage(get()) }
}