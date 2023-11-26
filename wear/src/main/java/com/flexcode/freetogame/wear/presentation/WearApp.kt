package com.flexcode.freetogame.wear.presentation

import android.app.Application
import com.flexcode.freetogame.di.initKoin
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class WearApp: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin{
            androidLogger(level = if (BuildConfig.DEBUG) Level.INFO  else Level.NONE)
            androidContext(androidContext = this@WearApp)
        }
    }
}