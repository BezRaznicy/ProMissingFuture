package ru.bezraznicy.promissingfuture.domain.model

import androidx.room.PrimaryKey
import com.google.gson.Gson
import ru.bezraznicy.promissingfuture.presentation.common.ModelType

sealed class Model(
    val name: String,
    @PrimaryKey(autoGenerate = true) val id: Long? = null
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}

class ModelBuilder(
    var name: String,
    var description: String = "",
    var time: String = "",
    var id: Long? = null
) {
    fun build(modelType: ModelType, owner: Long? = null): Model = when (modelType) {
        ModelType.KNOWLEDGE -> Knowledge(
            name = name,
            description = description.ifEmpty { null },
            idEvent = owner,
            id = id
        )
        ModelType.EVENT -> Event(
            name = name,
            description = description.ifEmpty { null },
            time = time.ifEmpty { null },
            idCatalog = owner,
            id = id
        )
        ModelType.CATALOG -> Catalog(
            name = name,
            id = id
        )
    }
}