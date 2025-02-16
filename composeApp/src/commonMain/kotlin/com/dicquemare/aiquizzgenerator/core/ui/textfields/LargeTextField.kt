package com.dicquemare.aiquizzgenerator.core.ui.textfields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PreviewLargeTextField() {
    LargeTextField(textState = remember { mutableStateOf("") }, hint = "Votre sujet")
}

@Composable
fun LargeTextField(textState: MutableState<String>, hint: String = "") {
    TextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        placeholder = {
            Text(hint)
        },
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
        textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
        maxLines = 3
    )
}