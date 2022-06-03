package com.example.data.repositories.currencies

import com.example.domain.common.Result
import com.example.domain.entities.rv.CryptoCurrency
import com.example.domain.entities.rv.currency_details.CurrencyChart
import com.example.domain.entities.CurrencyMainInfo
import com.example.domain.repositories.CurrenciesRepository

class CurrenciesRepositoryImpl(val currenciesDataSource: CurrenciesDataSource) :  CurrenciesRepository{
    override suspend fun getAllCurrencies(size:Int): Result<List<CryptoCurrency>> {
        return currenciesDataSource.getCurrencies(size)
    }

    override suspend fun getCurrencyData(id: String): Result<CurrencyMainInfo> {
        return currenciesDataSource.getCurrencyData(id)
    }

    override suspend fun getCurrencyCharts(id: String, interval: String, days:String): Result<CurrencyChart> {
        return currenciesDataSource.getChartsInfo(id = id, days = days,interval = interval)
    }


}