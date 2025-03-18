package com.dicquemare.aiquizzgenerator.feature.create_deck.data.repositories


import com.dicquemare.aiquizzgenerator.AppDatabase
import com.dicquemare.aiquizzgenerator.core.ext.toIntOrNull
import com.dicquemare.aiquizzgenerator.feature.create_deck.data.converters.toEntity
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.models.DeckCreationOptions
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.repositories.CreateDeckRepository
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.usecases.SaveCreationDeckOptionsParams
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.Deck
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.MultipleChoiceCard
import kotlinx.coroutines.delay


class CreateDeckRepositoryImpl(private val database: AppDatabase) : CreateDeckRepository {
    override suspend fun saveCreationDeck(params: SaveCreationDeckOptionsParams): DeckCreationOptions {
        val currentDeckCreation = database.deckCreationOptionsTableQueries
            .selectCurrentDeckCreationOptions()
            .executeAsOneOrNull()

        if (currentDeckCreation != null) {
            database.deckCreationOptionsTableQueries.updateDeck(
                subject = params.subject ?: currentDeckCreation.subject,
                type = params.type?.toString() ?: currentDeckCreation.type,
                difficulty = params.difficulty?.toString() ?: currentDeckCreation.difficulty,
                theme = params.theme?.toString() ?: currentDeckCreation.theme,
                card_count = params.cardCount?.toLong() ?: currentDeckCreation.card_count,
                with_trivia = params.withTrivia?.toIntOrNull() ?: currentDeckCreation.with_trivia
            )
        } else {
            database.deckCreationOptionsTableQueries.insertDeck(
                subject = params.subject ?: "",
                type = params.type?.toString(),
                difficulty = params.difficulty?.toString(),
                theme = params.theme?.toString(),
                card_count = params.cardCount?.toLong(),
                with_trivia = params.withTrivia?.toIntOrNull()
            )
        }

        return database.deckCreationOptionsTableQueries
            .selectCurrentDeckCreationOptions()
            .executeAsOneOrNull()?.toEntity() ?: DeckCreationOptions()
    }

    override suspend fun getCurrentCreationDeck(): DeckCreationOptions {
        return database.deckCreationOptionsTableQueries
            .selectCurrentDeckCreationOptions()
            .executeAsOneOrNull()?.toEntity() ?: DeckCreationOptions()
    }

    override suspend fun createDeckWithLLM(): Deck {
        delay(1500) // Simulate network delay
        return Deck(
            id = randomString(8),
            title = "Deck title",
            cards = listOf(
                MultipleChoiceCard(
                    id = randomString(8),
                    question = "What is the capital of France?",
                    choices = listOf("Paris", "London", "Berlin", "Madrid"),
                    correctAnswerIndex = 0
                ),
                MultipleChoiceCard(
                    id = randomString(8),
                    question = "What is the capital of Germany?",
                    choices = listOf("Paris", "London", "Berlin", "Madrid"),
                    correctAnswerIndex = 2
                ),
                MultipleChoiceCard(
                    id = randomString(8),
                    question = "What is the capital of Spain?",
                    choices = listOf("Paris", "London", "Berlin", "Madrid"),
                    correctAnswerIndex = 3
                ),
                MultipleChoiceCard(
                    id = randomString(8),
                    question = "What is the capital of England?",
                    choices = listOf("Paris", "London", "Berlin", "Madrid"),
                    correctAnswerIndex = 1
                ),
                MultipleChoiceCard(
                    id = randomString(8),
                    question = "What is the capital of Italy?",
                    choices = listOf("Paris", "London", "Berlin", "Madrid"),
                    correctAnswerIndex = 0
                ),
                MultipleChoiceCard(
                    id = randomString(8),
                    question = "What is the capital of Portugal?",
                    choices = listOf("Paris", "London", "Berlin", "Madrid"),
                    correctAnswerIndex = 3
                ),
            )
        )
    }

    private fun randomString(length: Int): String {
        val chars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }
}
