package com.dicquemare.aiquizzgenerator.feature.create_deck.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.dicquemare.aiquizzgenerator.NavigationRoutes
import com.dicquemare.aiquizzgenerator.core.ui.buttons.PrimaryButton
import com.dicquemare.aiquizzgenerator.core.ui.dropdowns.PrimaryDropDown
import com.dicquemare.aiquizzgenerator.core.ui.scaffolds.BaseScaffold
import com.dicquemare.aiquizzgenerator.core.ui.textfields.LargeTextField
import com.dicquemare.aiquizzgenerator.feature.create_deck.domain.models.CardType
import com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.viewmodels.CreateDeckSharedViewModel
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.CardDifficulty
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.CardTheme
import org.koin.compose.koinInject

@Composable
fun ChooseDeckOptionsView(
    navController: NavController? = null,
    sharedViewModel: CreateDeckSharedViewModel = koinInject()
) {
    val uiState by sharedViewModel.uiState.collectAsStateWithLifecycle()

    BaseScaffold {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Sujet",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            textAlign = TextAlign.Center,
        )
        LargeTextField(uiState.creationDeckOptions.subject ?: "", "Votre sujet") {
            sharedViewModel.updateSubject(it)
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Thème",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            textAlign = TextAlign.Center,
        )
        PrimaryDropDown(
            hint = "Votre thème",
            items = CardTheme.entries.associateBy { it.name },
            itemSelectedName = uiState.creationDeckOptions.theme?.name ?: "",
            onItemSelected = { sharedViewModel.updateTheme(it) }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Style de question",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            textAlign = TextAlign.Center,
        )
        PrimaryDropDown(
            hint = "Quels types de questions ?",
            items = CardType.entries.associateBy { it.name },
            itemSelectedName = uiState.creationDeckOptions.type?.name ?: "",
            onItemSelected = { sharedViewModel.updateType(it) }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Niveau",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            textAlign = TextAlign.Center,
        )
        PrimaryDropDown(
            hint = "Quel niveau de difficulté ?",
            items = CardDifficulty.entries.associateBy { it.name },
            itemSelectedName = uiState.creationDeckOptions.difficulty?.name ?: "",
            onItemSelected = { sharedViewModel.updateDifficulty(it) }
        )
        Spacer(modifier = Modifier.height(32.dp))
        PrimaryButton(text = "Créer le quiz") {
            if (uiState.creationDeckOptions.subject != null && uiState.creationDeckOptions.theme != null && uiState.creationDeckOptions.type != null && uiState.creationDeckOptions.difficulty != null) {
                navController?.navigate(NavigationRoutes.DeckCreationLoadingViewRoute)
            }
        }
    }
}