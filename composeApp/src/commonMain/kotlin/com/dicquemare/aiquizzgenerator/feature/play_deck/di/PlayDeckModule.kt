package com.dicquemare.aiquizzgenerator.feature.play_deck.di

import com.dicquemare.aiquizzgenerator.feature.play_deck.presentation.SimplePlayDeckViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val playDeckModule = module {
    viewModel { SimplePlayDeckViewModel(getDeckById = get()) }
}