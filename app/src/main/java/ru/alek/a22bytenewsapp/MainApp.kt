package ru.alek.a22bytenewsapp

import android.app.Application
import ru.alek.a22bytenewsapp.data.network.NetworkHandler

object MainApp: Application() {

    private lateinit var instance: MainApp

    fun getInstance(): MainApp {
        instance = MainApp
        return instance
    }

    val api = NetworkHandler.api

}