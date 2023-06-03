package ru.bezraznicy.promissingfuture.presentation.screen.models.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import ru.bezraznicy.promissingfuture.domain.model.Knowledge
import ru.bezraznicy.promissingfuture.presentation.theme.PromissingFutureTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KnowledgeItem(
    knowledge: Knowledge,
    dismissState: DismissState = rememberDismissState(),
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
    onSwipeRemove: () -> Unit = {},
    onSwipeShare: () -> Unit = {}
) {
    ModelItem(
        dismissState = dismissState,
        onClick = onClick,
        onLongClick = onLongClick,
        onSwipeRemove = onSwipeRemove,
        onSwipeShare = onSwipeShare) {
        ListItem(
            headlineContent = {
                Text(text = knowledge.name, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            },
            supportingContent = {
                knowledge.description?.let { Text(text = it, maxLines = 2, overflow = TextOverflow.Ellipsis) }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun KnowledgeItemPreviewLight() {
    PromissingFutureTheme {
        KnowledgeItem(
            knowledge = Knowledge(
                "Название",
                "Lorem ipsum de falso nor astranda Lorem ipsum de falso nor astranda Lorem ipsum de falso nor astranda Lorem ipsum de falso nor astranda"
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun KnowledgeItemPreviewDark() {
    PromissingFutureTheme {
        KnowledgeItem(
            knowledge = Knowledge(
                "Название",
                "Lorem ipsum de falso nor astranda Lorem ipsum de falso nor astranda Lorem ipsum de falso nor astranda Lorem ipsum de falso nor astranda"
            )
        )
    }
}