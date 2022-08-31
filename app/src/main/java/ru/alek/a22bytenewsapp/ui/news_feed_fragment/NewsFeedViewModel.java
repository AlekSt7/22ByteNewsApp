package ru.alek.a22bytenewsapp.ui.news_feed_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.alek.a22bytenewsapp.data.network.repos_impl.NewsRepoImpl;
import ru.alek.a22bytenewsapp.domain.interfaces.repos.INewsRepository;
import ru.alek.a22bytenewsapp.domain.models.NewsModel;

public class NewsFeedViewModel extends ViewModel {

    private final MutableLiveData<List<NewsModel>> newsSetMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> exceptionsLiveData = new MutableLiveData<>();
    private final INewsRepository newsRepository = new NewsRepoImpl();

    public LiveData<List<NewsModel>> getNewsSetMutableLiveData() {
        return newsSetMutableLiveData;
    }

    public LiveData<Throwable> getExceptionsLiveData() {
        return exceptionsLiveData;
    }

    public void getNews(){
        newsRepository.getNewsList(new NewsRepoImpl.NewsCallback() {
            @Override
            public void onDataRecieved(List<NewsModel> newsSet) {
                newsSetMutableLiveData.setValue(newsSet);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}