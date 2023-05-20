package ru.bezraznicy.promissingfuture.data.repository

import ru.bezraznicy.promissingfuture.data.local.EventDao
import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.domain.model.Event

class EventRepository(private val eventDao: EventDao): ModelRepository<Event> {
    override fun insert(event: Event) {
        eventDao.insert(event)
    }

    override fun delete(event: Event) {
        eventDao.delete(event)
    }

    override fun selectById(id: Long): Event {
        return eventDao.selectById(id)
    }

    override fun selectAll(): List<Event> {
        return eventDao.selectAll()
    }
}