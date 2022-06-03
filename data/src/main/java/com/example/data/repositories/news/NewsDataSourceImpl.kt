package com.example.data.repositories.news

import android.util.Log
import com.example.data.ApiUtils
import com.example.data.mappers.NewsMapper
import com.example.data.network.news.NewsService
import com.example.domain.entities.rv.FeedNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.domain.common.Result
import java.lang.Exception

class NewsDataSourceImpl(val service: NewsService, val mapper: NewsMapper) : NewsDataSource {
    override suspend fun getNews(): Result<List<FeedNews>> = withContext(Dispatchers.IO){
        val result = service.getAllNews(ApiUtils.newsApiKey, ApiUtils.newsDefaultLang)
        Log.d("result", result.body().toString())
        if(result.isSuccessful){
            return@withContext Result.Success<List<FeedNews>>(NewsMapper.newsApiResponseToFeedNews(result.body()!!.data))
        }else{
            return@withContext Result.Error(Exception(result.message()))
        }
    }

    override suspend fun getNewsByCategory(category: String): Result<List<FeedNews>> = withContext(Dispatchers.IO) {
        val result = service.getNewsById(ApiUtils.newsApiKey, ApiUtils.newsDefaultLang, category)
        if(result.isSuccessful){
            return@withContext Result.Success(NewsMapper.newsApiResponseToFeedNews(result.body()!!.data))
        }else{
            return@withContext Result.Error(Exception(result.message()))
        }
    }
}