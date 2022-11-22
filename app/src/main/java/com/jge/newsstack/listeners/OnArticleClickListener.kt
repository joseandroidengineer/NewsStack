package com.jge.newsstack.listeners

import com.jge.newsstack.models.Article

interface OnArticleClickListener {
    fun onArticleClick(article: Article)
}