package ru.bezraznicy.promissingfuture.presentation.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.bezraznicy.promissingfuture.data.local.CatalogDao
import ru.bezraznicy.promissingfuture.data.repository.CatalogRepository
import ru.bezraznicy.promissingfuture.data.repository.ModelRepository
import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.domain.model.Event
import ru.bezraznicy.promissingfuture.domain.model.Model

abstract class ModelBaseViewModel<T: Model>(private val modelRepository: ModelRepository<T>): ViewModel() {
    var state by mutableStateOf(ModelBasicState<T>())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val modelRepo = modelRepository.selectAll()
            withContext(Dispatchers.Main) {
                if (!state.models.containsAll(modelRepo))
                    state = state.copy(models = modelRepo)
            }
        }
    }

    fun onEvent(event: ModelBasicEvents<T>) {
        when (event) {
            // Reset viewmodel but not models
            is ModelBasicEvents.Reset -> {
                state = state.copy(wantToRemove = null, wantToAdd = false, removing = false, navigationDestination = null)
            }
            // Adding through share or creating new one
            is ModelBasicEvents.WantToAddModel -> {
                state = state.copy(wantToAdd = true)
            }
            is ModelBasicEvents.AddThroughCreate -> TODO()
            is ModelBasicEvents.AddThroughShare -> TODO()

            is ModelBasicEvents.WantToRemoveModel -> {
                state = state.copy(wantToRemove = event.model)
            }
            is ModelBasicEvents.RemoveModel -> {
                state = state.copy(removing = true)
                viewModelScope.launch(Dispatchers.IO) {
                    modelRepository.delete(event.model)
                    delay(1500)
                    val catalogRepo = modelRepository.selectAll()
                    withContext(Dispatchers.Main) {
                        if (!state.models.containsAll(catalogRepo))
                            state = state.copy(wantToRemove = null, removing = false, models = catalogRepo)
                    }
                }
            }
            is ModelBasicEvents.SelectModel -> {
                TODO()
            }
            is ModelBasicEvents.ShareModel -> {
                TODO()
            }
        }
    }
}