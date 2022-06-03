package com.example.data.repositories.news

import com.example.domain.common.Result
import com.example.domain.entities.rv.FeedNews

interface NewsDataSource {
    suspend fun getNews():Result<List<FeedNews>>
    suspend fun getNewsByCategory(category:String):Result<List<FeedNews>>
}