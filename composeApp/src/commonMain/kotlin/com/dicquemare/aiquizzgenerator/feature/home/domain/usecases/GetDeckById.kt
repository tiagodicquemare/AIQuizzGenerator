package com.dicquemare.aiquizzgenerator.feature.home.domain.usecases

import com.dicquemare.aiquizzgenerator.core.domain.BaseUseCase
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.Deck
import com.dicquemare.aiquizzgenerator.feature.home.domain.repositories.HomeRepository

class GetDeckById(private val homeRepository: HomeRepository) : BaseUseCase<String, Deck>() {
    override suspend fun execute(params: String): Deck {
        return homeRepository.getDeckById(params)
    }
}