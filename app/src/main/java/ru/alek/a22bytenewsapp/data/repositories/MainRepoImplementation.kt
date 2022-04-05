package ru.alek.a22bytenewsapp.data.repositories

import ru.alek.a22bytenewsapp.MainApp
import ru.alek.a22bytenewsapp.data.repositories.interfaces.Repository

class MainRepoImplementation: Repository {

    private val api = MainApp.getInstance().api

    override suspend fun getNews() = api.loadNews()

}