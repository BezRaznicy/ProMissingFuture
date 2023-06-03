package ru.bezraznicy.promissingfuture.presentation.screen.models.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
fun ModelItem(
    dismissState: DismissState,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    onSwipeRemove: () -> Unit,
    onSwipeShare: () -> Unit,
    listItem: @Composable RowScope.() -> Unit,
    ) {
    SwipeToDismiss(
        state = dismissState,
        background = {
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    DismissValue.Default -> MaterialTheme.colorScheme.background
                    DismissValue.DismissedToEnd -> Color.Red.copy(alpha = dismissState.progress).compositeOver(
                        MaterialTheme.colorScheme.background)
                    DismissValue.DismissedToStart -> Color.Green.copy(alpha = dismissState.progress).compositeOver(
                        MaterialTheme.colorScheme.background)
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
        dismissContent = listItem,
        modifier = Modifier.combinedClickable(onClick = onClick, onLongClick = onLongClick)
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