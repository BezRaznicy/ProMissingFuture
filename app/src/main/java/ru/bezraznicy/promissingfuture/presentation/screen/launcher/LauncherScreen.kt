package ru.bezraznicy.promissingfuture.presentation.screen.launcher

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.presentation.screen.launcher.components.CatalogListItem
import ru.bezraznicy.promissingfuture.presentation.theme.PromissingFutureTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LauncherScreen(
    launcherState: LauncherState,
    onEvent: (LauncherEvent) -> Unit
) {
    // True will not do anything
    // False will change dismiss state of the item
    var isConfirmed by remember { mutableStateOf(true) }

    if (launcherState.catalogToRemove != null) {
        AlertDialog(onDismissRequest = { onEvent(LauncherEvent.Start) },
            title = {
                isConfirmed = false
                Text(text = "Вы действительно хотите удалить каталог ${launcherState.catalogToRemove.name}?")
            },
            confirmButton = {
                TextButton(onClick = {
                    onEvent(LauncherEvent.RemoveCatalog(launcherState.catalogToRemove))
                }) {
                    Text(text = "Да")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onEvent(LauncherEvent.Start)
                }) {
                    Text(text = "Нет")
                }
            })
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(0.93f)
        ) {
            Text(text = "Каталоги", style = MaterialTheme.typography.headlineLarge)
            IconButton(onClick = { onEvent(LauncherEvent.AddCatalog) }) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }

        LazyColumn {
            items(items = launcherState.catalogs) { catalog ->
                key(catalog.id) {
                    val dismissState = rememberDismissState()
                    LaunchedEffect(key1 = isConfirmed) {
                        if (!isConfirmed) {
                            dismissState.snapTo(DismissValue.Default)
                            isConfirmed = true
                        }
                    }
                    CatalogListItem(
                        catalog = catalog,
                        dismissState = dismissState,
                        onClick = { onEvent(LauncherEvent.SelectCatalog(catalog)) },
                        onSwipeRemove = { onEvent(LauncherEvent.ConfirmRemoveCatalog(catalog)) },
                        onSwipeShare = { onEvent(LauncherEvent.ShareCatalog(catalog)) }
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LauncherPreviewLightTheme() {
    PromissingFutureTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            LauncherScreen(
                LauncherState(
                    listOf(
                        Catalog(1L, "Любовь"),
                        Catalog(2L, "Учёба"),
                        Catalog(3L, "Работа"),
                        Catalog(4L, "Хобби"),
                    )
                )
            ) {}
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
fun LauncherPreviewDarkTheme() {
    PromissingFutureTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            LauncherScreen(
                LauncherState(
                    listOf(
                        Catalog(1L, "Любовь"),
                        Catalog(2L, "Учёба"),
                        Catalog(3L, "Работа"),
                        Catalog(4L, "Хобби"),
                    )
                )
            ) {}
        }
    }
}