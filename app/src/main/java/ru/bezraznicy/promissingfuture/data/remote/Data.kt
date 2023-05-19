package ru.bezraznicy.promissingfuture.data.remote

import com.google.gson.Gson

// От сервера получаем целый каталог
sealed class RemoteModel {

    data class Catalog(val name: String, val inside: List<Event>) : RemoteModel()
    data class Event(
        val name: String,
        val description: String?,
        val time: String?,
        val inside: List<Knowledge>
    ) : RemoteModel()

    data class Knowledge(val name: String, val description: String?, val attachment: ByteArray?) :
        RemoteModel()

    override fun toString(): String {
        return Gson().toJson(this)
    }
}