package ru.bezraznicy.promissingfuture.presentation.common

import ru.bezraznicy.promissingfuture.presentation.navigation.Screen

data class ModelBasicState<T>(
    val models: List<T> = emptyList(),
    val wantToRemove: T? = null,
    val wantToAdd: Boolean = false,
    val removing: Boolean = false,
    val navigationDestination: Screen? = null
)