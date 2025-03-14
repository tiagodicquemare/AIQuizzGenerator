package com.dicquemare.aiquizzgenerator.feature.home.domain.usecases

import com.dicquemare.aiquizzgenerator.core.domain.BaseUseCase
import com.dicquemare.aiquizzgenerator.feature.home.domain.repositories.HomeRepository

class DeleteDeckById (private val homeRepository: HomeRepository): BaseUseCase<String, Unit>() {
    override suspend fun execute(params: String) {
        return homeRepository.deleteDeckById(params)
    }
}