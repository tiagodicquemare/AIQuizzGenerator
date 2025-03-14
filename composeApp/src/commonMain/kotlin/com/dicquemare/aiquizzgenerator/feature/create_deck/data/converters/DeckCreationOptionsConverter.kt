package com.dicquemare.aiquizzgenerator.feature.create_deck.data.converters

import com.dicquemare.aiquizzgenerator.DeckCreationOptionsEntity
import com.dicquemare.aiquizzgenerator.core.ext.enumValueOrNull
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.models.CardType
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.models.DeckCreationOptions
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.CardDifficulty
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.CardTheme

fun DeckCreationOptionsEntity.toEntity(): DeckCreationOptions {
    return DeckCreationOptions(
        subject = this.subject,
        difficulty = this.difficulty?.let { enumValueOrNull<CardDifficulty>(it) },
        type = this.type?.let { enumValueOrNull<CardType>(it) },
        theme = this.theme?.let { enumValueOrNull<CardTheme>(it) },
        cardCount = this.card_count?.toInt(),
        withTrivia = this.with_trivia?.let { it == 1L }
    )
}



