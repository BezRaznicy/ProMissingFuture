package ru.bezraznicy.promissingfuture.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.bezraznicy.promissingfuture.domain.model.Catalog

@Dao
interface CatalogDao {
    @Insert
    fun insert(catalog: Catalog)

    @Delete
    fun delete(catalog: Catalog)

    @Query("SELECT * FROM catalog WHERE id = :id")
    fun selectById(id: Long): Catalog

    @Query("SELECT * FROM catalog")
    fun selectAll(): List<Catalog>
}