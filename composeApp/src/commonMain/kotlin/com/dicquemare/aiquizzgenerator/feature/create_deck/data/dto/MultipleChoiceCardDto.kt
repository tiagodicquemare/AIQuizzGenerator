package com.dicquemare.aiquizzgenerator.feature.create_deck.data.dto

import com.dicquemare.aiquizzgenerator.core.ext.enumValueOrNull
import com.dicquemare.aiquizzgenerator.feature.create_deck.data.converters.toEntity
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.CardDifficulty
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.CardTheme
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.MultipleChoiceCard
import kotlinx.serialization.Serializable

@Serializable
data class MultipleChoiceCardDto(
    val id: String?,
    val question: String?,
    val choices: List<String> = listOf(),
    val correctAnswerIndex: Int?,
    val difficulty: String?,
    val theme: String?,
    val trivia: String?
) {
    fun toModel(): MultipleChoiceCard {
        return MultipleChoiceCard(
            id = id ?: "",
            question = question ?: "",
            choices = choices,
            correctAnswerIndex = correctAnswerIndex ?: 0,
            difficulty = this.difficulty?.let { enumValueOrNull<CardDifficulty>(it) }
                ?: CardDifficulty.MEDIUM,
            theme = this.theme?.let { enumValueOrNull<CardTheme>(it) } ?: CardTheme.INSTRUCTIVE,
            trivia = trivia ?: ""
        )
    }
}