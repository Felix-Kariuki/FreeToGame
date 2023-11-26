package com.flexcode.freetogame

import com.flexcode.freetogame.di.commonModule
import com.flexcode.freetogame.di.platformModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(listOf(commonModule(), platformModule()))
    }
}