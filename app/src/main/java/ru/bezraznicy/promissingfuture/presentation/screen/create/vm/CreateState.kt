package ru.bezraznicy.promissingfuture.presentation.screen.create.vm

import ru.bezraznicy.promissingfuture.domain.model.ModelBuilder
import ru.bezraznicy.promissingfuture.presentation.common.ModelType
import ru.bezraznicy.promissingfuture.presentation.navigation.Screen

data class CreateState(
    val modelToCreate: ModelType,
    val modelBuilder: ModelBuilder = ModelBuilder(""),
    val loading: Boolean = false,
    val navigationDestination: Screen? = null
)