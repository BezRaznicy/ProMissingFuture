package ru.bezraznicy.promissingfuture.presentation.screen.launcher.components

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import ru.bezraznicy.promissingfuture.domain.model.Catalog
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ru.bezraznicy.promissingfuture.presentation.theme.PromissingFutureTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CatalogListItem(
    catalog: Catalog,
    dismissState: DismissState,
    onClick: () -> Unit,
    onSwipeRemove: () -> Unit,
    onSwipeShare: () -> Unit
) {
    SwipeToDismiss(
        state = dismissState,
        background = {
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    DismissValue.Default -> MaterialTheme.colorScheme.background
                    DismissValue.DismissedToEnd -> Color.Red.copy(alpha = dismissState.progress).compositeOver(MaterialTheme.colorScheme.background)
                    DismissValue.DismissedToStart -> Color.Green.copy(alpha = dismissState.progress).compositeOver(MaterialTheme.colorScheme.background)
                }
            )
            Surface(color = color, modifier = Modifier.fillMaxSize()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
                        Text(text = "Удалить")
                    }
                    Column(Modifier.weight(1f), horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Center) {
                        Text(text = "Поделиться")
                    }
                }
            }
        },
        dismissContent = {
            ListItem(headlineContent = {
                Text(
                    catalog.name,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            })
        },
        modifier = Modifier.clickable {
            onClick()
        }
    )

    LaunchedEffect(dismissState.targetValue, dismissState.progress) {
        if (dismissState.progress != 1f) return@LaunchedEffect
        when (dismissState.targetValue) {
            DismissValue.DismissedToEnd -> {
                onSwipeRemove()
            }
            DismissValue.DismissedToStart -> {
                onSwipeShare()
            }
            DismissValue.Default -> {
                // ignore
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CatalogListItemPreviewLight() {
    PromissingFutureTheme {
        CatalogListItem(catalog = Catalog("Работа"), rememberDismissState(), {}, {}, {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun CatalogListItemPreviewDark() {
    PromissingFutureTheme {
        CatalogListItem(catalog = Catalog("Работа"), rememberDismissState(), {}, {}, {})
    }
}