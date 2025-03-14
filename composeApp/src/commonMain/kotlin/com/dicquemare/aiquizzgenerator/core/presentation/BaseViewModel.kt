package com.dicquemare.aiquizzgenerator.core.presentation

import kotlinx.coroutines.flow.MutableSharedFlow
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class UIEvent

abstract class BaseViewModel<X : Any>(initialState: X) : ViewModel() {
    protected val _uiEvent = MutableSharedFlow<UIEvent>()
    val uiEvent: SharedFlow<UIEvent> = _uiEvent.asSharedFlow()

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<X> = _uiState.asStateFlow()

    protected suspend fun updateState(update: suspend X.() -> X) {
        _uiState.value = _uiState.value.update()
    }
}
