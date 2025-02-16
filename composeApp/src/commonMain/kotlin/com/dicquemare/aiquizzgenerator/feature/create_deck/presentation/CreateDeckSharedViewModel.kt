package com.dicquemare.aiquizzgenerator.feature.create_deck.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CreateDeckSharedViewModel: ViewModel() {
    val subject = mutableStateOf("")

    fun updateSubject(newSubject: String) {
        subject.value = newSubject
    }
}