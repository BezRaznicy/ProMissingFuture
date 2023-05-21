package ru.bezraznicy.promissingfuture.presentation.screen.create.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.bezraznicy.promissingfuture.data.repository.ModelRepository
import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.domain.model.Event
import ru.bezraznicy.promissingfuture.domain.model.Knowledge
import ru.bezraznicy.promissingfuture.domain.model.Model
import ru.bezraznicy.promissingfuture.domain.model.ModelBuilder
import ru.bezraznicy.promissingfuture.presentation.common.ModelType

class CreateViewModel<T: Model>(
    private val ownerModel: Long?,
    private val repository: ModelRepository<T>,
    modelType: ModelType
): ViewModel() {

    var state by mutableStateOf(CreateState(modelType))
        private set

    // For replace
    constructor(model: Model, repository: ModelRepository<T>, modelType: ModelType) : this(model.id, repository, modelType) {
        state = state.copy(modelBuilder = when (model) {
            is Catalog -> ModelBuilder(model.name)
            is Event -> ModelBuilder(model.name, model.description ?: "", model.time ?: "")
            is Knowledge -> ModelBuilder(model.name, model.description ?: "")
        }.also { it.id = model.id }, modelToCreate = modelType)
    }

    fun onEvent(event: CreateEvent) {
        when (event) {
            is CreateEvent.SaveModel -> {
                state = state.copy(loading = true)
                viewModelScope.launch(Dispatchers.IO) {
                    state.modelBuilder.name = event.name
                    state.modelBuilder.description = event.description
                    state.modelBuilder.time = event.time
                    repository.insert(state.modelBuilder.build(state.modelToCreate, ownerModel) as T)
                    withContext(Dispatchers.Main) {
                        state = state.copy(loading = false)
                    }
                }
            }
        }
    }
}