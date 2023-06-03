package ru.bezraznicy.promissingfuture.presentation.screen.models.navigation

import ru.bezraznicy.promissingfuture.domain.model.Model

const val OWNER_ID = "ownerid"

sealed class ModelsScreens(val route: String) {

    object Catalogs: ModelsScreens(route = "$INNER_MODULE_ROUTE/catalog")

    class Events(owner: Model): ModelsScreens(route = "$INNER_MODULE_ROUTE/events/${owner.id}") {
        companion object {
            const val EMPTY_ROUTE = "$INNER_MODULE_ROUTE/events/{$OWNER_ID}"
        }
    }

    class Knowledges(owner: Model): ModelsScreens(route = "$INNER_MODULE_ROUTE/knowledges/${owner.id}") {
        companion object {
            const val EMPTY_ROUTE = "$INNER_MODULE_ROUTE/knowledges/{$OWNER_ID}"
        }
    }
}