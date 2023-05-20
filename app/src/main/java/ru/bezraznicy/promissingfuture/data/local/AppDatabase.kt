package ru.bezraznicy.promissingfuture.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.domain.model.Event
import ru.bezraznicy.promissingfuture.domain.model.Knowledge


@Database(entities = [Knowledge::class, Event::class, Catalog::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun knowledgeDao(): KnowledgeDao
    abstract fun eventDao(): EventDao
    abstract fun catalogDao(): CatalogDao
}