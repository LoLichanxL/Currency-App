package com.example.data.network.currencies

import com.example.data.entities.currencies.Currency
import com.example.data.entities.currencies.CurrencyDataResponse
import com.example.data.entities.currencies.CurrencyMarketCharts
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrenciesService {
    @GET("coins/markets")
    suspend fun getCurrencies(@Query("vs_currency") currency: String, @Query("per_page")per_page:Int): Response<List<Currency>>

    @GET("coins/{id}/market_chart")
    suspend fun getCurrencyChart(
        @Path("id") currencyId: String,
        @Query("vs_currency") vs_currency: String,
        @Query("interval") interval: String,
        @Query("days") days: String
    ):Response<CurrencyMarketCharts>

    @GET("coins/{id}")
    suspend fun getCurrencyData(
        @Path("id") currencyId: String,
        @Query("localization")localization:String,
        @Query("tickers")tickers:Boolean,
        @Query("market_data")market_data:Boolean,
        @Query("community_data")community_data:Boolean,
        @Query("developer_data")developer_data:Boolean,
        @Query("sparkline")sparkline:Boolean
    ):Response<CurrencyDataResponse>
}