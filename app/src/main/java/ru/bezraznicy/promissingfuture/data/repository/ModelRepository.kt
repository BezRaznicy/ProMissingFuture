package ru.bezraznicy.promissingfuture.data.repository

interface ModelRepository<T> {
    fun insert(catalog: T)

    fun delete(catalog: T)

    fun selectByOwnerId(id: Long): List<T>

    fun selectAll(): List<T>
}