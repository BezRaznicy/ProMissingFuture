package ru.bezraznicy.promissingfuture.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Catalog(
    name: String,
    id: Long? = null
): Model(name, id)