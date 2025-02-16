package com.dicquemare.aiquizzgenerator.core

import com.dicquemare.aiquizzgenerator.feature.create_deck.di.createDeckModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(createDeckModule)
    }
}