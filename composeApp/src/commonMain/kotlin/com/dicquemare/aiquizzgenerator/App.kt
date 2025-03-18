package com.dicquemare.aiquizzgenerator

import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.dicquemare.aiquizzgenerator.core.ui.theme.AIQuizzGeneratorTheme
import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.ChooseDeckOptionsView
import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.ChooseDeckSubjectView
import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.DeckCreationLoadingView
import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.VisualiseCreatedDeckView
import com.dicquemare.aiquizzgenerator.feature.home.presentation.DeckDetailsView
import com.dicquemare.aiquizzgenerator.feature.home.presentation.HomeView
import com.dicquemare.aiquizzgenerator.feature.play_deck.presentation.SimplePlayDeckView
import com.dicquemare.aiquizzgenerator.feature.splashscreen.presentation.SplashScreenView

@Composable
@Preview
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    AIQuizzGeneratorTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        val navController: NavHostController = rememberNavController()
        NavHost(
            navController,
            startDestination = NavigationRoutes.SplashScreenViewRoute
        ) {
            composable<NavigationRoutes.SplashScreenViewRoute> {
                SplashScreenView(navController = navController)
            }
            composable<NavigationRoutes.HomeViewRoute> {
                HomeView(navController = navController)
            }
            composable<NavigationRoutes.ChooseDeckSubjectViewRoute> {
                ChooseDeckSubjectView(navController = navController)
            }
            composable<NavigationRoutes.ChooseDeckOptionsViewRoute> {
                ChooseDeckOptionsView(navController = navController)
            }
            composable<NavigationRoutes.DeckCreationLoadingViewRoute> {
                DeckCreationLoadingView(navController = navController)
            }
            composable<NavigationRoutes.VisualiseDeckCreationViewRoute> {
                val args = it.toRoute<NavigationRoutes.VisualiseDeckCreationViewRoute>()
                VisualiseCreatedDeckView(navController = navController, deckId = args.deckId)
            }
            composable<NavigationRoutes.DeckDetailsViewRoute> {
                val args = it.toRoute<NavigationRoutes.DeckDetailsViewRoute>()
                DeckDetailsView(navController = navController, deckId = args.deckId)
            }
            composable<NavigationRoutes.SimplePlayDeckViewRoute> {
                val args = it.toRoute<NavigationRoutes.SimplePlayDeckViewRoute>()
                SimplePlayDeckView(navController = navController, deckId = args.deckId)
            }
        }
    }
}