package com.example.currencyapp.di

import com.example.data.repositories.currencies.CurrenciesRepositoryImpl
import com.example.data.repositories.news.NewsRepositoryImpl
import com.example.domain.repositories.CurrenciesRepository
import com.example.feature_currency_detail.di.DetailsFeatureDeps
import com.example.feature_main.di.MainFeatureDeps
import dagger.Component
import javax.inject.Scope

@[Component(modules = [AppModule::class])]
interface AppComponent : MainFeatureDeps, DetailsFeatureDeps{
    override val currenciesRepository: CurrenciesRepositoryImpl
    override val newsRepositoryImpl: NewsRepositoryImpl
    @Component.Builder
    interface Builder{
        fun build():AppComponent
    }
}
