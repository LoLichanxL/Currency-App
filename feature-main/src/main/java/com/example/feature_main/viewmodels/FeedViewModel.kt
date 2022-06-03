package com.example.feature_main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.common.Result
import com.example.domain.entities.rv.CryptoCurrency
import com.example.domain.entities.rv.FeedNews
import com.example.domain.repositories.CurrenciesRepository
import com.example.domain.repositories.NewsRepository
import com.example.utils.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedViewModel(val currenciesRepository: CurrenciesRepository, private val newsRepository: NewsRepository) : ViewModel() {
    private val _currencies:MutableLiveData<State> = MutableLiveData(State.LoadingState())
    val currencies: LiveData<State> = _currencies
    private val _news:MutableLiveData<State> = MutableLiveData(State.LoadingState())
    val news:LiveData<State> = _news
    val state: MutableLiveData<Boolean> = MutableLiveData(false)

    private val defaultCurrenciesListSize = 10

    fun fetchCurrencies(){
        CoroutineScope(Dispatchers.IO).launch {
            val responseResult = currenciesRepository.getAllCurrencies(defaultCurrenciesListSize)
            when(responseResult){
                is Result.Success<List<CryptoCurrency>> -> {
                    if(responseResult.data.isEmpty()){
                        _currencies.postValue(State.NoItemsState())
                    }else{
                        _currencies.postValue(State.SuccessfullyDownloaded<CryptoCurrency>(responseResult.data))
                        if (news.value is State.SuccessfullyDownloaded<*>){
                            state.postValue(true)
                        }
                    }
                }
                is Result.Error -> {
                    _currencies.postValue(State.ErrorState(responseResult.exception.message!!))
                }
            }
        }

    }

    fun fetchNews(){
        CoroutineScope(Dispatchers.IO).launch {
            val data = newsRepository.getAllNews()
            when (data){
                is Result.Success<List<FeedNews>> -> {
                    if(data.data.isEmpty()){
                        _news.postValue(State.NoItemsState())
                    }
                    else{
                        _news.postValue(State.SuccessfullyDownloaded<FeedNews>(data.data as List<FeedNews>))
                        if(currencies.value is State.SuccessfullyDownloaded<*>){
                            state.postValue(true)
                        }
                    }
                }
                is Result.Error -> {
                    _news.postValue(State.ErrorState(data.exception.toString()))
                }
            }
        }
    }
}