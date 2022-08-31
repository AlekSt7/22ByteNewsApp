package ru.alek.a22bytenewsapp.ui.news_feed_fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

import ru.alek.a22bytenewsapp.R;
import ru.alek.a22bytenewsapp.domain.models.NewsModel;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsModel> newsSet;

    public void setNews(List<NewsModel> newsSet){
        this.newsSet = newsSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(newsSet.get(position));
    }

    @Override
    public int getItemCount() {
        if(newsSet == null){
            return 0;
        }else{
            return newsSet.size();
        }
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        final ImageView preview;
        final TextView title, description, author, source;
        NewsViewHolder(View view){
            super(view);
            preview = view.findViewById(R.id.preview);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            author = view.findViewById(R.id.author);
            source = view.findViewById(R.id.source);
        }

        public void bind(NewsModel news){
            title.setText(news.getTitle());
            description.setText(news.getDescription());
            author.setText(news.getAuthor());
            source.setText(news.getSource().getName());

            Glide.with(itemView.getContext())
                    .load(news.geturlToImage())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(preview);
        }

    }

}
