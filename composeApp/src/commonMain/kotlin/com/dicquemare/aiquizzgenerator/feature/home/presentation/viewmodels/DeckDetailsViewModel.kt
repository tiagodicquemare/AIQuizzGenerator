package com.dicquemare.aiquizzgenerator.feature.home.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.dicquemare.aiquizzgenerator.core.presentation.BaseViewModel
import com.dicquemare.aiquizzgenerator.core.presentation.UIEvent
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.Deck
import com.dicquemare.aiquizzgenerator.feature.home.domain.usecases.DeleteDeckById
import com.dicquemare.aiquizzgenerator.feature.home.domain.usecases.GetDeckById
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

data class DeckDetailsViewModelState(
    val deck: Deck? = null
)

class DeckDetailsViewModel(
    private val getDeckById: GetDeckById,
    private val deleteDeckById: DeleteDeckById
) : BaseViewModel<DeckDetailsViewModelState>(DeckDetailsViewModelState()) {
    sealed class VisualiseDeckEvent : UIEvent() {
        data object DeckDeleted : VisualiseDeckEvent()
    }


    fun loadDeckById(deckId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getDeckById.execute(deckId)?.let { deck ->
                updateState { copy(deck = deck) }
            }
        }
    }

    fun deleteDeck() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteDeckById.execute(uiState.value.deck?.id ?: return@launch)
            _uiEvent.emit(VisualiseDeckEvent.DeckDeleted)
        }
    }
}