package com.dicquemare.aiquizzgenerator.feature.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dicquemare.aiquizzgenerator.NavigationRoutes
import com.dicquemare.aiquizzgenerator.core.ui.buttons.PrimaryButton
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.Deck
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeView(navController: NavController? = null) {
    val lazyListState = rememberLazyListState()
    val mockDeck = listOf<Deck>(
        Deck(id = "1", title = "Deck 1", cards = emptyList()),
        Deck(id = "2", title = "Deck 2", cards = emptyList()),
        Deck(id = "3", title = "Deck 3", cards = emptyList()),
    )
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            LazyColumn(
                horizontalAlignment = Alignment.Start,
                state = lazyListState
            ) {
                items(mockDeck.count()) { index ->
                    val deck = mockDeck[index]
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth().height(108.dp)
                            .background(
                                color = MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(16.dp)
                            ), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            deck.title,
                            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    PrimaryButton(text = "Créer un deck") {
                        navController?.navigate(NavigationRoutes.ChooseDeckSubjectViewRoute)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeView() {
    HomeView()
}