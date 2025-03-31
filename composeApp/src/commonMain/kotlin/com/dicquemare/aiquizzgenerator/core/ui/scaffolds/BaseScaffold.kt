package com.dicquemare.aiquizzgenerator.core.ui.scaffolds

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BaseScaffold(
    modifier: Modifier = Modifier.padding(horizontal = 24.dp),
    columnHorizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    val insets = WindowInsets.systemBars.asPaddingValues()
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            horizontalAlignment = columnHorizontalAlignment,
            modifier = modifier.fillMaxWidth().imePadding()
                .padding(insets)
                .padding(innerPadding)
                .imePadding(),
            content = content
        )
    }
}