package ru.bezraznicy.promissingfuture

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test
import ru.bezraznicy.promissingfuture.domain.model.Catalog
import ru.bezraznicy.promissingfuture.domain.model.Event
import ru.bezraznicy.promissingfuture.domain.model.Knowledge

class ModelToJsonTest {
    @Test
    fun catalogToJson() {
        val catalog = Catalog(1, "Работа")
        Assert.assertEquals(
            "{\"id\":1,\"name\":\"Работа\"}",
            Gson().toJson(catalog)
        )
    }

    @Test
    fun eventToJson() {
        val event = Event(1, 1, "Пара разработки мобильных приложений", "Конечная цель - итоговый проект", "* * * * * 10")
        Assert.assertEquals(
            "{\"id\":1,\"idCatalog\":1,\"description\":\"Конечная цель - итоговый проект\",\"time\":\"* * * * * 10\",\"name\":\"Пара разработки мобильных приложений\"}",
            Gson().toJson(event)
        )
    }

    @Test
    fun knowledgeToJson() {
        val knowledge = Knowledge(1, 1, "Jetpack Compose UI tutorial", "https://developer.android.com", byteArrayOf(0,5,1))
        Assert.assertEquals(
            "{\"id\":1,\"idEvent\":1,\"description\":\"https://developer.android.com\",\"attachment\":[0,5,1],\"name\":\"Jetpack Compose UI tutorial\"}",
            Gson().toJson(knowledge)
        )
    }
}