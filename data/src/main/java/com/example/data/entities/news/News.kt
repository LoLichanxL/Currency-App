package com.example.data.entities.news

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("body")
    val body: String,
    @SerializedName("categories")
    val categories: String,
    @SerializedName("downvotes")
    val downvotes: String,
    @SerializedName("guid")
    val guid: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("imageurl")
    val imageurl: String,
    @SerializedName("lang")
    val lang: String,
    @SerializedName("published_on")
    val published_on: Int,
    @SerializedName("source")
    val source: String,
    @SerializedName("source_info")
    val source_info: SourceInfo,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("upvotes")
    val upvotes: String,
    @SerializedName("url")
    val url: String
)