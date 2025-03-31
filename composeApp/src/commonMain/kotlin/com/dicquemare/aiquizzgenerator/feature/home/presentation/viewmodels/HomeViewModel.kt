package com.dicquemare.aiquizzgenerator.feature.home.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.dicquemare.aiquizzgenerator.core.presentation.BaseViewModel
import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.viewmodels.CreateDeckSharedViewModel.CreateDeckSharedUIEvent
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.Deck
import com.dicquemare.aiquizzgenerator.feature.home.domain.usecases.GetAllDecks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

data class HomeViewModelState(
    val decks: List<Deck> = listOf()
)

class HomeViewModel(getAllDecks: GetAllDecks) :
    BaseViewModel<HomeViewModelState>(HomeViewModelState()) {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllDecks.invoke(Unit, onSuccess = {
                updateState {
                    copy(decks = it)
                }
            })
        }
    }
}