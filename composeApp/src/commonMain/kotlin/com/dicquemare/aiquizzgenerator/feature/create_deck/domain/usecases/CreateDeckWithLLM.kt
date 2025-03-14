package com.dicquemare.aiquizzgenerator.feature.create_deck.domain.usecases

import com.dicquemare.aiquizzgenerator.core.domain.BaseUseCase
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.repositories.CreateDeckRepository
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.Deck
import com.dicquemare.aiquizzgenerator.feature.home.domain.repositories.HomeRepository

class CreateDeckWithLLM(
    private val createDeckRepository: CreateDeckRepository,
    private val homeRepository: HomeRepository
) :
    BaseUseCase<Unit, Deck>() {
    override suspend fun execute(params: Unit): Deck {
        val deck = createDeckRepository.createDeckWithLLM()
        homeRepository.saveDeck(deck)
        return deck
    }
}