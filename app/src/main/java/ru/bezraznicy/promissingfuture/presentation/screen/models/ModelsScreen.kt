package ru.bezraznicy.promissingfuture.presentation.screen.models

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.DismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import ru.bezraznicy.promissingfuture.data.repository.ModelRepository
import ru.bezraznicy.promissingfuture.data.repository.RepositoryProvider
import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.domain.model.Event
import ru.bezraznicy.promissingfuture.domain.model.Knowledge
import ru.bezraznicy.promissingfuture.domain.model.Model
import ru.bezraznicy.promissingfuture.presentation.common.ModelBasicEvents
import ru.bezraznicy.promissingfuture.presentation.common.ModelBasicScreen
import ru.bezraznicy.promissingfuture.presentation.common.ModelBasicViewModel
import ru.bezraznicy.promissingfuture.presentation.common.ModelType
import ru.bezraznicy.promissingfuture.presentation.navigation.Screen
import ru.bezraznicy.promissingfuture.presentation.screen.models.components.catalogs.CatalogListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelsScreen(
    repositoryProvider: RepositoryProvider
) {
    var selectedModel: Model? by rememberSaveable { mutableStateOf(null) }

    when (selectedModel) {
        null -> {
            AbstractScreen(
                repositoryProvider.catalogRepository,
                onSelectModel = { selectedModel = it },
                lazyItemScope = { catalog, dismissState, onClick, onRemove, onShare ->
                    CatalogListItem(catalog, dismissState, {onClick(catalog)}, onRemove, onShare)
                },
                modelType = ModelType.CATALOG
            )
        }
        is Catalog -> {
            AbstractScreen(
                repositoryProvider.catalogRepository,
                onSelectModel = { selectedModel = it },
                lazyItemScope = { catalog, dismissState, onClick, onRemove, onShare ->
                    CatalogListItem(catalog, dismissState, {onClick(catalog)}, onRemove, onShare)
                },
                modelType = ModelType.EVENT
            )
        }
        is Event -> AbstractScreen(
            repositoryProvider.knowledgeRepository,
            onSelectModel = { Screen.KnowledgeDetail(it) },
            lazyItemScope = { catalog, dismissState, onClick, onRemove, onShare ->
            },
            modelType = ModelType.KNOWLEDGE
        )
        is Knowledge -> throw IllegalStateException("Should not be knowledge. " +
                "Redirect to specific screen.")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T: Model> AbstractScreen(
    repository: ModelRepository<T>,
    onSelectModel: (T) -> Unit,
    modelType: ModelType,
    ownerModel: Model? = null,
    lazyItemScope: @Composable LazyItemScope.(T, DismissState, (T) -> Unit, () -> Unit, () -> Unit) -> Unit
) {
    val viewModel = rememberSaveable { ModelBasicViewModel(repository, ownerModel) }
    ModelBasicScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        lazyItemScope = { model, dismissState ->
            lazyItemScope(
                model,
                dismissState,
                onSelectModel,
                { viewModel.onEvent(ModelBasicEvents.WantToRemoveModel(model)) },
                { viewModel.onEvent(ModelBasicEvents.ShareModel(model)) }
            )
        },
        modelType = modelType
    )
}