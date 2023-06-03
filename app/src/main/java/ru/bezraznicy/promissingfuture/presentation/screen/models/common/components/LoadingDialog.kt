package ru.bezraznicy.promissingfuture.presentation.screen.models.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoadingDialog(
    title: String,
    openDialog: MutableState<Boolean> = mutableStateOf(true)
) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = title)
            },
            text = {
                Box(modifier = Modifier.fillMaxWidth(1f)) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            },
            confirmButton = {},
            dismissButton = {}
        )
    }
}