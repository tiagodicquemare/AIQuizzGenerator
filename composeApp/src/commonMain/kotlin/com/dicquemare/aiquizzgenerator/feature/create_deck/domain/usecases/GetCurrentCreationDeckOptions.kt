package com.dicquemare.aiquizzgenerator.feature.create_deck.domain.usecases

import com.dicquemare.aiquizzgenerator.core.domain.BaseUseCase
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.models.DeckCreationOptions
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.repositories.CreateDeckRepository

class GetCurrentCreationDeckOptions(
    private val repository: CreateDeckRepository
) : BaseUseCase<Unit, DeckCreationOptions>() {
    override suspend fun execute(params: Unit): DeckCreationOptions {
        return repository.getCurrentCreationDeck()
    }
}