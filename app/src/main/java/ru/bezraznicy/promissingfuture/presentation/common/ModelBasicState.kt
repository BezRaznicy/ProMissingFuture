package ru.bezraznicy.promissingfuture.presentation.common

import ru.bezraznicy.promissingfuture.domain.model.Model
import ru.bezraznicy.promissingfuture.presentation.navigation.Screen

data class ModelBasicState<T: Model>(
    val models: List<T> = emptyList(),
    val modelOwner: Model? = null,
    val wantToRemove: T? = null,
    val wantToAdd: Boolean = false,
    val removing: Boolean = false,
    val navigationDestination: Screen? = null
)