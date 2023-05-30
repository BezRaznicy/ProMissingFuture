package ru.bezraznicy.promissingfuture.presentation.screen.models

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.DismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
import ru.bezraznicy.promissingfuture.presentation.screen.create.vm.CreateEvent
import ru.bezraznicy.promissingfuture.presentation.screen.create.vm.CreateState
import ru.bezraznicy.promissingfuture.presentation.screen.models.components.CatalogListItem
import ru.bezraznicy.promissingfuture.presentation.screen.models.components.EventItem
import ru.bezraznicy.promissingfuture.presentation.screen.models.components.KnowledgeItem


// TODO: redo MVI screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelsScreen(
    navController: NavController = rememberNavController(),
    state: CreateState = CreateState(ModelType.EVENT),
    onEvent: (CreateEvent) -> Unit = {}
) {
    var selectedModel: Model? by remember { mutableStateOf(null) }

    when (selectedModel) {
        null -> AbstractScreen(
                repositoryProvider.catalogRepository,
                onSelectModel = { selectedModel = it },
                lazyItemScope = { catalog, dismissState, onClick, onRemove, onShare ->
                    CatalogListItem(catalog, dismissState, { onClick(catalog) }, onRemove, onShare)
                },
                modelType = ModelType.CATALOG,
                navController = navController
        )
        is Catalog -> AbstractScreen(
                repositoryProvider.eventRepository,
                onSelectModel = { selectedModel = it },
                ownerModel = selectedModel,
                lazyItemScope = { event, dismissState, onClick, onRemove, onShare ->
                    EventItem(event, dismissState, { onClick(event) }, onRemove, onShare)
                },
                modelType = ModelType.EVENT,
                navController = navController
            )
        is Event -> AbstractScreen(
            repositoryProvider.knowledgeRepository,
            onSelectModel = { navController.navigate(Screen.KnowledgeDetail(it).route) },
            lazyItemScope = { knowledge, dismissState, onClick, onRemove, onShare ->
                KnowledgeItem(
                    knowledge = knowledge,
                    dismissState = dismissState,
                    onClick = { onClick(knowledge) },
                    onSwipeRemove = onRemove,
                    onSwipeShare = onShare
                )
            },
            ownerModel = selectedModel,
            modelType = ModelType.KNOWLEDGE,
            navController = navController
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
    navController: NavController,
    lazyItemScope: @Composable LazyItemScope.(T, DismissState, (T) -> Unit, () -> Unit, () -> Unit) -> Unit
) {
    val viewModel = remember { ModelBasicViewModel(repository, ownerModel) }
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
        modelType = modelType,
        navController = navController
    )
}