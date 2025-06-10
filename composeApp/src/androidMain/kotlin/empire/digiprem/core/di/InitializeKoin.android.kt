package empire.digiprem.core.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import empire.digiprem.data.local.config.createDataStore
import empire.digiprem.domain.repository.AndroidTokenStorage
import empire.digiprem.domain.repository.TokenStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val initializeModules= module {
    single<DataStore<Preferences>> {createDataStore(androidContext())}
    single<TokenStorage> { AndroidTokenStorage(get()) }
}