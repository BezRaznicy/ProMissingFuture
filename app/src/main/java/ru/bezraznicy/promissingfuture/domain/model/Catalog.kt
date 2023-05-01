package ru.bezraznicy.promissingfuture.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Catalog(
    @PrimaryKey val id: Long,

    val name: String
): Model()