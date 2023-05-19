package ru.bezraznicy.promissingfuture.presentation.screen.launcher

import ru.bezraznicy.promissingfuture.domain.model.Catalog


sealed class LauncherEvent {
    class SelectCatalog(val catalog: Catalog): LauncherEvent()
    class ConfirmRemoveCatalog(val catalog: Catalog): LauncherEvent()
    class RemoveCatalog(val catalog: Catalog): LauncherEvent()
    class ShareCatalog(val catalog: Catalog): LauncherEvent()
    object AddCatalog : LauncherEvent()
    object Start : LauncherEvent()
}
