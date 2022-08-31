package ru.alek.a22bytenewsapp.domain.models;

public class NewsModel {

    private final String title;
    private final String urlToImage;
    private final String author;
    private final Source source;
    private final String description;

    public NewsModel(String title, String previewUrl, String author, Source source, String description){
        this.title = title;
        this.urlToImage = previewUrl;
        this.author = author;
        this.source = source;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String geturlToImage() {
        return urlToImage;
    }

    public String getAuthor() {
        return author;
    }

    public Source getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }
}
