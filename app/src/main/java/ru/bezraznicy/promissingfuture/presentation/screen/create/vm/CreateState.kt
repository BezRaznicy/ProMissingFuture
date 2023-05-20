package ru.bezraznicy.promissingfuture.presentation.screen.create.vm

import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.presentation.navigation.Screen

data class CreateState(
    val catalogs: List<Catalog> = emptyList(),
    val wantToRemove: Catalog? = null,
    val wantToCreate: Boolean = false,
    val removing: Boolean = false,
    val navigationDestination: Screen? = null
)