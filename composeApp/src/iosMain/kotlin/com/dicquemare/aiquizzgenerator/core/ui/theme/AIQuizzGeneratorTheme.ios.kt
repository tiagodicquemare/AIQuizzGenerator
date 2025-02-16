package com.dicquemare.aiquizzgenerator.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme

@Composable
actual fun AIQuizzGeneratorTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkTheme) darkScheme else lightScheme,
        typography = AIQuizzGeneratorTypography(),
        content = content
    )
}