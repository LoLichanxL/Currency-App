package com.example.data.entities.currencies

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrencyDataResponse(
    @SerializedName("additional_notices")
    @Expose(serialize = false, deserialize = false)
    val additional_notices: List<Any>,

    @SerializedName("asset_platform_id")
    @Expose(serialize = false, deserialize = false)
    val asset_platform_id: Any,
    @SerializedName("block_time_in_minutes")
    val block_time_in_minutes: Int,
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("coingecko_rank")
    val coingecko_rank: Int,
    @SerializedName("coingecko_score")
    val coingecko_score: Double,
    @SerializedName("community_score")
    val community_score: Double,
    @SerializedName("country_origin")
    val country_origin: String,
    @SerializedName("description")
    val description: Description,
    @SerializedName("developer_score")
    val developer_score: Double,
    @SerializedName("genesis_date")
    val genesis_date: String,
    @SerializedName("hashing_algorithm")
    val hashing_algorithm: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: Image,
    @SerializedName("last_updated")
    val last_updated: String,
    @SerializedName("links")
    @Expose(serialize = false, deserialize = false)
    val links: JsonObject,
    @SerializedName("liquidity_score")
    val liquidity_score: Double,
    @SerializedName("market_cap_rank")
    val market_cap_rank: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("platforms")
    @Expose(serialize = false, deserialize = false)
    val platforms: Platforms,
    @SerializedName("public_interest_score")
    val public_interest_score: Double,
    @SerializedName("public_interest_stats")
    @Expose(serialize = false, deserialize = false)
    val public_interest_stats: PublicInterestStats,
    @SerializedName("public_notice")
    @Expose(serialize = false, deserialize = false)
    val public_notice: Any,
    @SerializedName("sentiment_votes_down_percentage")
    val sentiment_votes_down_percentage: Double,
    @SerializedName("sentiment_votes_up_percentage")
    val sentiment_votes_up_percentage: Double,
    @SerializedName("status_updates")
    @Expose(serialize = false, deserialize = false)
    val status_updates: List<Any>,
    @SerializedName("symbol")
    val symbol: String
)