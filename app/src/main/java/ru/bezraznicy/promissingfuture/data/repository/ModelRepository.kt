package ru.bezraznicy.promissingfuture.data.repository

import ru.bezraznicy.promissingfuture.domain.model.Model

interface ModelRepository<T: Model> {
    fun insert(model: T)

    fun delete(model: T)

    fun selectByOwnerId(id: Long): List<T>

    fun selectAll(): List<T>
}