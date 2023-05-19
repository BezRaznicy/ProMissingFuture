package ru.bezraznicy.promissingfuture.presentation.screen.launcher

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ru.bezraznicy.promissingfuture.domain.model.Catalog

class LauncherViewModel: ViewModel() {

    var state by mutableStateOf(LauncherState(listOf(Catalog(1, "Работа"), Catalog(2, "Учеба"), Catalog(3, "Хобби"))))
        private set

    fun onEvent(event: LauncherEvent) {
        when (event) {
            is LauncherEvent.Start -> {
                state = state.copy(catalogToRemove = null, navigationDestination = null)
            }
            is LauncherEvent.AddCatalog -> {
                TODO()
            }
            is LauncherEvent.ConfirmRemoveCatalog -> {
                state = state.copy(catalogToRemove = event.catalog)
            }
            is LauncherEvent.RemoveCatalog -> {
                val modifiedList = state.catalogs.toMutableList()
                modifiedList.remove(event.catalog)
                state = state.copy(catalogToRemove = null, catalogs = modifiedList)
            }
            is LauncherEvent.SelectCatalog -> {
                TODO()
            }
            is LauncherEvent.ShareCatalog -> {
                TODO()
            }
        }
    }
}