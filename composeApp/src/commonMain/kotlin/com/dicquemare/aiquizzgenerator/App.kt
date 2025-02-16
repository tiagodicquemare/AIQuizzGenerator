package com.dicquemare.aiquizzgenerator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import aiquizzgenerator.composeapp.generated.resources.Res
import aiquizzgenerator.composeapp.generated.resources.compose_multiplatform
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dicquemare.aiquizzgenerator.core.ui.theme.AIQuizzGeneratorTheme
import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.ChooseDeckOptionsView
import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.ChooseDeckSubjectView
import com.dicquemare.aiquizzgenerator.feature.home.presentation.HomeView
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
            startDestination = NavigationRoutes.chooseDeckSubjectView
        ) {
            composable(NavigationRoutes.splashScreenView) {
                SplashScreenView(navController = navController)
            }
            composable(NavigationRoutes.homeView) {
                HomeView(navController = navController)
            }
            composable(NavigationRoutes.chooseDeckSubjectView) {
                ChooseDeckSubjectView(navController = navController)
            }
            composable(NavigationRoutes.chooseDeckOptionsView) {
                ChooseDeckOptionsView(navController = navController)
            }
        }
    }
}