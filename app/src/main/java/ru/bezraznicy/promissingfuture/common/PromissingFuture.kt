package ru.bezraznicy.promissingfuture.common

import android.app.Application
import androidx.room.Room
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.bezraznicy.promissingfuture.data.local.AppDatabase
import ru.bezraznicy.promissingfuture.data.remote.ShareService
import ru.bezraznicy.promissingfuture.data.repository.CatalogRepository
import ru.bezraznicy.promissingfuture.data.repository.EventRepository
import ru.bezraznicy.promissingfuture.data.repository.KnowledgeRepository
import ru.bezraznicy.promissingfuture.data.repository.RepositoryProvider

class PromissingFuture: Application(), RepositoryProvider {
    // DATA - REMOTE
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://tidinari.ru") // Replace with your backend base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val shareService by lazy {
        retrofit.create(ShareService::class.java)
    }

    // DATA - LOCAL
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "futuredb"
        ).build()
    }

    override val catalogRepository: CatalogRepository by lazy { CatalogRepository(database.catalogDao()) }
    override val eventRepository: EventRepository by lazy { EventRepository(database.eventDao()) }
    override val knowledgeRepository: KnowledgeRepository by lazy { KnowledgeRepository(database.knowledgeDao()) }
}