package com.dicquemare.aiquizzgenerator.feature.home.domain.models

enum class CardType {
    MULTIPLE_CHOICE,
}

abstract class QuizCard(
    open val type: CardType,
    open val options: QuizCardOptions
)
