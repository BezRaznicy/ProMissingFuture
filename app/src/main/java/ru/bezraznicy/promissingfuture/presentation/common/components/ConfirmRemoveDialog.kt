package ru.bezraznicy.promissingfuture.presentation.common.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import ru.bezraznicy.promissingfuture.domain.model.Model
import ru.bezraznicy.promissingfuture.presentation.common.ModelType
import java.util.Locale

@Composable
fun ConfirmRemoveDialog(
    modelToRemove: Model,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(onDismissRequest = { onDismiss() },
        title = {
            Text(text = "Вы действительно хотите удалить " +
                    "${ModelType.identify(modelToRemove).type.toLowerCase(Locale.ROOT)} ${modelToRemove.name}?")
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirm()
            }) {
                Text(text = "Да")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text(text = "Нет")
            }
        })
}