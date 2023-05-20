package ru.bezraznicy.promissingfuture.data.repository

interface RepositoryProvider {
    val catalogRepository: CatalogRepository
    val eventRepository: EventRepository
    val knowledgeRepository: KnowledgeRepository
}