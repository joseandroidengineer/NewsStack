package com.jge.newsstack.repo

import com.jge.newsstack.APIKEYHOLDER
import com.jge.newsstack.models.NetworkResponse
import com.jge.newsstack.network.NewApi
import retrofit2.Response
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val newsApi: NewApi
    ):ArticleRepository {
    override suspend fun getNewsArticles(): Response<NetworkResponse> {
        return newsApi.getListOfNews(APIKEYHOLDER.API_KEY, "us")
    }

}