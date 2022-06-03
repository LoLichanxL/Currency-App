package com.example.data.network.news

import com.example.data.entities.news.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("v2/news/")
    suspend fun getAllNews(@Query("api_key") apiKey:String, @Query("lang") lang:String):Response<NewsResponse>
    @GET("v2/news/")
    suspend fun getNewsById(@Query("api_key") apiKey:String, @Query("lang") lang:String, @Query("categories")category: String):Response<NewsResponse>
}