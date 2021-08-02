package com.niv_angert.anydo

import android.app.Application
import com.niv_angert.anydo.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 *Created by Niv Angert on 02/08/2021
 **/
class AnyDoTaskApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin:
        startKoin {
            androidLogger()
            androidContext(this@AnyDoTaskApp)
            modules(appModule)
        }
    }
}