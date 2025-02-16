package com.dicquemare.aiquizzgenerator.feature.home.domain.models

data class MultipleChoiceCard(
    val question: String,
    val choices: List<String>,
    val correctAnswerIndex: Int,
    val difficulty: CardDifficulty = CardDifficulty.MEDIUM,
    val theme: CardTheme = CardTheme.INTERESTING,
    val trivia: String? = null
) : QuizCard(
    type = CardType.MULTIPLE_CHOICE,
    options = QuizCardOptions(
        difficulty = difficulty,
        theme = theme
    )
)