package com.dicquemare.aiquizzgenerator.feature.create_deck.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.dicquemare.aiquizzgenerator.core.ui.scaffolds.BaseScaffold
import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.components.MultipleChoiceCardItem
import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.viewmodels.VisualiseDeckViewModel
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.MultipleChoiceCard
import org.koin.compose.koinInject

@Composable
fun VisualiseCreatedDeckView(
    navController: NavController? = null,
    viewModel: VisualiseDeckViewModel = koinInject(),
    deckId: String
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadDeckById(deckId)
    }
    BaseScaffold {
        Row {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(
                        MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Text(
                    uiState.deck?.title ?: "No deck found",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        uiState.deck?.cards?.let { cards ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(cards.size) { index ->
                    val card = cards[index]
                    when(card) {
                        is MultipleChoiceCard -> {
                            MultipleChoiceCardItem(card)
                        }
                    }
                }
            }
        }
    }
}