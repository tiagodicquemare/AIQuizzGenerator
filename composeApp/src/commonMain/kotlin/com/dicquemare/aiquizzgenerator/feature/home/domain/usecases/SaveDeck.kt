package com.dicquemare.aiquizzgenerator.feature.home.domain.usecases

import com.dicquemare.aiquizzgenerator.core.domain.BaseUseCase
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.Deck
import com.dicquemare.aiquizzgenerator.feature.home.domain.repositories.HomeRepository

class SaveDeck (private val homeRepository: HomeRepository) : BaseUseCase<Deck, Unit>() {
    override suspend fun execute(params: Deck) {
        homeRepository.saveDeck(params)
    }
}