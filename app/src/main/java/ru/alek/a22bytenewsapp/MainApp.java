package ru.alek.a22bytenewsapp;

import android.app.Application;

import ru.alek.a22bytenewsapp.data.network.RetrofitManager;
import ru.alek.a22bytenewsapp.data.network.interfaces.api.ApiManager;

public class MainApp extends Application {

    private static MainApp instance;
    private final ApiManager api;

    private MainApp(){
        RetrofitManager retrofitManager = new RetrofitManager();
        api = retrofitManager.getApi();
    }

    public static MainApp getInstance() {
        if(instance == null)  {
            synchronized (MainApp.class){
                if(instance == null){
                    instance = new MainApp();
                }
            }
        }
        return instance;
    }

    public ApiManager getApi() {
        return api;
    }
}
