package ru.alek.a22bytenewsapp.data.network.interfaces.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.alek.a22bytenewsapp.domain.models.ServerResponse;

public interface ApiManager {

    @GET("top-headlines/")
    Call<ServerResponse> loadNews(@Query("apiKey") String apiKey,
                                         @Query("country") String country);

}
