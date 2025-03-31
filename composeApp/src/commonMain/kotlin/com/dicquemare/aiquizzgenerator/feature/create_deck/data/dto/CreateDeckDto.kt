package com.dicquemare.aiquizzgenerator.feature.create_deck.data.dto

import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.models.DeckCreationOptions
import kotlinx.serialization.Serializable

@Serializable
data class CreateDeckDto(
    val subject: String? = null,
    val type: String? = null,
    val difficulty: String? = null,
    val theme: String? = null,
    val cardCount: Int? = null,
    val withTrivia: Boolean? = null
) {
    companion object {
        fun toDto(options: DeckCreationOptions): CreateDeckDto {
            return CreateDeckDto(
                subject = options.subject,
                type = options.type?.name,
                difficulty = options.difficulty?.name,
                theme = options.theme?.name,
                cardCount = options.cardCount,
                withTrivia = options.withTrivia
            )
        }
    }
}
