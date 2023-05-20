package ru.bezraznicy.promissingfuture.presentation.screen.launcher

import android.content.res.Configuration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.presentation.common.components.BasicModelList
import ru.bezraznicy.promissingfuture.presentation.screen.launcher.components.CatalogListItem
import ru.bezraznicy.promissingfuture.presentation.common.components.ConfirmRemoveDialog
import ru.bezraznicy.promissingfuture.presentation.common.components.LoadingDialog
import ru.bezraznicy.promissingfuture.presentation.screen.launcher.vm.LauncherEvent
import ru.bezraznicy.promissingfuture.presentation.screen.launcher.vm.LauncherState
import ru.bezraznicy.promissingfuture.presentation.theme.PromissingFutureTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LauncherScreen(
    launcherState: LauncherState,
    onEvent: (LauncherEvent) -> Unit
) {
    if (launcherState.removing) {
        LoadingDialog("Вносим изменения...")
    }
    if (launcherState.wantToRemove != null) {
        ConfirmRemoveDialog(
            modelToRemove = launcherState.wantToRemove,
            onConfirm = { onEvent(LauncherEvent.RemoveCatalog(launcherState.wantToRemove)) },
            onDismiss = { onEvent(LauncherEvent.Reset) },
        )
    }
    BasicModelList(
        title = "Каталоги",
        onClickAddButton = { onEvent(LauncherEvent.AddCatalog) },
        items = launcherState.catalogs,
        wantToRemove = launcherState.wantToRemove,
        lazyItemScope = { catalog, dismissState ->
            CatalogListItem(
                catalog = catalog,
                dismissState = dismissState,
                onClick = { onEvent(LauncherEvent.SelectCatalog(catalog)) },
                onSwipeRemove = { onEvent(LauncherEvent.WantToRemoveCatalog(catalog)) },
                onSwipeShare = { onEvent(LauncherEvent.ShareCatalog(catalog)) }
            )
        },
    )
}

@Preview(showSystemUi = true)
@Composable
fun LauncherPreviewLightTheme() {
    PromissingFutureTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            LauncherScreen(
                LauncherState(
                    listOf(
                        Catalog("Любовь"),
                        Catalog("Учёба"),
                        Catalog("Работа"),
                        Catalog("Хобби"),
                    )
                )
            ) {}
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
fun LauncherPreviewDarkTheme() {
    PromissingFutureTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            LauncherScreen(
                LauncherState(
                    listOf(
                        Catalog("Любовь"),
                        Catalog("Учёба"),
                        Catalog("Работа"),
                        Catalog("Хобби"),
                    )
                )
            ) {}
        }
    }
}