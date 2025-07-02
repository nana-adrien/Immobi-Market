package empire.digiprem.core.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import empire.digiprem.data.local.config.createDataStore
import empire.digiprem.data.remote.config.HttpConstants
import empire.digiprem.data.remote.config.KtorfitServiceCreator
import empire.digiprem.data.remote.config.provideHttpClient
import empire.digiprem.domain.repository.AndroidTokenStorage
import empire.digiprem.domain.repository.TokenStorage
import io.ktor.client.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val initializeModules= module {
    single<DataStore<Preferences>> {createDataStore(androidContext())}
    single<TokenStorage> { AndroidTokenStorage(get()) }/*
    single<HttpClient>{ provideHttpClient("192.168.137.1",get()) }
    single <KtorfitServiceCreator>{ KtorfitServiceCreator("192.168.137.1",get(),get()) }*/
}