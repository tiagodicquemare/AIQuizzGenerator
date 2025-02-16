package com.dicquemare.aiquizzgenerator.feature.create_deck.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dicquemare.aiquizzgenerator.NavigationRoutes
import com.dicquemare.aiquizzgenerator.core.ui.scaffolds.BaseScaffold
import com.dicquemare.aiquizzgenerator.core.ui.textfields.LargeTextFieldWithButton
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
fun ChooseDeckSubjectView(
    navController: NavController? = null,
    sharedViewModel: CreateDeckSharedViewModel = koinInject()
) {
    BaseScaffold(content = {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Vous voulez faire un quiz sur quel sujet ?",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(32.dp))
        LargeTextFieldWithButton(hint = "Votre sujet", onSendButtonClicked = { text ->
            if (text.isNotBlank()) {
                sharedViewModel.updateSubject(text)
                navController?.navigate(NavigationRoutes.chooseDeckOptionsView)
            }
        })
        Spacer(modifier = Modifier.weight(1f))
    })
}

@Preview
@Composable
fun PreviewChooseDeckSubjectView() {
    ChooseDeckSubjectView()
}