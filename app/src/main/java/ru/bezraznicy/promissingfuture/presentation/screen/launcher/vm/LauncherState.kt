package ru.bezraznicy.promissingfuture.presentation.screen.launcher.vm

import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.presentation.navigation.Screen

data class LauncherState(
    val catalogs: List<Catalog> = emptyList(),
    val wantToRemove: Catalog? = null,
    val removing: Boolean = false,
    val navigationDestination: Screen? = null
)