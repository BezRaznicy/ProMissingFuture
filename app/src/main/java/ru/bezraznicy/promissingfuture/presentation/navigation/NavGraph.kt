package ru.bezraznicy.promissingfuture.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import ru.bezraznicy.promissingfuture.common.PromissingFuture
import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.domain.model.Event
import ru.bezraznicy.promissingfuture.domain.model.Knowledge
import ru.bezraznicy.promissingfuture.domain.model.Model
import ru.bezraznicy.promissingfuture.presentation.screen.models.common.ModelType
import ru.bezraznicy.promissingfuture.presentation.screen.create.CreateScreen
import ru.bezraznicy.promissingfuture.presentation.screen.create.vm.CreateViewModel
import ru.bezraznicy.promissingfuture.presentation.screen.models.navigation.modelsNavigation

@Composable
fun SetupNavGraph(navController: NavHostController) {
    // Get the current context
    val context = LocalContext.current
    // Get the Application instance
    val application = context.applicationContext as PromissingFuture
    NavHost(navController = navController, startDestination = "models") {
        // Показать список каталогов, список событий в каталоге и список знаний
        // models
        modelsNavigation(navController, application)

        // Создать модель
        composable(
            route = Screen.Create.EMPTY_ROUTE,
            arguments = listOf(
                navArgument(OWNER) { type = NavType.LongType },
                navArgument(MODEL_TYPE) { type = NavType.StringType }
            ),
        ) { backStackEntry ->
            val owner = backStackEntry.arguments?.getLong(OWNER, -1L)
                ?.let { if (it == -1L) null else it }
            val modelType = ModelType.valueOf(backStackEntry.arguments!!.getString(MODEL_TYPE)!!)
            val createViewModel: CreateViewModel<Model> = viewModel(
                // TODO: утечка памяти? Каждый созданный экран = 1 ViewModel в памяти, что плохо наверное
                 factory = viewModelFactory {
                     initializer {
                         when (modelType) {
                             ModelType.CATALOG -> CreateViewModel(
                                 owner,
                                 application.catalogRepository,
                                 modelType
                             )

                             ModelType.EVENT -> CreateViewModel(
                                 owner,
                                 application.eventRepository,
                                 modelType
                             )

                             ModelType.KNOWLEDGE -> CreateViewModel(
                                 owner,
                                 application.knowledgeRepository,
                                 modelType
                             )
                         }
                     }
                 }
            )
            CreateScreen(
                navController = navController,
                state = createViewModel.state,
                onEvent = { createViewModel.onEvent(it) }
            )
        }

        // Replace model
        composable(
            route = Screen.Replace.EMPTY_ROUTE,
            arguments = listOf(
                navArgument(MODEL) { type = NavType.StringType }
            ),
        ) { backStackEntry ->
            val gson = backStackEntry.arguments!!.getString(MODEL_TO_EDIT)
            val modelType = backStackEntry.arguments!!.getSerializable(MODEL_TYPE) as ModelType
            // TODO: утечка памяти? Каждый созданный экран = 1 ViewModel в памяти, что плохо
            val createViewModel: CreateViewModel<Model> = viewModel(
                factory = viewModelFactory {
                initializer {
                    when (modelType) {
                        ModelType.CATALOG -> CreateViewModel(
                            Gson().fromJson(gson, Catalog::class.java),
                            application.catalogRepository, modelType
                        )
                        ModelType.EVENT -> CreateViewModel(
                            Gson().fromJson(gson, Event::class.java),
                            application.eventRepository, modelType
                        )
                        ModelType.KNOWLEDGE -> CreateViewModel(
                            Gson().fromJson(gson, Knowledge::class.java),
                            application.knowledgeRepository, modelType
                        )
                    }
                }
            }
            )
            CreateScreen(
                navController = navController,
                state = createViewModel.state,
                onEvent = { createViewModel.onEvent(it) }
            )
        }
    }
}