package ru.bezraznicy.promissingfuture.data.repository

import ru.bezraznicy.promissingfuture.data.local.KnowledgeDao
import ru.bezraznicy.promissingfuture.domain.model.Event
import ru.bezraznicy.promissingfuture.domain.model.Knowledge

class KnowledgeRepository(private val knowledgeDao: KnowledgeDao): ModelRepository<Knowledge> {
    override fun insert(knowledge: Knowledge) {
        knowledgeDao.insert(knowledge)
    }

    override fun delete(knowledge: Knowledge) {
        knowledgeDao.delete(knowledge)
    }

    override fun selectByOwnerId(id: Long): List<Knowledge> {
        return knowledgeDao.selectByOwnerId(id)
    }

    override fun selectAll(): List<Knowledge> {
        return knowledgeDao.selectAll()
    }
}