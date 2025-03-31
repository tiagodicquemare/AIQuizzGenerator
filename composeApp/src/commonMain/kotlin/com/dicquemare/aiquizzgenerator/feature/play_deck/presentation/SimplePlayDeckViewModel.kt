package com.dicquemare.aiquizzgenerator.feature.play_deck.presentation

import androidx.lifecycle.viewModelScope
import com.dicquemare.aiquizzgenerator.core.presentation.BaseViewModel
import com.dicquemare.aiquizzgenerator.core.presentation.UIEvent
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.Deck
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.QuizCard
import com.dicquemare.aiquizzgenerator.feature.home.domain.usecases.GetDeckById
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

data class SimplePlayDeckViewModelState(
    val deck: Deck? = null,
    val currentSelectedCard: QuizCard? = null,
    val cardCountLeft: Int = 0
)

class SimplePlayDeckViewModel(private val getDeckById: GetDeckById) :
    BaseViewModel<SimplePlayDeckViewModelState>(SimplePlayDeckViewModelState()) {
    private val cardsAlreadyDrawed = mutableListOf<QuizCard>()
    fun loadDeckById(deckId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getDeckById.invoke(deckId, onSuccess = { deck ->
                updateState { copy(deck = deck, cardCountLeft = deck.cardsCount()) }
            })
        }
    }

    fun drawCard() {
        viewModelScope.launch(Dispatchers.IO) {
            pickRandomCard()?.let { cardDrawed ->
                cardsAlreadyDrawed.add(cardDrawed)
                updateState {
                    copy(
                        currentSelectedCard = cardDrawed,
                        cardCountLeft = (deck?.cardsCount() ?: 0) - cardsAlreadyDrawed.size
                    )
                }
            }
        }
    }

    private fun pickRandomCard(): QuizCard? {
        uiState.value.deck?.let { deck ->
            if (deck.cards.size > cardsAlreadyDrawed.size) {
                val cardsLeft = deck.cards - cardsAlreadyDrawed.toList().toSet()
                return cardsLeft.random()
            }
        }
        return null
    }
}