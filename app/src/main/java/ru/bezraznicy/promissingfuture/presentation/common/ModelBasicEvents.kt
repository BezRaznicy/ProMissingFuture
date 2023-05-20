package ru.bezraznicy.promissingfuture.presentation.common

import ru.bezraznicy.promissingfuture.domain.model.Model

sealed class ModelBasicEvents<T: Model> {
    class Reset<T: Model> : ModelBasicEvents<T>()

    class SelectModel<T: Model>(val model: T): ModelBasicEvents<T>()
    class ShareModel<T: Model>(val model: T): ModelBasicEvents<T>()

    class WantToRemoveModel<T: Model>(val model: T): ModelBasicEvents<T>()
    class RemoveModel<T: Model>(val model: T): ModelBasicEvents<T>()

    class WantToAddModel<T: Model> : ModelBasicEvents<T>()
    class AddThroughCreate<T: Model> : ModelBasicEvents<T>()
    class AddThroughShare<T: Model> : ModelBasicEvents<T>()
}