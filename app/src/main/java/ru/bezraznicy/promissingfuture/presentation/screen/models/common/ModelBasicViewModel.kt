package ru.bezraznicy.promissingfuture.presentation.screen.models.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.bezraznicy.promissingfuture.data.repository.ModelRepository
import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.domain.model.Event
import ru.bezraznicy.promissingfuture.domain.model.Knowledge
import ru.bezraznicy.promissingfuture.domain.model.Model
import ru.bezraznicy.promissingfuture.presentation.navigation.Screen

/**
 * На самом деле используется не совсем как ViewModel, но предполагалась именно так.
 * @param modelOwner например, у каждого Knowledge есть owner - Event. Вот этот овнер и указывается.
 */
class ModelBasicViewModel<T: Model>(
    private val modelRepository: ModelRepository<T>,
    private val modelOwner: Long? = null
): ViewModel() {
    var state by mutableStateOf(ModelBasicState<T>(modelOwner = modelOwner))
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val modelRepo = if (modelOwner == null) {
                modelRepository.selectAll()
            } else {
                modelRepository.selectByOwnerId(modelOwner)
            }
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
            is ModelBasicEvents.AddThroughShare -> TODO()

            is ModelBasicEvents.WantToRemoveModel -> {
                state = state.copy(wantToRemove = event.model)
            }
            is ModelBasicEvents.RemoveModel -> {
                state = state.copy(removing = true)
                viewModelScope.launch(Dispatchers.IO) {
                    modelRepository.delete(event.model)
                    val catalogRepo = if (modelOwner == null) {
                        modelRepository.selectAll()
                    } else {
                        modelRepository.selectByOwnerId(modelOwner)
                    }
                    withContext(Dispatchers.Main) {
                        if (!state.models.containsAll(catalogRepo))
                            state = state.copy(wantToRemove = null, removing = false, models = catalogRepo)
                    }
                }
            }
            is ModelBasicEvents.ShareModel -> TODO()
        }
    }
}