package com.example.feature_main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repositories.news.NewsRepositoryImpl

class NewsViewModelFactory(private val repositoryImpl: NewsRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsFeedViewModel(repositoryImpl = repositoryImpl) as T
    }
}