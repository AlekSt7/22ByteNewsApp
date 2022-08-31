package ru.alek.a22bytenewsapp.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.alek.a22bytenewsapp.BuildConfig;
import ru.alek.a22bytenewsapp.data.network.interfaces.api.ApiManager;

public class RetrofitManager {

    private Retrofit retrofit;

    private ApiManager api;

    public Retrofit getRetrofit() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public ApiManager getApi() {
        if(api == null){
            api = getRetrofit().create(ApiManager.class);
        }
        return api;
    }
}
