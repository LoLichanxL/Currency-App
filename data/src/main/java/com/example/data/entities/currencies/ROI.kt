package com.example.data.entities.currencies

import com.google.gson.annotations.SerializedName

data class ROI(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("percentage")
    val percentage: Double,
    @SerializedName("times")
    val times: Double
)