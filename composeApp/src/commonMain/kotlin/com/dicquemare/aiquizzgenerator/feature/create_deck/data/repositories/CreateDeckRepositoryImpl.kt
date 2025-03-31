package com.dicquemare.aiquizzgenerator.feature.create_deck.data.repositories


import com.dicquemare.aiquizzgenerator.AppDatabase
import com.dicquemare.aiquizzgenerator.core.ext.toIntOrNull
import com.dicquemare.aiquizzgenerator.core.utils.KMMLogger
import com.dicquemare.aiquizzgenerator.feature.create_deck.data.CreateDeckApiService
import com.dicquemare.aiquizzgenerator.feature.create_deck.data.converters.toEntity
import com.dicquemare.aiquizzgenerator.feature.create_deck.data.dto.CreateDeckDto
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.models.DeckCreationOptions
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.repositories.CreateDeckRepository
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.usecases.SaveCreationDeckOptionsParams
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.Deck
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.MultipleChoiceCard
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay

sealed class CreateDeckRepositoryError : Throwable() {
    object NoDeckCreationOptions : CreateDeckRepositoryError()
    object NoDeckCreated : CreateDeckRepositoryError()
}

class CreateDeckRepositoryImpl(
    private val database: AppDatabase,
    private val createDeckApiService: CreateDeckApiService
) : CreateDeckRepository {
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
        val currentDeckCreation = database.deckCreationOptionsTableQueries
            .selectCurrentDeckCreationOptions()
            .executeAsOneOrNull()
        currentDeckCreation?.let { data ->
            val deckDto = createDeckApiService.createDeck(CreateDeckDto.toDto(data.toEntity()))
            KMMLogger.d("CreateDeckApiService createDeck response : $deckDto")
            return deckDto.toModel()
        }
        throw CreateDeckRepositoryError.NoDeckCreationOptions
    }

    private fun randomString(length: Int): String {
        val chars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }
}
