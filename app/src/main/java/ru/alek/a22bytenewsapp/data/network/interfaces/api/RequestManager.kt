package ru.alek.a22bytenewsapp.data.network.interfaces.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.alek.a22bytenewsapp.data.entities.ServerResponse
import ru.alek.a22bytenewsapp.utils.MainUtils

interface RequestManager {

    @GET("top-headlines/")
    suspend fun loadNews(@Query("apiKey") apiKey: String = MainUtils.APIKey,
                         @Query("country") country: String = MainUtils.DefaultCountry): Response<ServerResponse>

}