package com.dicquemare.aiquizzgenerator.feature.splashscreen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
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
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SplashScreenView(navController: NavController? = null) {
    Scaffold (
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Générez vos cartes de quiz avec de l'IA",
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground),
                textAlign = TextAlign.Center,

            )
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.weight(1f))
            PrimaryButton(
                text = "Commencer",
                onClick = {
                    navController?.navigate(NavigationRoutes.homeView)
                },
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
@Preview
@Composable
fun PreviewSplashScreenView() {
    SplashScreenView()
}