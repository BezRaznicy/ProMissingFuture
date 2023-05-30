package ru.bezraznicy.promissingfuture.presentation.screen.models.vm

import ru.bezraznicy.promissingfuture.data.repository.RepositoryProvider

class ModelsListViewModel(val repositoryProvider: RepositoryProvider) {

    var state: ModelsListState = ModelsListState()
}