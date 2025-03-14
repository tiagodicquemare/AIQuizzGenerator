package com.dicquemare.aiquizzgenerator.feature.home.domain.repositories

import com.dicquemare.aiquizzgenerator.feature.home.domain.models.Deck

interface HomeRepository {
    suspend fun saveDeck(deck: Deck)
    suspend fun deleteDeckById(deckId: String)
    suspend fun getDecks(): List<Deck>
    suspend fun getDeckById(deckId: String): Deck?
}