package ru.bezraznicy.promissingfuture.presentation.navigation

import ru.bezraznicy.promissingfuture.domain.model.Knowledge
import ru.bezraznicy.promissingfuture.domain.model.Model

const val KNOWLEDGE_INFO = "knowledge"
const val SHARE_INFO = "share"

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
     * Экран "Знание"
     * Показывает детальную информацию о [Knowledge]
     */
    class KnowledgeDetail(knowledge: Knowledge): Screen(route = "knowledge?$KNOWLEDGE_INFO={${knowledge}}") {
        companion object {
            const val EMPTY_ROUTE = "knowledge?$KNOWLEDGE_INFO={$KNOWLEDGE_INFO}"
        }
    }
}