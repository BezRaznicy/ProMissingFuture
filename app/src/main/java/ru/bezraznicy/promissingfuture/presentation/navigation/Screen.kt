package ru.bezraznicy.promissingfuture.presentation.navigation

import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.domain.model.Event
import ru.bezraznicy.promissingfuture.domain.model.Knowledge
import ru.bezraznicy.promissingfuture.domain.model.Model


const val CATALOG_INFO = "catalog"
const val EVENT_INFO = "event"
const val KNOWLEDGE_INFO = "knowledge"
const val SHARE_INFO = "share"

/**
 * Определяет ссылку [route] для навигации
 */
sealed class Screen(val route: String) {
    /**
     * Показывает каталоги
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
     * Экран "Каталог"
     * Показывает события, содержащиеся в [Catalog]
     */
    class CatalogDetail(catalog: Catalog): Screen(route = "catalog?$CATALOG_INFO={${catalog.id}}") {
        companion object {
            const val EMPTY_ROUTE = "catalog?$CATALOG_INFO={$CATALOG_INFO}"
        }
    }

    /**
     * Экран "Событие"
     * Показывает знания, содержащиеся в [Event]
     */
    class EventDetail(event: Event): Screen(route = "event?$EVENT_INFO={${event.id}}") {
        companion object {
            const val EMPTY_ROUTE = "event?$EVENT_INFO={$EVENT_INFO}"
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