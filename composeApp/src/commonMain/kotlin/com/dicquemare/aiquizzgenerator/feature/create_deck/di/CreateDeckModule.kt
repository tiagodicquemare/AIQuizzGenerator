package com.dicquemare.aiquizzgenerator.feature.create_deck.di

import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.CreateDeckSharedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val createDeckModule = module {
    viewModel { CreateDeckSharedViewModel() }
}