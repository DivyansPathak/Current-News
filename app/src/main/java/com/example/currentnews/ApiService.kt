package com.example.currentnews

import NewsApp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    fun getHeadLine(
        @Query("country") country : String,
        @Query("category") category : String,
        @Query("apiKey") apiKey : String,
        @Query("language") language : String = "en"

    ) : Call<NewsApp>
}