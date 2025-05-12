package empire.digiprem

import android.app.Application
import empire.digiprem.core.di.InitializeKoin
import org.koin.android.ext.koin.androidContext

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        InitializeKoin(config = {
            androidContext(this@MyApplication)
        } )
    }
}