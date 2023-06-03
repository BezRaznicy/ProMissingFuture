package ru.bezraznicy.promissingfuture.presentation.screen.models.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import ru.bezraznicy.promissingfuture.data.repository.RepositoryProvider
import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.domain.model.Event
import ru.bezraznicy.promissingfuture.domain.model.Knowledge
import ru.bezraznicy.promissingfuture.presentation.navigation.Screen
import ru.bezraznicy.promissingfuture.presentation.screen.models.common.ModelBasicEvents
import ru.bezraznicy.promissingfuture.presentation.screen.models.common.ModelBasicScreen
import ru.bezraznicy.promissingfuture.presentation.screen.models.common.ModelBasicViewModel
import ru.bezraznicy.promissingfuture.presentation.screen.models.common.ModelType
import ru.bezraznicy.promissingfuture.presentation.screen.models.components.CatalogListItem
import ru.bezraznicy.promissingfuture.presentation.screen.models.components.EventItem
import ru.bezraznicy.promissingfuture.presentation.screen.models.components.KnowledgeItem

const val INNER_MODULE_ROUTE = "models"

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.modelsNavigation(
    navController: NavController,
    repositoryProvider: RepositoryProvider
) {
    navigation(startDestination = ModelsScreens.Catalogs.route, route = INNER_MODULE_ROUTE) {
        composable(ModelsScreens.Catalogs.route) {
            val viewModel: ModelBasicViewModel<Catalog> = viewModel(
                factory = viewModelFactory {
                    initializer {
                        ModelBasicViewModel(repositoryProvider.catalogRepository)
                    }
                }
            )
            ModelBasicScreen(
                state = viewModel.state,
                onEvent = viewModel::onEvent,
                lazyItemScope = { model, dismissState ->
                    CatalogListItem(
                        catalog = model,
                        dismissState = dismissState,
                        onClick = { navController.navigate(ModelsScreens.Events(model).route) },
                        onLongClick = { navController.navigate(Screen.Replace(model).route) },
                        onSwipeRemove = { viewModel.onEvent(ModelBasicEvents.WantToRemoveModel(model)) },
                        onSwipeShare = { viewModel.onEvent(ModelBasicEvents.ShareModel(model)) }
                    )
                },
                modelType = ModelType.CATALOG,
                navController = navController
            )
        }

        composableWithIdArgument(ModelsScreens.Events.EMPTY_ROUTE) {
            val ownerId = it.arguments?.getLong(OWNER_ID) ?: -1

            val viewModel: ModelBasicViewModel<Event> = viewModel(
                factory = viewModelFactory {
                    initializer {
                        ModelBasicViewModel(repositoryProvider.eventRepository, ownerId)
                    }
                }
            )
            ModelBasicScreen(
                state = viewModel.state,
                onEvent = viewModel::onEvent,
                lazyItemScope = { model, dismissState ->
                    EventItem(
                        event = model,
                        dismissState = dismissState,
                        onClick = { navController.navigate(ModelsScreens.Knowledges(model).route) },
                        onLongClick = { navController.navigate(Screen.Replace(model).route) },
                        onSwipeRemove = { viewModel.onEvent(ModelBasicEvents.WantToRemoveModel(model)) },
                        onSwipeShare = { viewModel.onEvent(ModelBasicEvents.ShareModel(model)) }
                    )
                },
                modelType = ModelType.EVENT,
                navController = navController
            )
        }

        composableWithIdArgument(ModelsScreens.Knowledges.EMPTY_ROUTE) { it ->
            val ownerId = it.arguments?.getLong(OWNER_ID) ?: -1

            val viewModel: ModelBasicViewModel<Knowledge> = viewModel(
                factory = viewModelFactory {
                    initializer {
                        ModelBasicViewModel(repositoryProvider.knowledgeRepository, ownerId)
                    }
                }
            )
            ModelBasicScreen(
                state = viewModel.state,
                onEvent = viewModel::onEvent,
                lazyItemScope = { model, dismissState ->
                    KnowledgeItem(
                        knowledge = model,
                        dismissState = dismissState,
                        onClick = { navController.navigate(Screen.Replace(model).route) },
                        onLongClick = { navController.navigate(Screen.Replace(model).route) },
                        onSwipeRemove = { viewModel.onEvent(ModelBasicEvents.WantToRemoveModel(model)) },
                        onSwipeShare = { viewModel.onEvent(ModelBasicEvents.ShareModel(model)) }
                    )
                },
                modelType = ModelType.KNOWLEDGE,
                navController = navController
            )
        }
    }
}

fun NavGraphBuilder.composableWithIdArgument(
    route: String,
    argumentKey: String = OWNER_ID,
    argumentType: NavArgumentBuilder.() -> Unit = { type = NavType.LongType },
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = listOf(
            navArgument(argumentKey, argumentType)
        ),
        content = content
    )
}

