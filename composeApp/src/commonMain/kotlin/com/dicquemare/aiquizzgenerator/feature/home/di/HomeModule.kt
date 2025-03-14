package com.dicquemare.aiquizzgenerator.feature.home.di

import com.dicquemare.aiquizzgenerator.feature.home.data.repositories.HomeRepositoryImpl
import com.dicquemare.aiquizzgenerator.feature.home.domain.repositories.HomeRepository
import com.dicquemare.aiquizzgenerator.feature.home.domain.usecases.DeleteDeckById
import com.dicquemare.aiquizzgenerator.feature.home.domain.usecases.GetAllDecks
import com.dicquemare.aiquizzgenerator.feature.home.domain.usecases.GetDeckById
import com.dicquemare.aiquizzgenerator.feature.home.domain.usecases.SaveDeck
import org.koin.dsl.module

val homeModule = module {
    single<HomeRepository> { HomeRepositoryImpl(database = get()) }
    factory { SaveDeck(homeRepository = get()) }
    factory { GetAllDecks(homeRepository = get()) }
    factory { GetDeckById(homeRepository = get()) }
    factory { DeleteDeckById(homeRepository = get()) }
}