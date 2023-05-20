package ru.bezraznicy.promissingfuture.presentation.screen.create.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.bezraznicy.promissingfuture.data.repository.CatalogRepository

class CreateViewModel(private val catalogRepository: CatalogRepository): ViewModel() {

    var state by mutableStateOf(CreateState())
        private set

    init {
        onEvent(CreateEvent.Start)
    }

    fun onEvent(event: CreateEvent) {

    }
}