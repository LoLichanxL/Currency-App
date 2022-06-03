package com.example.data.repositories.currencies

import com.example.domain.common.Result
import com.example.domain.entities.rv.CryptoCurrency
import com.example.domain.entities.rv.currency_details.CurrencyChart
import com.example.domain.entities.CurrencyMainInfo

interface CurrenciesDataSource {
    suspend fun getCurrencies(size:Int):Result<List<CryptoCurrency>>
    suspend fun getCurrencyData(id:String):Result<CurrencyMainInfo>
    suspend fun getChartsInfo(id:String, days:String, interval: String): Result<CurrencyChart>

}