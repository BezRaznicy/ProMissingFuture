package ru.bezraznicy.promissingfuture.presentation.screen.models.vm

import ru.bezraznicy.promissingfuture.domain.model.Model

sealed class ModelsListEvents {
    object Reset: ModelsListEvents()

    class SelectModel(model: Model): ModelsListEvents()
    object PressBackButton: ModelsListEvents()
}