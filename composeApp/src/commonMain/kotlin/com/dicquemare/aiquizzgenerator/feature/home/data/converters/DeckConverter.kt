package com.dicquemare.aiquizzgenerator.feature.home.data.converters

import com.dicquemare.aiquizzgenerator.DeckEntity
import com.dicquemare.aiquizzgenerator.MultipleChoiceCardEntity
import com.dicquemare.aiquizzgenerator.core.ext.enumValueOrNull
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.models.CardType
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.CardDifficulty
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.CardTheme
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.Deck
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.MultipleChoiceCard
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.QuizCard
import kotlinx.serialization.json.Json

fun DeckEntity.toEntity(quizCards: List<QuizCard>): Deck {
    return Deck(
        id = this.id.toString(),
        title = this.title,
        cards = quizCards
    )
}

fun MultipleChoiceCardEntity.toEntity(): MultipleChoiceCard {
    return MultipleChoiceCard(
        id = this.id,
        difficulty = enumValueOrNull<CardDifficulty>(this.difficulty) ?: CardDifficulty.MEDIUM,
        theme = enumValueOrNull<CardTheme>(this.theme) ?: CardTheme.INSTRUCTIVE ,
        question = this.question,
        choices =  Json.decodeFromString(this.choices),
        correctAnswerIndex = this.correctAnswerIndex.toInt(),
        trivia = this.trivia
    )
}