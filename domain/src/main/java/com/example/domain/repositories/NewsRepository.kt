package com.example.domain.repositories

import com.example.domain.common.Result
import com.example.domain.entities.rv.FeedNews

interface NewsRepository {
    suspend fun getAllNews():Result<List<FeedNews>>
    suspend fun getNewsByCategory(category:String):Result<List<FeedNews>>
}