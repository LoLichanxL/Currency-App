package com.example.data.repositories.currencies

import android.util.Log
import com.example.data.ApiUtils
import com.example.data.mappers.CurrenciesApiResponseMapper
import com.example.data.network.currencies.CurrenciesService
import com.example.domain.entities.rv.CryptoCurrency
import com.example.domain.entities.rv.currency_details.CurrencyChart
import com.example.domain.entities.CurrencyMainInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.domain.common.Result as Result

class CurrenciesDataSourceImpl(
    val service: CurrenciesService,
    val mapper: CurrenciesApiResponseMapper
) : CurrenciesDataSource {
    override suspend fun getCurrencies(size:Int): Result<List<CryptoCurrency>> =
        withContext(Dispatchers.IO) {
            val data = service.getCurrencies(ApiUtils.defaultCurrency, per_page = size)
            if (data.isSuccessful) {
                Result.Success(mapper.apiCurrenciesToCurrencies(data.body()!!))
            } else {
                Result.Error(Exception(data.message()))
            }
        }

    override suspend fun getCurrencyData(id: String): Result<CurrencyMainInfo> = withContext(Dispatchers.IO){
        val data = service.getCurrencyData(currencyId = id, "false", false, false, false, false, false)
        if (data.isSuccessful){
            Result.Success(mapper.currencyDataResponseToCurrencyMainInfo(data.body()!!))
        }else{
            Result.Error(Exception(data.message()))
        }
    }

    override suspend fun getChartsInfo(id: String, days: String, interval:String): Result<CurrencyChart> = withContext(Dispatchers.IO) {
        val data = service.getCurrencyChart(currencyId = id, vs_currency = ApiUtils.defaultCurrency,interval = interval,  days = days)
        if (data.isSuccessful) {
            Result.Success(mapper.currencyChartsResponseToCharts(data.body()!!))
        }else{
            Result.Error(Exception(data.message()))
        }

    }

}
