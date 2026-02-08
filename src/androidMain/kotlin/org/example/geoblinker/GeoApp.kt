package org.example.geoblinker

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.example.geoblinker.di.initKoin

/**
 * Android Application class
 * Инициализирует Koin при старте приложения
 */
class GeoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        initKoin {
            androidLogger()
            androidContext(this@GeoApp)
        }
    }
}
