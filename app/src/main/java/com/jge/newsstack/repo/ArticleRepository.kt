package com.jge.newsstack.repo

import com.jge.newsstack.models.NetworkResponse
import retrofit2.Response

interface ArticleRepository {
    suspend fun getNewsArticles(): Response<NetworkResponse>
}