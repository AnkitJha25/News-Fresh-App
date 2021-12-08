package com.example.newsfresh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val listener:OnNewsClick) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val items = ArrayList<News>()

    class NewsViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

        val titleView : TextView = itemView.findViewById(R.id.title)
        val author : TextView = itemView.findViewById(R.id.author)
        val image : ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewsViewHolder(view)

        view.setOnClickListener{
            listener.onClicked(items[viewHolder.absoluteAdapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentNews = items[position]
        holder.titleView.text = currentNews.title
        holder.author.text = currentNews.author

        // Access image from image url to display using "GLIDE"

        Glide.with(holder.itemView.context).load(currentNews.imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateData(newData:ArrayList<News>){
        items.clear()
        items.addAll(newData)

        notifyDataSetChanged()
    }
}

// Open the news in a chrome tab when news article is clicked
interface OnNewsClick{
    fun onClicked(news: News)
}