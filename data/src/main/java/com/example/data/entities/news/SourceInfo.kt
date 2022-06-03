package com.example.data.entities.news

import com.google.gson.annotations.SerializedName

data class SourceInfo(
    @SerializedName("img")
    val img: String,

    @SerializedName("lang")
    val lang: String,

    @SerializedName("name")
    val name: String
)