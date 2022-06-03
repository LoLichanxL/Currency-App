package com.example.feature_main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.repositories.CurrenciesRepository
import com.example.domain.repositories.NewsRepository

class FeedViewModelFactory(private val repository: CurrenciesRepository, private val newsRepository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeedViewModel(repository, newsRepository) as T
    }
}