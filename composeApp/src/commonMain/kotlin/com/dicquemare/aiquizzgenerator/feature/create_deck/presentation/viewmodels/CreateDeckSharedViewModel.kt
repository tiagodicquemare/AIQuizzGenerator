package com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.dicquemare.aiquizzgenerator.core.presentation.BaseViewModel
import com.dicquemare.aiquizzgenerator.core.presentation.UIEvent
import com.dicquemare.aiquizzgenerator.core.utils.KMMLogger
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.models.CardType
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.models.DeckCreationOptions
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.usecases.CreateDeckWithLLM
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.usecases.GetCurrentCreationDeckOptions
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.usecases.SaveCreationDeckOptions
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.usecases.SaveCreationDeckOptionsParams
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.CardDifficulty
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.CardTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

data class CreateDeckSharedViewModelState(
    val creationDeckOptions: DeckCreationOptions = DeckCreationOptions(),
)

class CreateDeckSharedViewModel(
    private val getCurrentCreationDeckOptions: GetCurrentCreationDeckOptions,
    private val saveCreationDeckOptions: SaveCreationDeckOptions,
    private val createDeckWithLLM: CreateDeckWithLLM
) : BaseViewModel<CreateDeckSharedViewModelState>(CreateDeckSharedViewModelState()) {

    sealed class CreateDeckSharedUIEvent : UIEvent() {
        data class NavigateToVisualiseDeckCreation(val deckId: String) : UIEvent()
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentCreationDeckOptions.invoke(Unit, onSuccess = {
                updateState { copy(creationDeckOptions = it) }
            })
        }
    }

    private suspend fun updateCreationDeckOptions(params: SaveCreationDeckOptionsParams) {
        saveCreationDeckOptions.invoke(params, onSuccess = {
            updateState { copy(creationDeckOptions = it) }
        })
    }

    fun updateSubject(newSubject: String) {
        viewModelScope.launch(Dispatchers.IO) {
            updateCreationDeckOptions(SaveCreationDeckOptionsParams(subject = newSubject))
        }
    }

    fun updateTheme(theme: CardTheme) {
        viewModelScope.launch(Dispatchers.IO) {
            updateCreationDeckOptions(SaveCreationDeckOptionsParams(theme = theme))
        }
    }

    fun updateDifficulty(difficulty: CardDifficulty) {
        viewModelScope.launch(Dispatchers.IO) {
            updateCreationDeckOptions(SaveCreationDeckOptionsParams(difficulty = difficulty))
        }
    }

    fun updateType(type: CardType) {
        viewModelScope.launch(Dispatchers.IO) {
            updateCreationDeckOptions(SaveCreationDeckOptionsParams(type = type))
        }
    }

    fun launchDeckCreation() {
        viewModelScope.launch(Dispatchers.IO) {
            createDeckWithLLM.invoke(Unit, onSuccess = { deck ->
                _uiEvent.emit(CreateDeckSharedUIEvent.NavigateToVisualiseDeckCreation(deck.id))
            }, onFailure = {

            })
        }
    }
}