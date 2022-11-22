package com.jge.newsstack.network

import com.jge.newsstack.models.NetworkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewApi {
    @GET("news")
    suspend fun getListOfNews(@Query("access_key") accessKey:String,
                              @Query("countries") countries:String): Response<NetworkResponse>
}