package com.onoffrice

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ArtWorkApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ArtWorkApplication)
            androidLogger()
            modules(modules)
        }
    }
}
