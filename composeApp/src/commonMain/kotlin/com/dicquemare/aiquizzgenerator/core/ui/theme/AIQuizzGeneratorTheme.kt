package com.dicquemare.aiquizzgenerator.core.ui.theme

import androidx.compose.runtime.Composable

@Composable
expect fun AIQuizzGeneratorTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)