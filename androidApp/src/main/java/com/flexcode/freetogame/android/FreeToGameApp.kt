package com.flexcode.freetogame.android

import android.app.Application
import com.flexcode.freetogame.di.initKoin
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class FreeToGameApp: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin{
            androidLogger(level = if (BuildConfig.DEBUG) Level.INFO  else Level.NONE)
            androidContext(androidContext = this@FreeToGameApp)
        }
    }
}