package ru.bezraznicy.promissingfuture.presentation.screen.create.vm

import ru.bezraznicy.promissingfuture.domain.model.Catalog

sealed class CreateEvent {
    class SelectCatalog(val catalog: Catalog): CreateEvent()
    class ConfirmRemoveCatalog(val catalog: Catalog): CreateEvent()
    class RemoveCatalog(val catalog: Catalog): CreateEvent()
    class ShareCatalog(val catalog: Catalog): CreateEvent()
    object AddCatalog : CreateEvent()
    object WantToAddCatalog : CreateEvent()
    object Start : CreateEvent()
    object Reset : CreateEvent()
}
