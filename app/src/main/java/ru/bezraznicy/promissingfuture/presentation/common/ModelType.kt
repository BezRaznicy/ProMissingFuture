package ru.bezraznicy.promissingfuture.presentation.common

import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.domain.model.Event
import ru.bezraznicy.promissingfuture.domain.model.Knowledge
import ru.bezraznicy.promissingfuture.domain.model.Model

enum class ModelType(val type: String) {
    CATALOG("Каталог"),
    EVENT("Событие"),
    KNOWLEDGE("Знание");

    companion object {
        fun identify(model: Model) = when (model) {
            is Catalog -> CATALOG
            is Event -> EVENT
            is Knowledge -> KNOWLEDGE
        }
    }
}