package ru.bezraznicy.promissingfuture.data.remote

import retrofit2.http.GET
import retrofit2.http.PUT

interface ShareService {
    @PUT("/share")
    suspend fun share(catalog: RemoteModel.Catalog): String

    @GET("/accept")
    suspend fun accept(url: String): RemoteModel.Catalog
}