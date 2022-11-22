package com.jge.newsstack.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jge.newsstack.R
import com.jge.newsstack.listeners.OnArticleClickListener
import com.jge.newsstack.models.Article

class ArticleAdapter(
    var listOfArticles:List<Article>,
    val onArticleClickListener: OnArticleClickListener): RecyclerView.Adapter<ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.article_item,parent, false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = listOfArticles[position]
        holder.bindViews(article)
        holder.itemView.setOnClickListener{
            onArticleClickListener.onArticleClick(article)
        }
    }

    override fun getItemCount(): Int {
        if(listOfArticles.isNotEmpty()){
            return listOfArticles.size
        }
        return 0
    }

    fun setArticles(articles: List<Article>) {
        listOfArticles = articles
        notifyDataSetChanged()
    }
}