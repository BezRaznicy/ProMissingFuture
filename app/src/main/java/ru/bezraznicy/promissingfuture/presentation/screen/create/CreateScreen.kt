package ru.bezraznicy.promissingfuture.presentation.screen.create

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.bezraznicy.promissingfuture.domain.model.Event
import ru.bezraznicy.promissingfuture.presentation.common.ModelType
import ru.bezraznicy.promissingfuture.presentation.common.components.LoadingDialog
import ru.bezraznicy.promissingfuture.presentation.navigation.Screen
import ru.bezraznicy.promissingfuture.presentation.screen.create.components.FormTextField
import ru.bezraznicy.promissingfuture.presentation.notification.scheduleEventNotification
import ru.bezraznicy.promissingfuture.presentation.screen.create.components.TimeNotificationField
import ru.bezraznicy.promissingfuture.presentation.screen.create.vm.CreateEvent
import ru.bezraznicy.promissingfuture.presentation.screen.create.vm.CreateState
import ru.bezraznicy.promissingfuture.presentation.theme.PromissingFutureTheme

@Composable
fun CreateScreen(
    navController: NavController = rememberNavController(),
    state: CreateState = CreateState(ModelType.EVENT),
    onEvent: (CreateEvent) -> Unit = {}
) {
    var name by remember { mutableStateOf(state.modelBuilder.name) }
    var description by remember { mutableStateOf(state.modelBuilder.description) }
    var time by remember { mutableStateOf(state.modelBuilder.time) }

    if (state.loading) {
        LoadingDialog(title = "Вставляем данные...")
    }
    if (state.navigationDestination != null) {
        val context = LocalContext.current
        LaunchedEffect(key1 = Unit) {
            if (state.finalModel is Event) {
                // Нельзя во view model, так как использует context :(
                scheduleEventNotification(event = state.finalModel, context = context)
            }
            navController.navigate(Screen.Launcher.route)
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp)
        ) {
            IconButton(onClick = { onEvent(CreateEvent.SaveModel(name, description, time)) }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
            Text(text = state.modelToCreate.type, style = MaterialTheme.typography.headlineLarge)
            IconButton(onClick = {
                onEvent(CreateEvent.SaveModel(name, description, time))
            }) {
                Icon(Icons.Filled.Save, contentDescription = null)
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp)
        ) {
            FormTextField(
                value = name,
                onValueChange = { name = it },
                title = "Название",
                singleLine = false,
                modifier = Modifier.fillMaxWidth()
            )
            if (state.modelToCreate == ModelType.KNOWLEDGE) {
                FormTextField(
                    value = description,
                    onValueChange = { description = it },
                    title = "Описание",
                    minLines = 5,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if (state.modelToCreate == ModelType.EVENT) {
                FormTextField(
                    value = description,
                    onValueChange = { description = it },
                    title = "Описание",
                    maxLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )
                // need to notify
                var needToNotify by remember { mutableStateOf(false) }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 18.dp)
                ) {
                    Checkbox(
                        checked = needToNotify,
                        onCheckedChange = { needToNotify = it }
                    )
                    Text(
                        text = "Нужно уведомление",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                if (needToNotify) {
                    TimeNotificationField(
                        { time = it }
                    )
                }
            }
        }
    }
    LaunchedEffect(key1 = state.navigationDestination) {
        if (state.navigationDestination != null) {
            navController.navigate(state.navigationDestination.route)
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