package ru.alek.a22bytenewsapp.data.network.repos_impl;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.alek.a22bytenewsapp.BuildConfig;
import ru.alek.a22bytenewsapp.MainApp;
import ru.alek.a22bytenewsapp.data.network.interfaces.api.ApiManager;
import ru.alek.a22bytenewsapp.domain.interfaces.repos.INewsRepository;
import ru.alek.a22bytenewsapp.domain.models.NewsModel;
import ru.alek.a22bytenewsapp.domain.models.ServerResponse;
import ru.alek.a22bytenewsapp.exceptions.ApiException;

public class NewsRepoImpl implements INewsRepository {

    private final ApiManager api;

    public NewsRepoImpl(){
        api = MainApp.getInstance().getApi();
    }

    public interface NewsCallback{
        void onDataRecieved(List<NewsModel> newsSet);
        void onFailure(Throwable t);
    }

    @Override
    public void getNewsList(NewsCallback callback) {
            api.loadNews(
                    BuildConfig.API_KEY,
                    "ru"
            ).enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                    ServerResponse body = response.body();
                    if(response.isSuccessful() && body != null){
                        if(body.getMessage() == null || body.getCode() == null){
                            callback.onDataRecieved(body.getArticles());
                        }else{
                            callback.onFailure(new ApiException(body));
                        }
                    }else{
                            callback.onFailure(new Throwable(response.message()));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {
                    callback.onFailure(t);
                }
            });
    }

}
