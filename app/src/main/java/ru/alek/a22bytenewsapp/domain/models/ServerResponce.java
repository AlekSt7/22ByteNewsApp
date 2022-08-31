package ru.alek.a22bytenewsapp.domain.models;

import java.util.List;

public class ServerResponce {

    private final String status;
    private final String code;
    private final String message;
    private final String totalResults;
    private final List<NewsModel> articles;

    public ServerResponce(String totalResults, List<NewsModel> articles, String status, String code, String message){
        this.totalResults = totalResults;
        this.articles = articles;
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public List<NewsModel> getArticles() {
        return articles;
    }

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
