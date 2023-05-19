package ru.bezraznicy.promissingfuture.presentation.screen.launcher

import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.presentation.navigation.Screen

data class LauncherState(
    val catalogs: List<Catalog> = emptyList(),
    val catalogToRemove: Catalog? = null,
    val navigationDestination: Screen? = null
)