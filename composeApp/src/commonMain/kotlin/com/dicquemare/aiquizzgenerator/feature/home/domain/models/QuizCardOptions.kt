package com.dicquemare.aiquizzgenerator.feature.home.domain.models

enum class CardDifficulty {
    EASY,
    MEDIUM,
    HARD
}

enum class CardTheme {
    FUNNY,
    INTERESTING,
    UNUSUAL,
    INSTRUCTIVE,
}

data class QuizCardOptions (
    val difficulty: CardDifficulty = CardDifficulty.MEDIUM,
    val theme: CardTheme = CardTheme.INTERESTING
)