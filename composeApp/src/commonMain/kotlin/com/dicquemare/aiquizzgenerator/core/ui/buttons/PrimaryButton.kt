package com.dicquemare.aiquizzgenerator.core.ui.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import org.jetbrains.compose.ui.tooling.preview.Preview


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview()
@Composable
fun PreviewPrimaryButton() {
    PrimaryButton(text = "Primary Button", onClick = {})
}

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    enabled: Boolean = true,
    color: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    Box(modifier = modifier) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(12.dp),
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(color),
            modifier = Modifier
                .heightIn(min = 54.dp).fillMaxWidth()
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center
            )
        }
    }
}