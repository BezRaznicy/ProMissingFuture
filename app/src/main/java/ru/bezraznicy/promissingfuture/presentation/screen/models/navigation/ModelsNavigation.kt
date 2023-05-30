package ru.bezraznicy.promissingfuture.presentation.screen.models.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument

fun NavGraphBuilder.modelsNavigation(
    navController: NavController
) {
    navigation(startDestination = "catalogs", route = "models") {
        composableWithIdArgument("catalogs") {
            CatalogList
        }

        composableWithIdArgument("events") {

        }

        composableWithIdArgument("knowledges") {

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