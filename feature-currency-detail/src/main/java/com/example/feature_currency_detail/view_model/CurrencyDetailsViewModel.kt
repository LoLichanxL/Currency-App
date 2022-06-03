package com.example.feature_currency_detail.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.repositories.currencies.CurrenciesRepositoryImpl
import com.example.domain.common.Result
import com.example.domain.entities.rv.currency_details.CurrencyChart
import com.example.domain.entities.CurrencyMainInfo
import com.example.domain.entities.rv.FeedNews
import com.example.domain.repositories.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyDetailsViewModel(private val currenciesRepositoryImpl: CurrenciesRepositoryImpl, private val newsRepository: NewsRepository) :
    ViewModel() {
    val coinData : MutableLiveData<CurrencyMainInfo> = MutableLiveData()
    private val _coinChartData: MutableLiveData<CurrencyChart> = MutableLiveData()
    private val _newsData: MutableLiveData<List<FeedNews>> = MutableLiveData()
    val newsData: LiveData<List<FeedNews>> = _newsData
    val coinChartData: LiveData<CurrencyChart> = _coinChartData
    val dataState:MutableLiveData<Boolean> = MutableLiveData(false)
    fun fetchCurrencyData(currencyId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            when(val result = currenciesRepositoryImpl.getCurrencyData(currencyId)){
                is Result.Success -> {
                    coinData.postValue(result.data!!)
                    fetchNews(result.data!!.slug)
                    if(coinChartData.value != null && newsData.value != null){
                        dataState.postValue(true)
                    }
                }
            }
        }
    }

    fun fetchChartData(id:String, days:String, interval:String){
        CoroutineScope(Dispatchers.IO).launch {
            when(val result = currenciesRepositoryImpl.getCurrencyCharts(id = id, interval = interval, days = days)){
                is Result.Success -> {
                    _coinChartData.postValue(result.data!!)
                    if(coinData.value != null && newsData.value != null){
                        dataState.postValue(true)
                    }
                }
            }
        }
    }


    private fun fetchNews(category: String){
        CoroutineScope(Dispatchers.IO).launch {
            when(val result = newsRepository.getNewsByCategory(category)){
                is Result.Success -> {
                    if (result.data!!.size > 5){
                        _newsData.postValue(result.data!!.subList(0, 5))
                    }else{
                        _newsData.postValue(result.data!!)
                    }
                    if (coinData.value != null && coinChartData.value != null){
                        dataState.postValue(true)
                    }
                }
            }
        }
    }
}