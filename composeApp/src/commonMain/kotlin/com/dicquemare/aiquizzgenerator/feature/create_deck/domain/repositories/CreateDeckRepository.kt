package com.dicquemare.aiquizzgenerator.feature.create_deck.domain.repositories

import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.models.DeckCreationOptions
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.usecases.SaveCreationDeckOptionsParams
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.Deck

interface CreateDeckRepository {
    suspend fun saveCreationDeck(params: SaveCreationDeckOptionsParams): DeckCreationOptions
    suspend fun getCurrentCreationDeck(): DeckCreationOptions
    suspend fun createDeckWithLLM(): Deck
}
