package com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.dicquemare.aiquizzgenerator.core.presentation.BaseViewModel
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.Deck
import com.dicquemare.aiquizzgenerator.feature.home.domain.usecases.DeleteDeckById
import com.dicquemare.aiquizzgenerator.feature.home.domain.usecases.GetDeckById
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

data class VisualiseDeckViewModelState(
    val deck: Deck? = null
)

class VisualiseDeckViewModel(
    private val getDeckById: GetDeckById,
    private val deleteDeckById: DeleteDeckById
) : BaseViewModel<VisualiseDeckViewModelState>(VisualiseDeckViewModelState()) {


    fun loadDeckById(deckId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getDeckById.execute(deckId)?.let { deck ->
                updateState { copy(deck = deck) }
            }
        }
    }
}