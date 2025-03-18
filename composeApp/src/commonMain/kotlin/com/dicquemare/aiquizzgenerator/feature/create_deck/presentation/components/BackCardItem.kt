package com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.dicquemare.aiquizzgenerator.core.utils.QuizCardUtils
import com.dicquemare.aiquizzgenerator.core.utils.getScreenWidth

@Composable
fun BackCardItem(
    percentageScreenWidth: Float = 0.4f,
    cardRatio: Float = QuizCardUtils.cardRatioFrance
) {
    val screenWidth = getScreenWidth()
    Card(
        modifier = Modifier
            .width(screenWidth * percentageScreenWidth)
            .aspectRatio(cardRatio)
            .clip(RoundedCornerShape(12.dp))
            .shadow(4.dp)
            .background(MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                "Provide back card background", style = MaterialTheme.typography.titleLarge.copy(
                    color =
                    MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}