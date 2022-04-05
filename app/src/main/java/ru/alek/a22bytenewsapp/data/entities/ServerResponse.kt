package ru.alek.a22bytenewsapp.data.entities

/**
 *
 * Модель ответа с сервера
 *
 * */
data class ServerResponse(
    val totalResults: String,
    val articles: News
) {

    data class News(
        val title: String,
        val urlToImage: String?,
        val author: String?,
        val source: Source
    )

    data class Source(
        val name: String
    )

}