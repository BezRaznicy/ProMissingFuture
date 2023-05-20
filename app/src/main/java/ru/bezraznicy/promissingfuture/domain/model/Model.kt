package ru.bezraznicy.promissingfuture.domain.model

import androidx.room.PrimaryKey
import com.google.gson.Gson

sealed class Model(
    val name: String,
    @PrimaryKey(autoGenerate = true) val id: Long? = null
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}