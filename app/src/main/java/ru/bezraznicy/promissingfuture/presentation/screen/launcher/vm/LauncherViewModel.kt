package ru.bezraznicy.promissingfuture.presentation.screen.launcher.vm

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

class LauncherViewModel(private val catalogRepository: CatalogRepository): ViewModel() {

    var state by mutableStateOf(LauncherState())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val catalogRepo = catalogRepository.selectAll()
            withContext(Dispatchers.Main) {
                if (!state.catalogs.containsAll(catalogRepo))
                    state = state.copy(catalogs = catalogRepo)
            }
        }
    }

    fun onEvent(event: LauncherEvent) {
        when (event) {
            // Adding through share or creating new one
            LauncherEvent.WantToAddCatalog -> {

            }
            is LauncherEvent.AddCatalog -> {
                TODO()
            }
            is LauncherEvent.WantToRemoveCatalog -> {
                state = state.copy(wantToRemove = event.catalog)
            }
            is LauncherEvent.RemoveCatalog -> {
                state = state.copy(removing = true)
                viewModelScope.launch(Dispatchers.IO) {
                    catalogRepository.delete(event.catalog)
                    delay(1500)
                    val catalogRepo = catalogRepository.selectAll()
                    withContext(Dispatchers.Main) {
                        if (!state.catalogs.containsAll(catalogRepo))
                            state = state.copy(wantToRemove = null, removing = false, catalogs = catalogRepo)
                    }
                }
            }
            is LauncherEvent.SelectCatalog -> {
                TODO()
            }
            is LauncherEvent.ShareCatalog -> {
                TODO()
            }
            LauncherEvent.Reset -> {
                state = state.copy(wantToRemove = null, removing = false, navigationDestination = null)
            }
        }
    }
}