package com.example.data.entities.news

import com.google.gson.JsonArray
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("Type")
    val Type: Int,
    @SerializedName("Message")
    val Message: String,
    @Expose(serialize = false, deserialize = false)
    @SerializedName("Promoted")
    val Promoted: JsonArray,
    @SerializedName("Data")
    val data: List<News>,
    @SerializedName("HasWarning")
    val HasWarning: Boolean,
    @Expose(serialize = false, deserialize = false)
    @SerializedName("RateLimit")
    val rateLimit: RateLimit
)