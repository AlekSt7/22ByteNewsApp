package ru.alek.a22bytenewsapp.ui.news_feed_fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.stanford.simplebouncyrecycler.views.SimpleBouncyRecyclerView;

import ru.alek.a22bytenewsapp.R;
import ru.alek.a22bytenewsapp.exceptions.ApiException;

public class NewsFeedFragment extends Fragment {

    private NewsFeedViewModel mViewModel;
    private SimpleBouncyRecyclerView newsRecyclerView;
    private NewsAdapter newsAdapter;
    private LinearLayout errorWrapper;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button retryButon;
    private LottieAnimationView loader;

    private static final String TAG = "NewsFeedFragment";

    enum UiConfig{
        IS_LOADING,
        IS_LOADED,
        ERROR
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.news_feed_fragment, container, false);
        newsRecyclerView = root.findViewById(R.id.news);
        errorWrapper = root.findViewById(R.id.error_wrapper);
        swipeRefreshLayout = root.findViewById(R.id.data_wrapper);
        retryButon = root.findViewById(R.id.retry_button);
        loader = root.findViewById(R.id.loader);
        newsAdapter = new NewsAdapter();
        newsRecyclerView.setAdapter(newsAdapter);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NewsFeedViewModel.class);
        swipeRefreshLayout.setOnRefreshListener(this::loadData);
        retryButon.setOnClickListener(v -> loadData());
        loadData();
        observeData();
    }

    private void loadData(){
        setUI(UiConfig.IS_LOADING);
        mViewModel.getNews();
    }

    private void observeData(){
        mViewModel.getNewsSetMutableLiveData().observe(getViewLifecycleOwner(), newsModel -> {
            setUI(UiConfig.IS_LOADED);
            newsAdapter.setNews(newsModel);
        });
        mViewModel.getExceptionsLiveData().observe(getViewLifecycleOwner(), exception -> {
            if(exception instanceof ApiException){
                Log.e(TAG, ((ApiException) exception).getCode() + " " + exception.getMessage()
                        + " " + ((ApiException) exception).getErrorMessage());
            }if(exception instanceof ApiException){
                setUI(UiConfig.ERROR);
            } else{
                Log.e(TAG, exception.getMessage());
            }
            setUI(UiConfig.ERROR);
        });
    }

    private void setUI(UiConfig config){
        switch (config){
            case IS_LOADED:
                errorWrapper.setVisibility(View.GONE);
                loader.setVisibility(View.GONE);
                swipeRefreshLayout.setVisibility(View.VISIBLE);
                newsRecyclerView.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);
                break;
            case IS_LOADING:
                errorWrapper.setVisibility(View.GONE);
                swipeRefreshLayout.setVisibility(View.GONE);
                newsRecyclerView.setVisibility(View.GONE);
                loader.setVisibility(View.VISIBLE);
                break;
            case ERROR:
                loader.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                newsRecyclerView.setVisibility(View.GONE);
                swipeRefreshLayout.setVisibility(View.VISIBLE);
                errorWrapper.setVisibility(View.VISIBLE);
                break;
        }
    }

}