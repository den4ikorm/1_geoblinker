package org.example.geoblinker.android

import android.app.Application
import org.example.geoblinker.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GeoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GeoApp)
            modules(appModule)
        }
    }
}
