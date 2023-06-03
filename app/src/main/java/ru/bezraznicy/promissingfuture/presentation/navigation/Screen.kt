package ru.bezraznicy.promissingfuture.presentation.navigation

import com.google.gson.Gson
import ru.bezraznicy.promissingfuture.domain.model.Knowledge
import ru.bezraznicy.promissingfuture.domain.model.Model
import ru.bezraznicy.promissingfuture.presentation.screen.models.common.ModelType
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

const val KNOWLEDGE_INFO = "knowledge"
const val SHARE_INFO = "share"
const val OWNER = "owner"
const val MODEL_TO_EDIT = "modeltoedit"
const val MODEL_TYPE = "modeltype"
const val MODEL = "model"

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
    class Share(item: Model): Screen(route = "share/${item}") {
        companion object {
            const val EMPTY_ROUTE = "share/{$SHARE_INFO}"
        }
    }

    /**
     * Экран "Создать"
     * Позволяет создавать [Model]
     */
    class Create(ownerId: Long?, modelType: ModelType): Screen(route = "create/${ownerId ?: -1}/$modelType") {
        companion object {
            const val EMPTY_ROUTE = "create/{$OWNER}/{$MODEL_TYPE}"
        }
    }

    /**
     * Экран "Заменить"
     * Позволяет изменить [Model]
     */
    class Replace(model: Model): Screen(
        route = URLEncoder.encode("replace/${Gson().toJson(model)}", StandardCharsets.UTF_8.toString())
    ) {
        companion object {
            const val EMPTY_ROUTE = "replace/{$MODEL}"
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