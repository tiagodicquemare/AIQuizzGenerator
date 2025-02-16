package com.dicquemare.aiquizzgenerator.feature.create_deck.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dicquemare.aiquizzgenerator.core.ui.scaffolds.BaseScaffold
import com.dicquemare.aiquizzgenerator.core.ui.textfields.LargeTextField
import org.koin.compose.koinInject

@Composable
fun ChooseDeckOptionsView(
    navController: NavController? = null,
    sharedViewModel: CreateDeckSharedViewModel = koinInject()
) {
    val textState = sharedViewModel.subject
    BaseScaffold {
        Spacer(modifier = Modifier.height(32.dp))
        LargeTextField(textState, "Votre sujet")
    }
}