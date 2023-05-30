package ru.bezraznicy.promissingfuture.presentation.screen.models.vm

import ru.bezraznicy.promissingfuture.domain.model.Model
import ru.bezraznicy.promissingfuture.presentation.common.ModelType
import ru.bezraznicy.promissingfuture.presentation.navigation.Screen

data class ModelsListState(
    val listedModelType: ModelType = ModelType.CATALOG,
    val selectedModel: Model? = null,
    val loading: Boolean = false,
    val navigationDestination: Screen? = null
) {
    /**
     * Убирает состояние навигации и загрузки, но не данные
     */
    fun softReset(): ModelsListState = copy(loading = false, navigationDestination = null)
}