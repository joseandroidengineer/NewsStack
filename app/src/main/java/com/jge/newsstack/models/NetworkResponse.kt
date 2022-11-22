package com.jge.newsstack.models

import com.google.gson.annotations.SerializedName

class NetworkResponse(@SerializedName("pagination") val pagination: Pagination,
                      @SerializedName("data")       val listOfArticles: List<Article>) {
}