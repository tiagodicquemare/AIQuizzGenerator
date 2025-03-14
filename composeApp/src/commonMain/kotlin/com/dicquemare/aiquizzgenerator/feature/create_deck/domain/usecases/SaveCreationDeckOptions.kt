package com.dicquemare.aiquizzgenerator.feature.create_deck.domain.usecases

import com.dicquemare.aiquizzgenerator.core.domain.BaseUseCase
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.models.CardType
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.models.DeckCreationOptions
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.repositories.CreateDeckRepository
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.CardDifficulty
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.CardTheme

class SaveCreationDeckOptions(
    private val repository: CreateDeckRepository
) : BaseUseCase<SaveCreationDeckOptionsParams, DeckCreationOptions>() {
    override suspend fun execute(params: SaveCreationDeckOptionsParams): DeckCreationOptions {
        return repository.saveCreationDeck(params)
    }
}

data class SaveCreationDeckOptionsParams(
    val subject: String? = null,
    val type: CardType? = null,
    val difficulty: CardDifficulty? = null,
    val theme: CardTheme? = null,
    val cardCount: Int? = null,
    val withTrivia: Boolean? = null
)
