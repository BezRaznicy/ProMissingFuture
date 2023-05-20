package ru.bezraznicy.promissingfuture.data.repository

import ru.bezraznicy.promissingfuture.data.local.CatalogDao
import ru.bezraznicy.promissingfuture.domain.model.Catalog

class CatalogRepository(private val catalogDao: CatalogDao): ModelRepository<Catalog> {
    override fun insert(catalog: Catalog) {
        catalogDao.insert(catalog)
    }

    override fun delete(catalog: Catalog) {
        catalogDao.delete(catalog)
    }

    override fun selectByOwnerId(id: Long): List<Catalog> {
        throw UnsupportedOperationException("Catalog is fully free: does not have an owner")
    }

    override fun selectAll(): List<Catalog> {
        return catalogDao.selectAll()
    }
}