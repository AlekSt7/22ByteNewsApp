package ru.alek.a22bytenewsapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.alek.a22bytenewsapp.data.network.interfaces.api.RequestManager
import ru.alek.a22bytenewsapp.utils.MainUtils

object NetworkHandler {

    private val retrofit by lazy { Retrofit.Builder()
        .baseUrl(MainUtils.BaseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build() }

    val api: RequestManager
        get(){
            return retrofit.create(RequestManager::class.java)
        }

}