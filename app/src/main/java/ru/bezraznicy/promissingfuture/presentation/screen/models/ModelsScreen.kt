package ru.bezraznicy.promissingfuture.presentation.screen.models

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import ru.bezraznicy.promissingfuture.data.repository.RepositoryProvider
import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.domain.model.Event
import ru.bezraznicy.promissingfuture.domain.model.Knowledge
import ru.bezraznicy.promissingfuture.domain.model.Model
import ru.bezraznicy.promissingfuture.presentation.common.ModelBasicEvents
import ru.bezraznicy.promissingfuture.presentation.common.ModelBasicScreen
import ru.bezraznicy.promissingfuture.presentation.common.ModelBasicViewModel
import ru.bezraznicy.promissingfuture.presentation.screen.launcher.components.CatalogListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelsScreen(
    repositoryProvider: RepositoryProvider
) {
    var selectedModel: Model? by rememberSaveable { mutableStateOf(null) }

    when (selectedModel) {
        null -> {
            val viewModel = rememberSaveable { ModelBasicViewModel(repositoryProvider.catalogRepository) }
            ModelBasicScreen(state = viewModel.state, onEvent = viewModel::onEvent) { catalog, dismissState ->
                CatalogListItem(
                    catalog = catalog,
                    dismissState = dismissState,
                    onClick = { selectedModel = catalog },
                    onSwipeRemove = { viewModel.onEvent(ModelBasicEvents.WantToRemoveModel(catalog)) },
                    onSwipeShare = { viewModel.onEvent(ModelBasicEvents.ShareModel(catalog)) }
                )
            }
        }
        is Catalog -> {
            val viewModel = rememberSaveable { ModelBasicViewModel(repositoryProvider.eventRepository, selectedModel) }
            ModelBasicScreen(state = viewModel.state, onEvent = viewModel::onEvent) { event, dismissState ->
                CatalogListItem(
                    catalog = Catalog("123123"),
                    dismissState = dismissState,
                    onClick = { selectedModel = event },
                    onSwipeRemove = { viewModel.onEvent(ModelBasicEvents.WantToRemoveModel(event)) },
                    onSwipeShare = { viewModel.onEvent(ModelBasicEvents.ShareModel(event)) }
                )
            }
        }
        is Event -> TODO()
        is Knowledge -> TODO()
    }
}