package ru.bezraznicy.promissingfuture.common

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.bezraznicy.promissingfuture.data.remote.ShareService

object PromissingFuture: Application() {
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://tidinari.ru") // Replace with your backend base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val shareService by lazy {
        retrofit.create(ShareService::class.java)
    }
}