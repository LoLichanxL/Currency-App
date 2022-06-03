package com.example.feature_currency_detail.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repositories.currencies.CurrenciesRepositoryImpl
import com.example.domain.repositories.NewsRepository

class CurrencyDetailsViewModelFactory(val currenciesRepositoryImpl: CurrenciesRepositoryImpl, val newsRepository: NewsRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrencyDetailsViewModel(currenciesRepositoryImpl, newsRepository) as T
    }
}