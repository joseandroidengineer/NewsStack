package com.jge.newsstack.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.jge.newsstack.R
import com.jge.newsstack.models.Article

class ArticleViewHolder(itemView: View):ViewHolder(itemView) {

    val textViewTitle:TextView = itemView.findViewById(R.id.title_tv)
    val textViewAuthor:TextView = itemView.findViewById(R.id.author_tv)
    val imageViewThumbnail:ImageView = itemView.findViewById(R.id.thumbnail_iv)

    fun bindViews(article: Article){
        textViewAuthor.text = article.author
        textViewTitle.text = article.title
        Glide
            .with(itemView)
            .load(article.image)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageViewThumbnail)
    }
}