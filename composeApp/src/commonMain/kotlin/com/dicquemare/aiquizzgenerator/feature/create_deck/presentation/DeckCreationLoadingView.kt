package com.dicquemare.aiquizzgenerator.feature.create_deck.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.dicquemare.aiquizzgenerator.NavigationRoutes
import com.dicquemare.aiquizzgenerator.core.ui.scaffolds.BaseScaffold
import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.viewmodels.CreateDeckSharedViewModel
import org.koin.compose.koinInject

@Composable
fun DeckCreationLoadingView(
    navController: NavController? = null,
    sharedViewModel: CreateDeckSharedViewModel = koinInject()
) {
    val uiState by sharedViewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        sharedViewModel.launchDeckCreation()
        sharedViewModel.uiEvent.collect() { event ->
            when (event) {
                is CreateDeckSharedViewModel.CreateDeckSharedUIEvent.NavigateToVisualiseDeckCreation -> {
                    navController?.navigate(NavigationRoutes.VisualiseDeckCreationViewRoute(event.deckId))
                }
            }
        }
    }
    BaseScaffold {
        Spacer(modifier = Modifier.weight(1f))
        Text("Requête au serveur...", style = MaterialTheme.typography.headlineMedium.copy(
            color = MaterialTheme.colorScheme.onBackground
        ))
        Spacer(modifier = Modifier.height(16.dp))
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}