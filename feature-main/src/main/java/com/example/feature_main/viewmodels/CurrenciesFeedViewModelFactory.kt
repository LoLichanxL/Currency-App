package com.example.feature_main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.repositories.CurrenciesRepository

class CurrenciesFeedViewModelFactory(private val currenciesRepository: CurrenciesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrenciesFeedViewModel(currenciesRepository) as T
    }
}