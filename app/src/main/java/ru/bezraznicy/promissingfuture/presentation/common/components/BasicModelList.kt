package ru.bezraznicy.promissingfuture.presentation.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.bezraznicy.promissingfuture.domain.model.Model

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T: Model> BasicModelList(
    title: String,
    onClickAddButton: () -> Unit,
    items: List<T>,
    wantToRemove: Model?,
    lazyItemScope: @Composable LazyItemScope.(T, DismissState) -> Unit
) {
    BasicModelList(
        title = title,
        onClickAdd = onClickAddButton,
        lazyListScope = {
            items(items = items) {
                key(it.id) {
                    val dismissState = rememberDismissState()
                    lazyItemScope(it, dismissState)
                    LaunchedEffect(key1 = wantToRemove) {
                        if (wantToRemove == null) {
                            dismissState.snapTo(DismissValue.Default)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun BasicModelList(
    title: String,
    onClickAdd: () -> Unit,
    lazyListScope: LazyListScope.() -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = title, style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(start = 12.dp))
            IconButton(onClick = onClickAdd) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }

        LazyColumn(content = lazyListScope)
    }
}