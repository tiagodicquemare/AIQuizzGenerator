package com.dicquemare.aiquizzgenerator.core.ui.textfields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PreviewLargeTextFieldWithButton() {
    LargeTextFieldWithButton(hint = "Votre sujet", onSendButtonClicked = {})
}

@Composable
fun LargeTextFieldWithButton(
    text: String = "",
    hint: String = "",
    onChanged: (String) -> Unit = {},
    onSendButtonClicked: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LargeTextField(
            text = text,
            hint = hint,
        ) {
            onChanged(it)
        }
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    onSendButtonClicked(text)
                },
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = "Envoyer")
            }
        }
    }
}