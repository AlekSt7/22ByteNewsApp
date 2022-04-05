package ru.alek.a22bytenewsapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.alek.a22bytenewsapp.data.entities.ServerResponse
import ru.alek.a22bytenewsapp.data.repositories.MainRepoImplementation

class MainNewsActivityViewModel(): ViewModel() {
    private val repo: MainRepoImplementation = MainRepoImplementation()
    private val mutableLiveData: MutableLiveData<ServerResponse> = MutableLiveData()
    val liveData = mutableLiveData as LiveData<ServerResponse>

    suspend fun fetchNews() {
        val response = repo.getNews()
        if(response.isSuccessful){
            if(response.code() == 404){
                throw Exception("404")
            }
            mutableLiveData.postValue(response.body())
        }else{
            throw Exception("Ошибка при загрузке данных")
        }
    }

}