package com.dicquemare.aiquizzgenerator.feature.generate_pdf.presentation

import com.dicquemare.aiquizzgenerator.feature.home.domain.models.Deck
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.MultipleChoiceCard

class SimplePdfHtmlCssGenerator(private val deck: Deck) {
    fun buildPdfHtmlFile(): String {
        return buildString {
            appendLine("<!DOCTYPE html>")
            appendLine("<html>")
            appendLine("<head>")
            appendLine("<style>")
            appendLine(bodyCss)
            appendLine(deckTitleCss)
            appendLine(questionTitleCss)
            appendLine(optionCss)
            appendLine("</style>")
            appendLine("</head>")
            appendLine("<body>")
            appendDeckContent(this@SimplePdfHtmlCssGenerator.deck)
            appendLine("</body>")
            appendLine("</html>")
        }
    }

    private fun StringBuilder.appendDeckContent(deck: Deck) {
        appendLine("<div class='deck-title'>${deck.title}</div>")
        deck.cards.forEachIndexed { index, question ->
            when (question) {
                is MultipleChoiceCard -> {
                    appendLine("<div class='question'>")
                    appendLine("<div class='question-title'>Q${index + 1}: ${question.question}</div>")
                    question.choices.forEachIndexed { i, option ->
                        if (i == question.correctAnswerIndex) {
                            appendLine("<div class='option' style='color: green;'>${'A' + i}: $option (Correct)</div>")
                        } else {
                            appendLine("<div class='option'>${'A' + i}: $option</div>")
                        }
                    }
                }
            }
        }
        appendLine("</div>")
    }

    // CSS Styles
    private val bodyCss = "body { font-family: sans-serif; padding: 20px; }"
    private val deckTitleCss =
        ".deck-title { font-weight: bold; font-size: 20px; margin-bottom: 20px; }"
    private val questionTitleCss = ".question-title { font-weight: bold; margin-bottom: 10px; }"
    private val optionCss = ".option { margin-left: 20px; margin-bottom: 4px; }"
}
