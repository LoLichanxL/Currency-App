package com.example.data.entities.currencies

import com.google.gson.annotations.SerializedName

data class CurrencyMarketCharts(
    @SerializedName("prices")
    val prices: List<List<Double>>,
    @SerializedName("market_caps")
    val market_caps: List<List<Double>>
)