package com.dicquemare.aiquizzgenerator.feature.home.data.repositories

import com.dicquemare.aiquizzgenerator.AppDatabase
import com.dicquemare.aiquizzgenerator.core.ext.enumValueOrNull
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.models.CardType
import com.dicquemare.aiquizzgenerator.feature.home.data.converters.toEntity
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.CardDifficulty
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.CardTheme
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.Deck
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.MultipleChoiceCard
import com.dicquemare.aiquizzgenerator.feature.home.domain.repositories.HomeRepository
import kotlinx.serialization.json.Json

class HomeRepositoryImpl(private val database: AppDatabase) : HomeRepository {
    override suspend fun saveDeck(deck: Deck) {
        database.deckTableQueries.insertDeck(id = deck.id, title = deck.title)
        deck.cards.forEach { card ->
            database.quizCardTableQueries.insertCard(
                id = card.id,
                deckId = deck.id,
                difficulty = card.options.difficulty.name,
                theme = card.options.theme.name,
                type = if (card is MultipleChoiceCard) CardType.MULTIPLE_CHOICE.name else "UNKNOWN",
            )
            if (card is MultipleChoiceCard) {
                database.multipleChoiceCardTableQueries.insertCard(
                    id = card.id,
                    question = card.question,
                    choices = Json.encodeToString(card.choices),
                    correctAnswerIndex = card.correctAnswerIndex.toLong(),
                    difficulty = card.options.difficulty.name,
                    theme = card.options.theme.name,
                    trivia = card.trivia
                )
            }
        }
    }

    override suspend fun deleteDeckById(deckId: String) {
        val deck = getDeckById(deckId) ?: return
        deck.cards.forEach { card ->
            database.quizCardTableQueries.deleteCardById(card.id)
        }
        database.deckTableQueries.deleteDeck(deckId)
    }

    override suspend fun getDecks(): List<Deck> {
        return database.deckTableQueries.selectAllDecks().executeAsList()
            .groupBy { it.deckId to it.deckTitle }
            .map { (deckKey, cards) ->
                Deck(
                    id = deckKey.first,
                    title = deckKey.second,
                    cards = cards.mapNotNull { row ->
                        when (row.quizCardType) {
                            CardType.MULTIPLE_CHOICE.name -> row.multipleChoiceQuestion?.let {
                                MultipleChoiceCard(
                                    id = row.quizCardId ?: "",
                                    difficulty = enumValueOrNull<CardDifficulty>(row.quizCardDifficulty)
                                        ?: CardDifficulty.MEDIUM,
                                    theme = enumValueOrNull<CardTheme>(row.quizCardTheme)
                                        ?: CardTheme.INSTRUCTIVE,
                                    question = it,
                                    choices = if (row.multipleChoiceChoices != null) {
                                        Json.decodeFromString(row.multipleChoiceChoices)
                                    } else {
                                        emptyList()
                                    },
                                    correctAnswerIndex = row.multipleChoiceCorrectAnswerIndex?.toInt()
                                        ?: 0,
                                    trivia = row.multipleChoiceTrivia
                                )
                            }

                            else -> null
                        }
                    }
                )
            }
    }

    override suspend fun getDeckById(deckId: String): Deck? {
        val rows = database.deckTableQueries.selectDeckById(deckId).executeAsList()

        if (rows.isEmpty()) {
            return null
        }
        return Deck(
            id = rows.first().deckId,
            title = rows.first().deckTitle,
            cards = rows.mapNotNull { row ->
                when (row.quizCardType) {
                    CardType.MULTIPLE_CHOICE.name -> row.multipleChoiceQuestion?.let {
                        MultipleChoiceCard(
                            id = row.quizCardId ?: "",
                            difficulty = enumValueOrNull<CardDifficulty>(row.quizCardDifficulty)
                                ?: CardDifficulty.MEDIUM,
                            theme = enumValueOrNull<CardTheme>(row.quizCardTheme)
                                ?: CardTheme.INSTRUCTIVE,
                            question = it,
                            choices = if (row.multipleChoiceChoices != null) {
                                Json.decodeFromString(row.multipleChoiceChoices)
                            } else {
                                emptyList()
                            },
                            correctAnswerIndex = row.multipleChoiceCorrectAnswerIndex?.toInt()
                                ?: 0,
                            trivia = row.multipleChoiceTrivia
                        )
                    }

                    else -> null
                }
            }
        )
    }
}