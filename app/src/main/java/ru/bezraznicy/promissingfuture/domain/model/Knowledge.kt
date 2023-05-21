package ru.bezraznicy.promissingfuture.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(
        entity = Event::class,
        parentColumns = ["id"],
        childColumns = ["id_Event"],
        onDelete = ForeignKey.CASCADE
    )
])
class Knowledge(
    name: String,
    val description: String?,

    id: Long? = null,
    @ColumnInfo(name = "id_Event") val idEvent: Long? = null
): Model(name, id)