package com.dicquemare.aiquizzgenerator.feature.create_deck.data.dto

import com.dicquemare.aiquizzgenerator.feature.home.domain.models.Deck
import kotlinx.serialization.Serializable

@Serializable
data class DeckDto(
    val id: String?,
    val title: String?,
    val cards: List<MultipleChoiceCardDto> = listOf()
) {
    fun toModel(): Deck {
        return Deck(
            id = id ?: "",
            title = title ?: "",
            cards = cards.map { it.toModel() }
        )
    }
}