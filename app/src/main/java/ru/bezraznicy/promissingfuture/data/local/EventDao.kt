package ru.bezraznicy.promissingfuture.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.bezraznicy.promissingfuture.domain.model.Event

@Dao
interface EventDao {
    @Insert
    fun insert(event: Event)

    @Delete
    fun delete(event: Event)

    @Query("SELECT * FROM event WHERE id_Catalog = :id")
    fun selectByOwnerId(id: Long): List<Event>

    @Query("SELECT * FROM event")
    fun selectAll(): List<Event>
}
