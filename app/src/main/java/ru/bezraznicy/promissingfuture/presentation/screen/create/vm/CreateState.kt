package ru.bezraznicy.promissingfuture.presentation.screen.create.vm

import ru.bezraznicy.promissingfuture.domain.model.Model
import ru.bezraznicy.promissingfuture.domain.model.ModelBuilder
import ru.bezraznicy.promissingfuture.presentation.common.ModelType
import ru.bezraznicy.promissingfuture.presentation.navigation.Screen

data class CreateState(
    val modelToCreate: ModelType,
    val modelBuilder: ModelBuilder = ModelBuilder(""),
    val finalModel: Model? = null,
    val loading: Boolean = false,
    val navigationDestination: Screen? = null
) {
    /**
     * Убирает состояние навигации и загрузки, но не данные
     */
    fun softReset(): CreateState = copy(loading = false, navigationDestination = null)
}