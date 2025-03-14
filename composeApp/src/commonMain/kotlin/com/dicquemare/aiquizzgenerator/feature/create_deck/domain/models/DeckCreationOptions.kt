package com.dicquemare.aiquizzgenerator.feature.create_deck.domain.models

import com.dicquemare.aiquizzgenerator.feature.home.domain.models.CardDifficulty
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.CardTheme

enum class CardType {
    MULTIPLE_CHOICE,
    TRUE_FALSE,
    OPEN_ENDED
}

data class DeckCreationOptions (
    val subject: String? = null,
    val type: CardType? = null,
    val difficulty: CardDifficulty? = null,
    val theme: CardTheme? = null,
    val cardCount: Int? = null,
    val withTrivia: Boolean? = null
)
