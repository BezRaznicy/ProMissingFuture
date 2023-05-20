package ru.bezraznicy.promissingfuture.presentation.screen.launcher.vm

import ru.bezraznicy.promissingfuture.domain.model.Catalog

sealed class LauncherEvent {
    object Reset : LauncherEvent()

    class SelectCatalog(val catalog: Catalog): LauncherEvent()
    class ShareCatalog(val catalog: Catalog): LauncherEvent()

    class RemoveCatalog(val catalog: Catalog): LauncherEvent()
    class WantToRemoveCatalog(val catalog: Catalog): LauncherEvent()

    object AddCatalog : LauncherEvent()
    object WantToAddCatalog : LauncherEvent()
}
