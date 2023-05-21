package ru.bezraznicy.promissingfuture.presentation.screen.create

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.bezraznicy.promissingfuture.presentation.common.ModelType
import ru.bezraznicy.promissingfuture.presentation.common.components.LoadingDialog
import ru.bezraznicy.promissingfuture.presentation.screen.create.vm.CreateEvent
import ru.bezraznicy.promissingfuture.presentation.screen.create.vm.CreateState
import ru.bezraznicy.promissingfuture.presentation.theme.PromissingFutureTheme

@Composable
fun CreateScreen(
    state: CreateState = CreateState(ModelType.CATALOG),
    onEvent: (CreateEvent) -> Unit = {}
) {
    var name by remember { mutableStateOf(state.modelBuilder.name) }
    var description by remember { mutableStateOf(state.modelBuilder.description) }
    var time by remember { mutableStateOf(state.modelBuilder.time) }

    if (state.loading) {
        LoadingDialog(title = "Вставляем данные...")
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(0.93f)
        ) {
            Text(text = state.modelToCreate.type, style = MaterialTheme.typography.headlineLarge)
            IconButton(onClick = { onEvent(CreateEvent.SaveModel(name, description, time)) }) {
                Icon(Icons.Filled.Save, contentDescription = null)
            }
        }
        TextField(
            value = name,
            onValueChange = { name = it }
        )
        if (state.modelToCreate == ModelType.KNOWLEDGE || state.modelToCreate == ModelType.EVENT) {
            TextField(
                value = description,
                onValueChange = { description = it }
            )
        }
        if (state.modelToCreate == ModelType.EVENT) {
            TextField(
                value = time,
                onValueChange = { time = it }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CreatePreviewLightTheme() {
    PromissingFutureTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            CreateScreen()
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
fun CreatePreviewDarkTheme() {
    PromissingFutureTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            CreateScreen()
        }
    }
}