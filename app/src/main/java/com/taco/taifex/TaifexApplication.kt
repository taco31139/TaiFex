package com.taco.taifex

import android.app.Application
import com.taco.taifex.koin.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TaifexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TaifexApplication)
            modules(koinModules)
        }
    }
}