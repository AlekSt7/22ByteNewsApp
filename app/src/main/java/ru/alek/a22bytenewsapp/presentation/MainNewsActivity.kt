package ru.alek.a22bytenewsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Dispatcher
import ru.alek.a22bytenewsapp.databinding.NewsActivityMainBinding
import ru.alek.a22bytenewsapp.presentation.adpters.MainNewsAdapter

class MainNewsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = NewsActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[MainNewsActivityViewModel::class.java]

        loadData(viewModel, binding)

        viewModel.liveData.observe(this) {
            if(it != null){
                binding.progress.visibility = View.GONE
                binding.errorWrapper.visibility = View.GONE
                binding.newsList.adapter = MainNewsAdapter(it.articles)
            }
        }

        binding.apply {
            retryButton.setOnClickListener {
                    errorWrapper.visibility = View.GONE
                    loadData(viewModel, binding)
                }
            }
    }

    fun loadData(viewModel: MainNewsActivityViewModel, binding: NewsActivityMainBinding){
        binding.progress.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.Main) {
            try {
                viewModel.fetchNews()
            } catch (e: Exception) {
                binding.progress.visibility = View.GONE
                binding.errorWrapper.visibility = View.VISIBLE
            }
        }
    }
    
    fun setUiState(binding: NewsActivityMainBinding){

    }

}