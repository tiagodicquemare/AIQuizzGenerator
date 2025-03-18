package com.dicquemare.aiquizzgenerator.feature.create_deck.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dicquemare.aiquizzgenerator.core.utils.QuizCardUtils
import com.dicquemare.aiquizzgenerator.core.utils.getScreenWidth
import com.dicquemare.aiquizzgenerator.feature.home.domain.models.MultipleChoiceCard

@Composable
fun MultipleChoiceCardItem(card: MultipleChoiceCard, percentageScreenWidth: Float = 0.4f) {
    val screenWidth = getScreenWidth()
    Card(
        modifier = Modifier
            .width(screenWidth * percentageScreenWidth)
            .aspectRatio(QuizCardUtils.cardRatioFrance)
            .clip(RoundedCornerShape(12.dp))
            .shadow(4.dp)
            .background(MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(top = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = card.question,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp).padding(horizontal = 12.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                card.choices.forEach { answer ->
                    Text(
                        text = answer,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                            .padding(4.dp)
                            .clip(RoundedCornerShape(6.dp))
                    )
                }
            }
        }
    }
}