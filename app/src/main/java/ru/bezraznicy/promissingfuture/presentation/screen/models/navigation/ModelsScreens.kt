package ru.bezraznicy.promissingfuture.presentation.screen.models.navigation

import ru.bezraznicy.promissingfuture.domain.model.Model

const val OWNER_ID = "ownerid"

sealed class ModelsScreens(val route: String) {

    object Catalogs: ModelsScreens(route = "catalog")

    class Events(owner: Model): ModelsScreens(route = "events/${owner.id}") {
        companion object {
            const val EMPTY_ROUTE = "create/{$OWNER_ID}"
        }
    }

    class Knowledges(owner: Model): ModelsScreens(route = "knowledges/${owner.id}") {
        companion object {
            const val EMPTY_ROUTE = "knowledges/{$OWNER_ID}"
        }
    }
}