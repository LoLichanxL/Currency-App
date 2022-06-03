package com.example.data.repositories.news

import com.example.domain.common.Result
import com.example.domain.entities.rv.FeedNews
import com.example.domain.repositories.NewsRepository

class NewsRepositoryImpl(val newsDataSource: NewsDataSource) : NewsRepository {
    override suspend fun getAllNews(): Result<List<FeedNews>> {
        return newsDataSource.getNews()
    }

    override suspend fun getNewsByCategory(category: String): Result<List<FeedNews>> {
        return newsDataSource.getNewsByCategory(category)
    }
}