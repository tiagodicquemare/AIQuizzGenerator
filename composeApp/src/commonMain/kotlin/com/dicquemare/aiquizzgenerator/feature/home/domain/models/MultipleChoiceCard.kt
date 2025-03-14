package com.dicquemare.aiquizzgenerator.feature.home.domain.models

data class MultipleChoiceCard(
    override val id: String,
    val question: String,
    val choices: List<String>,
    val correctAnswerIndex: Int,
    private val difficulty: CardDifficulty = CardDifficulty.MEDIUM,
    private val theme: CardTheme = CardTheme.INTERESTING,
    val trivia: String? = null
) : QuizCard(
    id = id,
    options = QuizCardOptions(
        difficulty = difficulty,
        theme = theme
    )
)