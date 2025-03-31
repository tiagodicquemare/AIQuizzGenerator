package com.dicquemare.aiquizzgenerator.feature.create_deck.di

import com.dicquemare.aiquizzgenerator.feature.create_deck.data.CreateDeckApiService
import com.dicquemare.aiquizzgenerator.feature.create_deck.data.repositories.CreateDeckRepositoryImpl
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.repositories.CreateDeckRepository
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.usecases.CreateDeckWithLLM
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.usecases.GetCurrentCreationDeckOptions
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.usecases.SaveCreationDeckOptions
import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.viewmodels.CreateDeckSharedViewModel
import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.viewmodels.VisualiseDeckViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val createDeckModule = module {
    viewModel {
        CreateDeckSharedViewModel(
            getCurrentCreationDeckOptions = get(),
            saveCreationDeckOptions = get(),
            createDeckWithLLM = get()
        )
    }
    viewModel {
        VisualiseDeckViewModel(
            getDeckById = get(),
            deleteDeckById = get()
        )
    }
    single<CreateDeckRepository> {
        CreateDeckRepositoryImpl(
            database = get(),
            createDeckApiService = get()
        )
    }
    factory { CreateDeckApiService(get()) }
    factory { SaveCreationDeckOptions(get()) }
    factory { GetCurrentCreationDeckOptions(get()) }
    factory { CreateDeckWithLLM(get(), get()) }

}