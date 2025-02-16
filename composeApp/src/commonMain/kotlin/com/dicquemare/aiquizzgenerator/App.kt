package com.dicquemare.aiquizzgenerator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import aiquizzgenerator.composeapp.generated.resources.Res
import aiquizzgenerator.composeapp.generated.resources.compose_multiplatform
import com.dicquemare.aiquizzgenerator.core.ui.theme.AIQuizzGeneratorTheme

@Composable
@Preview
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    AIQuizzGeneratorTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        var visible by remember { mutableStateOf(true) }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(visible = visible) {
                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = "Logo"
                )
            }
            Button(onClick = { visible = !visible }) {
                Text("Toggle visibility")
            }
        }
    }
}