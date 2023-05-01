package ru.bezraznicy.promissingfuture.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(
        entity = Catalog::class,
        parentColumns = ["id"],
        childColumns = ["id_Catalog"],
        onDelete = ForeignKey.CASCADE
    )
])
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "id_Catalog") val idCatalog: Long,

    val name: String,
    val description: String?,
    val time: String?
): Model()