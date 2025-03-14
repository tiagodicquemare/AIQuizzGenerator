package com.dicquemare.aiquizzgenerator.feature.home.domain.usecases

import com.dicquemare.aiquizzgenerator.core.domain.BaseUseCase
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.Deck
import com.dicquemare.aiquizzgenerator.feature.home.domain.repositories.HomeRepository

class GetAllDecks (private val homeRepository: HomeRepository): BaseUseCase<Unit, List<Deck>>() {
    override suspend fun execute(params: Unit): List<Deck> {
        return homeRepository.getDecks()
    }
}