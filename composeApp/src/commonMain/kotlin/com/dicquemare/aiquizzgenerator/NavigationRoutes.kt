package com.dicquemare.aiquizzgenerator

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationRoutes() {
    @Serializable
    data object HomeViewRoute : NavigationRoutes()
    @Serializable
    data object SplashScreenViewRoute : NavigationRoutes()
    @Serializable
    data object ChooseDeckSubjectViewRoute : NavigationRoutes()
    @Serializable
    data object ChooseDeckOptionsViewRoute : NavigationRoutes()
    @Serializable
    data object DeckCreationLoadingViewRoute : NavigationRoutes()
    @Serializable
    data class VisualiseDeckCreationViewRoute(val deckId: String) : NavigationRoutes()
}