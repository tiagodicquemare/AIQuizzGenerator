package com.dicquemare.aiquizzgenerator.feature.home.domain.models



data class Deck (
    val id: String,
    val title: String,
    val cards: List<QuizCard>
) {
    fun cardsCount(): Int = cards.size
}