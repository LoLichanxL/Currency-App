package com.example.data.mappers

import com.example.data.entities.currencies.Currency
import com.example.data.entities.currencies.CurrencyDataResponse
import com.example.data.entities.currencies.CurrencyMarketCharts
import com.example.domain.entities.rv.CryptoCurrency
import com.example.domain.entities.rv.currency_details.CurrencyChart
import com.example.domain.entities.CurrencyMainInfo

class CurrenciesApiResponseMapper {
    fun apiCurrenciesToCurrencies (data:List<Currency>):List<CryptoCurrency>{
        val result : MutableList<CryptoCurrency> = ArrayList()
        data.forEach {
            result.add(
                CryptoCurrency(
                id = it.id, name = it.name, symbol = it.symbol, priceUsd = it.current_price,
                priceChange24h = it.price_change_24h, percentChange_24h = it.price_change_percentage_24h, it.image)
            )
        }
        return result.toList()
    }
    fun currencyDataResponseToCurrencyMainInfo(currency:CurrencyDataResponse):CurrencyMainInfo{
        return CurrencyMainInfo(currency.symbol, currency.image.small, currency.name)
    }

    fun currencyChartsResponseToCharts(charts: CurrencyMarketCharts): CurrencyChart {
        return CurrencyChart(charts.prices, charts.market_caps)
    }
}