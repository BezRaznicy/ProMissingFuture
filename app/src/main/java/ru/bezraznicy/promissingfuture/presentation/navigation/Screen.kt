package ru.bezraznicy.promissingfuture.presentation.navigation

import com.google.gson.Gson
import ru.bezraznicy.promissingfuture.domain.model.Knowledge
import ru.bezraznicy.promissingfuture.domain.model.Model
import ru.bezraznicy.promissingfuture.presentation.common.ModelType

const val KNOWLEDGE_INFO = "knowledge"
const val SHARE_INFO = "share"
const val OWNER = "owner"
const val MODEL_TO_EDIT = "modeltoedit"
const val MODEL_TYPE = "modeltype"

/**
 * Определяет ссылку [route] для навигации
 */
sealed class Screen(val route: String) {
    /**
     * Показывает абстрактный экран с каталогами, событиями и знаниями
     */
    object Launcher: Screen(route = "launcher")

    /**
     * Экран "Поделиться"
     * Позволяет делиться [Model]
     */
    class Share(item: Model): Screen(route = "share?$SHARE_INFO={${item}}") {
        companion object {
            const val EMPTY_ROUTE = "share?$SHARE_INFO={$SHARE_INFO}"
        }
    }

    /**
     * Экран "Создать"
     * Позволяет создавать [Model]
     */
    class Create(owner: Model?, modelType: ModelType): Screen(route = "create/${owner?.id ?: -1}/$modelType") {
        companion object {
            const val EMPTY_ROUTE = "create/{$OWNER}/{$MODEL_TYPE}"
        }
    }

    /**
     * Экран "Заменить"
     * Позволяет изменить [Model]
     */
    class Replace(modelToEdit: Model, modelType: ModelType): Screen(
        route = "create?$MODEL_TO_EDIT={${Gson().toJson(modelToEdit)}}&$MODEL_TYPE={$modelType}"
    ) {
        companion object {
            const val EMPTY_ROUTE = "replace?$MODEL_TO_EDIT={$MODEL_TO_EDIT}&$MODEL_TYPE={$MODEL_TYPE}"
        }
    }

    /**
     * Экран "Знание"
     * Показывает детальную информацию о [Knowledge]
     */
    class KnowledgeDetail(knowledge: Knowledge): Screen(route = "knowledge?$KNOWLEDGE_INFO={${knowledge}}") {
        companion object {
            const val EMPTY_ROUTE = "knowledge?$KNOWLEDGE_INFO={$KNOWLEDGE_INFO}"
        }
    }
}