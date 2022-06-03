package com.example.data.mappers

import com.example.data.entities.news.News
import com.example.domain.entities.rv.FeedNews

class NewsMapper {
    companion object{
        fun newsApiResponseToFeedNews(data:List<News>):List<FeedNews>{
            val result : MutableList<FeedNews> = ArrayList()
            data.forEach {
                result.add(FeedNews(it.title, it.imageurl, it.url, it.source))
            }
            return result.toList()
        }
    }
}