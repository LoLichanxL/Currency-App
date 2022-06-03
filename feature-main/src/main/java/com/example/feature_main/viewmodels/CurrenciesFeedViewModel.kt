package com.example.feature_main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.common.Result
import com.example.domain.entities.rv.CryptoCurrency
import com.example.domain.repositories.CurrenciesRepository
import com.example.utils.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrenciesFeedViewModel(private val currenciesRepository: CurrenciesRepository) : ViewModel() {
    private val _currenciesState:MutableLiveData<State> = MutableLiveData(State.LoadingState())
    val currenciesState:LiveData<State> = _currenciesState
    fun fetchAllCurrencies(){
        CoroutineScope(Dispatchers.IO).launch {
            val result = currenciesRepository.getAllCurrencies(100)
            when(result){
                is Result.Success -> {
                    if(result.data.isNotEmpty()){
                        _currenciesState.postValue(State.SuccessfullyDownloaded<CryptoCurrency>(result.data))
                    }
                }
                is Result.Error -> {

                }
            }
        }
    }
}