package ru.bezraznicy.promissingfuture.presentation.common

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.DismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ru.bezraznicy.promissingfuture.domain.model.Model
import ru.bezraznicy.promissingfuture.presentation.common.components.AddNewDialog
import ru.bezraznicy.promissingfuture.presentation.common.components.BasicModelList
import ru.bezraznicy.promissingfuture.presentation.common.components.ConfirmRemoveDialog
import ru.bezraznicy.promissingfuture.presentation.common.components.LoadingDialog
import ru.bezraznicy.promissingfuture.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T: Model> ModelBasicScreen(
    state: ModelBasicState<T>,
    onEvent: (ModelBasicEvents<T>) -> Unit,
    lazyItemScope: @Composable LazyItemScope.(T, DismissState) -> Unit,
    modelType: ModelType,
    navController: NavController
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
    if (state.wantToAdd) {
        AddNewDialog(
            modelType = modelType,
            wantToCreate = { navController.navigate(Screen.Create(state.modelOwner, modelType).route) },
            wantToImport = { onEvent(ModelBasicEvents.AddThroughShare()) },
            onClose = { onEvent(ModelBasicEvents.Reset()) }
        )
    }
    BasicModelList(
        title = modelType.type,
        onClickAddButton = { onEvent(ModelBasicEvents.WantToAddModel()) },
        items = state.models,
        wantToRemove = state.wantToRemove,
        lazyItemScope = lazyItemScope,
    )
}