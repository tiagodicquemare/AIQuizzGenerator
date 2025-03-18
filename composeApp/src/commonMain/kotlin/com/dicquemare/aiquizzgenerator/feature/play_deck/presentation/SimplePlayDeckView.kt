package com.dicquemare.aiquizzgenerator.feature.play_deck.presentation

import aiquizzgenerator.composeapp.generated.resources.Res
import aiquizzgenerator.composeapp.generated.resources.ic_back_arrow
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.dicquemare.aiquizzgenerator.core.ui.buttons.PrimaryButton
import com.dicquemare.aiquizzgenerator.core.ui.scaffolds.BaseScaffold
import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.components.BackCardItem
import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.components.MultipleChoiceCardItem
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.MultipleChoiceCard
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@Composable
fun SimplePlayDeckView(
    navController: NavController? = null,
    viewModel: SimplePlayDeckViewModel = koinInject(),
    deckId: String
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val percentageScreenWidthCard = 0.8f
    LaunchedEffect(Unit) {
        viewModel.loadDeckById(deckId)
    }
    BaseScaffold(modifier = Modifier) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
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
            Spacer(modifier = Modifier.weight(1f))
            Text(
                "${uiState.cardCountLeft}/${uiState.deck?.cardsCount()}",
                style = MaterialTheme.typography.displaySmall.copy(color = MaterialTheme.colorScheme.secondary),
                modifier = Modifier.padding(end = 24.dp)
            )
        }

        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            uiState.currentSelectedCard?.let { card ->
                when (card) {
                    is MultipleChoiceCard -> {
                        MultipleChoiceCardItem(
                            card = card,
                            percentageScreenWidth = percentageScreenWidthCard
                        )
                    }
                }
            } ?: run {
                BackCardItem(percentageScreenWidth = percentageScreenWidthCard)
            }
            Spacer(modifier = Modifier.weight(1f))
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Piocher"
            ) {
                viewModel.drawCard()
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}