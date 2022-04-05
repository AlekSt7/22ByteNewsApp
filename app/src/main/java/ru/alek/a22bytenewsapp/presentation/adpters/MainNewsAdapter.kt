package ru.alek.a22bytenewsapp.presentation.adpters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ru.alek.a22bytenewsapp.R
import ru.alek.a22bytenewsapp.data.entities.ServerResponse
import ru.alek.a22bytenewsapp.databinding.NewsItemBinding

class MainNewsAdapter(private val news: List<ServerResponse.News>): RecyclerView.Adapter<MainNewsAdapter.Item>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Item(NewsItemBinding.inflate(
        LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount() = news.size

    class Item(private val binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(news: ServerResponse.News){

            val res = binding.root.context.resources

            binding.apply {
                header.text = news.title
                if(news.author != null){
                    val textAuthor = "${res.getText(R.string.author)} + ${news.author}"
                    author.text = textAuthor
                }else{
                    author.visibility = View.GONE
                }
                val textSource = "${res.getText(R.string.source)} + ${news.source.name}"
                source.text = textSource

                Glide.with(itemView.context)
                    .load(news.urlToImage)
                    .centerCrop()
                    .error(R.drawable.error_image_back)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(preview)

            }
        }
    }

}