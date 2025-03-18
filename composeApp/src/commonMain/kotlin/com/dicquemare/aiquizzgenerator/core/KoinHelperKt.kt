package com.dicquemare.aiquizzgenerator.core

import com.dicquemare.aiquizzgenerator.core.data.local.platformModule
import com.dicquemare.aiquizzgenerator.core.di.databaseModule
import com.dicquemare.aiquizzgenerator.feature.create_deck.di.createDeckModule
import com.dicquemare.aiquizzgenerator.feature.home.di.homeModule
import com.dicquemare.aiquizzgenerator.feature.play_deck.di.playDeckModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(additionalModules: List<Module> = emptyList()) {
    startKoin {
        modules(
            additionalModules + platformModule + databaseModule + createDeckModule + homeModule + playDeckModule
        )
    }
}
