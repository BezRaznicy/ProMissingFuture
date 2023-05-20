package ru.bezraznicy.promissingfuture.data.repository

interface ModelRepository<T> {
    fun insert(catalog: T)

    fun delete(catalog: T)

    fun selectById(id: Long): T

    fun selectAll(): List<T>
}