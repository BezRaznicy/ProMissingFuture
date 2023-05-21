package ru.bezraznicy.promissingfuture.presentation.screen.models.components

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
    ModelItem(
        dismissState = dismissState,
        onClick = onClick,
        onSwipeRemove = onSwipeRemove,
        onSwipeShare = onSwipeShare
    ) {
        ListItem(headlineContent = {
            Text(
                catalog.name,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CatalogListItemPreviewLight() {
    PromissingFutureTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            CatalogListItem(catalog = Catalog("Работа"), rememberDismissState(), {}, {}, {})
        }
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