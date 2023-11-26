package com.flexcode.freetogame.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(declaration: KoinAppDeclaration = {}) =
    startKoin {
        declaration
        modules(listOf(commonModule(), platformModule()))
    }