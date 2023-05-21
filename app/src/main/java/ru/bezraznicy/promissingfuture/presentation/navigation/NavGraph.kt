package ru.bezraznicy.promissingfuture.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.bezraznicy.promissingfuture.common.PromissingFuture
import ru.bezraznicy.promissingfuture.presentation.screen.models.ModelsScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    // Get the current context
    val context = LocalContext.current
    // Get the Application instance
    val application = context.applicationContext as PromissingFuture
    NavHost(navController = navController, startDestination = Screen.Launcher.route) {
        // Показать список каталогов, список событий в каталоге и список знаний
        composable(Screen.Launcher.route) {
            ModelsScreen(repositoryProvider = application)
        }

        // Поделиться любой моделью
        composableWithArgument(Screen.Share.EMPTY_ROUTE, SHARE_INFO, { type = NavType.StringType } ) {
            TODO()
        }

        // Показать детали знания
        composableWithArgument(Screen.KnowledgeDetail.EMPTY_ROUTE, KNOWLEDGE_INFO) {
            TODO()
        }
    }
}

fun NavGraphBuilder.composableWithArgument(
    route: String,
    argumentKey: String,
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