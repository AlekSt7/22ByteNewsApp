package ru.alek.a22bytenewsapp.data.repositories.interfaces

import retrofit2.Response
import ru.alek.a22bytenewsapp.data.entities.ServerResponse

interface Repository {

    suspend fun getNews(): Response<ServerResponse>

}