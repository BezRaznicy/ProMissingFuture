package ru.bezraznicy.promissingfuture.presentation.screen.models.common.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import ru.bezraznicy.promissingfuture.presentation.screen.models.common.ModelType
import java.util.Locale

@Composable
fun AddNewDialog(
    modelType: ModelType,
    openDialog: MutableState<Boolean> = mutableStateOf(true),
    wantToCreate: () -> Unit,
    wantToImport: () -> Unit,
    onClose: () -> Unit
) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
                onClose()
            },
            title = {
                Text(text = "Вы хотите создать или добавить ${modelType.type.lowercase(Locale.getDefault())}?")
            },
            confirmButton = {
                TextButton(onClick = wantToImport) {
                    Text(text = "Добавить через QR-code")
                }
            },
            dismissButton = {
                TextButton(onClick = wantToCreate) {
                    Text(text = "Создать")
                }
            }
        )
    }
}