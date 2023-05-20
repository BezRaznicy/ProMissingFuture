package ru.bezraznicy.promissingfuture.presentation.screen.models.components.catalogs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.presentation.common.ModelBasicEvents
import ru.bezraznicy.promissingfuture.presentation.common.ModelBasicState
import ru.bezraznicy.promissingfuture.presentation.common.components.BasicModelList
import ru.bezraznicy.promissingfuture.presentation.common.components.ConfirmRemoveDialog
import ru.bezraznicy.promissingfuture.presentation.common.components.LoadingDialog
import ru.bezraznicy.promissingfuture.presentation.screen.launcher.components.CatalogListItem
import ru.bezraznicy.promissingfuture.presentation.screen.launcher.vm.LauncherEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    state: ModelBasicState<Catalog>,
    onEvent: (ModelBasicEvents<Catalog>) -> Unit
) {
    if (state.removing) {
        LoadingDialog("Вносим изменения...")
    }
    if (state.wantToRemove != null) {
        ConfirmRemoveDialog(
            modelToRemove = state.wantToRemove,
            onConfirm = { onEvent(ModelBasicEvents.RemoveModel(state.wantToRemove)) },
            onDismiss = { onEvent(ModelBasicEvents.Reset()) },
        )
    }
    BasicModelList(
        title = "Каталоги",
        onClickAddButton = { onEvent(ModelBasicEvents.WantToAddModel()) },
        items = state.models,
        wantToRemove = state.wantToRemove,
        lazyItemScope = { catalog, dismissState ->
            CatalogListItem(
                catalog = catalog,
                dismissState = dismissState,
                onClick = { onEvent(ModelBasicEvents.SelectModel(catalog)) },
                onSwipeRemove = { onEvent(ModelBasicEvents.WantToRemoveModel(catalog)) },
                onSwipeShare = { onEvent(ModelBasicEvents.ShareModel(catalog)) }
            )
        },
    )
}