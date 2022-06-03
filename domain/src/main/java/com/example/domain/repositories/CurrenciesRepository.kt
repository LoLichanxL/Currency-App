package com.example.domain.repositories

import com.example.domain.common.Result
import com.example.domain.entities.rv.CryptoCurrency
import com.example.domain.entities.rv.currency_details.CurrencyChart
import com.example.domain.entities.CurrencyMainInfo


interface CurrenciesRepository {
    suspend fun getAllCurrencies(size:Int):Result<List<CryptoCurrency>>
    suspend fun getCurrencyData(id:String):Result<CurrencyMainInfo>
    suspend fun getCurrencyCharts(id:String, interval:String, days:String):Result<CurrencyChart>
}