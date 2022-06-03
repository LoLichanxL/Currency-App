package com.example.currencyapp.di

import com.example.data.mappers.CurrenciesApiResponseMapper
import com.example.data.mappers.NewsMapper
import com.example.data.network.currencies.CurrenciesService
import com.example.data.network.news.NewsService
import com.example.data.repositories.currencies.CurrenciesDataSourceImpl
import com.example.data.repositories.currencies.CurrenciesRepositoryImpl
import com.example.data.repositories.news.NewsDataSourceImpl
import com.example.data.repositories.news.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {

    @Provides
    fun provideCurrenciesRepositoryImpl(currenciesDataSourceImpl: CurrenciesDataSourceImpl):
            CurrenciesRepositoryImpl = CurrenciesRepositoryImpl(currenciesDataSourceImpl)

    @Provides
    fun provideCurrenciesDataSource(
        service: CurrenciesService,
        mapper: CurrenciesApiResponseMapper
    ):
            CurrenciesDataSourceImpl = CurrenciesDataSourceImpl(service, mapper)

    @Provides
    fun provideCurrenciesResponseMapper(): CurrenciesApiResponseMapper = CurrenciesApiResponseMapper()

    @Provides
    fun provideCurrenciesService(): CurrenciesService =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.coingecko.com/api/v3/").build()
            .create(CurrenciesService::class.java)

    @Provides
    fun provideNewsService(): NewsService =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://min-api.cryptocompare.com/data/")
            .build().create(NewsService::class.java)

    @Provides
    fun provideNewsMapper():NewsMapper = NewsMapper()

    @Provides
    fun provideNewsDataSource(service: NewsService, mapper: NewsMapper): NewsDataSourceImpl = NewsDataSourceImpl(service, mapper)

    @Provides
    fun provideNewsRepositoryImpl(newsDataSource: NewsDataSourceImpl): NewsRepositoryImpl = NewsRepositoryImpl(newsDataSource)
}