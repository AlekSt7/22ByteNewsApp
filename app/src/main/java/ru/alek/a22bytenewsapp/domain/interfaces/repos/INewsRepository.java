package ru.alek.a22bytenewsapp.domain.interfaces.repos;

import java.util.List;

import ru.alek.a22bytenewsapp.data.network.repos_impl.NewsRepoImpl;
import ru.alek.a22bytenewsapp.domain.models.NewsModel;

public interface INewsRepository {

    void getNewsList(NewsRepoImpl.NewsCallback callback);

}
