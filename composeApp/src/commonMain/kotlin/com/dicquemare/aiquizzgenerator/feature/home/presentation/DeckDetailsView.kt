package com.dicquemare.aiquizzgenerator.feature.home.presentation

import aiquizzgenerator.composeapp.generated.resources.Res
import aiquizzgenerator.composeapp.generated.resources.ic_arrow_down
import aiquizzgenerator.composeapp.generated.resources.ic_back_arrow
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.dicquemare.aiquizzgenerator.NavigationRoutes
import com.dicquemare.aiquizzgenerator.core.ui.buttons.PrimaryButton
import com.dicquemare.aiquizzgenerator.core.ui.scaffolds.BaseScaffold
import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.components.MultipleChoiceCardItem
import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.viewmodels.VisualiseDeckViewModel
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.MultipleChoiceCard
import com.dicquemare.aiquizzgenerator.feature.home.presentation.viewmodels.DeckDetailsViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
fun DeckDetailsView(
    navController: NavController? = null,
    viewModel: DeckDetailsViewModel = koinInject(),
    deckId: String
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadDeckById(deckId)

        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is VisualiseDeckViewModel.VisualiseDeckEvent.DeckDeleted -> {
                    navController?.navigate(NavigationRoutes.HomeViewRoute)
                }
            }
        }
    }
    BaseScaffold(modifier = Modifier) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_back_arrow),
                contentDescription = "Back Arrow",
                modifier = Modifier
                    .clickable {
                        navController?.popBackStack()
                    }.padding(vertical = 16.dp).padding(end = 24.dp, start = 24.dp)
            )
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Text(
                    uiState.deck?.title ?: "No deck found",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.align(Alignment.Center).padding(horizontal = 40.dp)
                )
            }
            Spacer(modifier = Modifier.width(72.dp))
        }
        uiState.deck?.cards?.let { cards ->
            Box() {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize().padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    item {
                        Spacer(modifier = Modifier.height(0.dp))
                    }
                    items(cards.size) { index ->
                        val card = cards[index]
                        when (card) {
                            is MultipleChoiceCard -> {
                                MultipleChoiceCardItem(card)
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(152.dp))
                    }
                }
                Column(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)) {
                    PrimaryButton(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp), text = "Jouer") {
                        navController?.navigate(NavigationRoutes.SimplePlayDeckViewRoute(deckId))
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDeckDetailsView() {
    DeckDetailsView(deckId = "1")
}