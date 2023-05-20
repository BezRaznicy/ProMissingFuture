package ru.bezraznicy.promissingfuture.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.bezraznicy.promissingfuture.domain.model.Knowledge

@Dao
interface KnowledgeDao {
    @Insert
    fun insert(knowledge: Knowledge)

    @Delete
    fun delete(knowledge: Knowledge)

    @Query("SELECT * FROM knowledge WHERE id_Event = :id")
    fun selectByOwnerId(id: Long): List<Knowledge>

    @Query("SELECT * FROM knowledge")
    fun selectAll(): List<Knowledge>
}