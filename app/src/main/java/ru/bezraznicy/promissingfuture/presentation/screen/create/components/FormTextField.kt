package ru.bezraznicy.promissingfuture.presentation.screen.create.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    title: String = "",
    hint: String = title,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = maxLines,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = title)
        },
        minLines = minLines,
        singleLine = singleLine,
        maxLines = maxLines,
        placeholder = {
            Text(text = hint)
        },
        modifier = modifier
    )
}