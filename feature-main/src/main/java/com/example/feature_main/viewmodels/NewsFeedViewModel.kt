package com.example.feature_main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.repositories.news.NewsRepositoryImpl
import com.example.domain.common.Result
import com.example.domain.entities.rv.FeedNews
import com.example.utils.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsFeedViewModel(val repositoryImpl: NewsRepositoryImpl) : ViewModel(){
    private val _news:MutableLiveData<State> = MutableLiveData(State.LoadingState())
    val news:LiveData<State> = _news
    fun fetchNews(){
        CoroutineScope(Dispatchers.IO).launch {
            val data = repositoryImpl.getAllNews()
            when (data){
                is Result.Success<List<FeedNews>> -> {
                    if(data.data.isEmpty()){
                        _news.postValue(State.NoItemsState())
                    }
                    else{
                        _news.postValue(State.SuccessfullyDownloaded<FeedNews>(data.data as List<FeedNews>))
                    }
                }
                is Result.Error -> {
                    _news.postValue(State.ErrorState(data.exception.toString()))
                }
            }
        }
    }
}