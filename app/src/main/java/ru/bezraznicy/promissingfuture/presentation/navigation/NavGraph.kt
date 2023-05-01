package ru.bezraznicy.promissingfuture.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Launcher.route) {
        // Show & Edit Список каталогов
        composable(Screen.Launcher.route) {
            TODO()
        }

        // Поделиться любой моделью
        composableWithArgument(Screen.Share.EMPTY_ROUTE, SHARE_INFO, { type = NavType.StringType } ) {
            TODO()
        }

        // TODO: Может быть это всё объединить с помощью Model...
        // Show & Edit Catalog
        composableWithArgument(Screen.CatalogDetail.EMPTY_ROUTE, CATALOG_INFO) {
            TODO()
        }

        // Show & Edit Event
        composableWithArgument(Screen.EventDetail.EMPTY_ROUTE, EVENT_INFO) {
            TODO()
        }

        // Show & Edit Knowledge
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