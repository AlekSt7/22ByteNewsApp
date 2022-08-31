package ru.alek.a22bytenewsapp.ui.news_feed_fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.alek.a22bytenewsapp.R;
import ru.alek.a22bytenewsapp.domain.models.NewsModel;

public class NewsFeedFragment extends Fragment {

    private NewsFeedViewModel mViewModel;
    private RecyclerView newsRecyclerView;
    private NewsAdapter newsAdapter;

    public static NewsFeedFragment newInstance() {
        return new NewsFeedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.news_feed_fragment, container, false);
        newsRecyclerView = root.findViewById(R.id.news);
        newsAdapter = new NewsAdapter();
        newsRecyclerView.setAdapter(newsAdapter);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NewsFeedViewModel.class);
        mViewModel.getNews();
        observeData();

    }

    private void observeData(){
        mViewModel.getNewsSetMutableLiveData().observe(getViewLifecycleOwner(), newsModel -> newsAdapter.setNews(newsModel));
    }

}