package ru.bezraznicy.promissingfuture.domain.model

import com.google.gson.Gson

sealed class Model {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}