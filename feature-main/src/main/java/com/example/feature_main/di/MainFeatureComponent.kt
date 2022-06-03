package com.example.feature_main.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.example.data.repositories.currencies.CurrenciesRepositoryImpl
import com.example.data.repositories.news.NewsRepositoryImpl
import com.example.domain.repositories.CurrenciesRepository
import com.example.domain.repositories.NewsRepository
import com.example.feature_main.view.CurrenciesFeedFragment
import com.example.feature_main.view.FeedFragment
import com.example.feature_main.view.NewsFeedFragment
import com.example.feature_main.viewmodels.CurrenciesFeedViewModel
import com.example.feature_main.viewmodels.CurrenciesFeedViewModelFactory
import com.example.feature_main.viewmodels.FeedViewModelFactory
import com.example.feature_main.viewmodels.NewsViewModelFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlin.properties.Delegates.notNull

@Component(dependencies = [MainFeatureDeps::class], modules = [MainFeatureModule::class])
internal interface MainFeatureComponent {
    fun inject(fragment:FeedFragment)
    fun inject(fragment: NewsFeedFragment)
    fun inject(fragment: CurrenciesFeedFragment)

    @Component.Builder
    interface Builder{
        fun build ():MainFeatureComponent
        fun deps(deps: MainFeatureDeps):Builder
    }
}
@Module
class MainFeatureModule{
    @Provides
    fun provideFeedViewModuleFactory(currenciesRepositoryImpl: CurrenciesRepositoryImpl, newsRepositoryImpl: NewsRepositoryImpl):FeedViewModelFactory = FeedViewModelFactory(repository = currenciesRepositoryImpl, newsRepository = newsRepositoryImpl)
    @Provides
    fun provideNewsViewModelFactory(newsRepositoryImpl: NewsRepositoryImpl):NewsViewModelFactory = NewsViewModelFactory(repositoryImpl = newsRepositoryImpl)

    @Provides
    fun provideCurrenciesViewModelFactory(currenciesRepositoryImpl: CurrenciesRepositoryImpl) = CurrenciesFeedViewModelFactory(currenciesRepositoryImpl)
}

interface MainFeatureDeps{
    val currenciesRepository: CurrenciesRepositoryImpl
    val newsRepository: NewsRepositoryImpl
}
interface MainFeatureDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: MainFeatureDeps

    companion object : MainFeatureDepsProvider by MainFeatureDepsStore
}

object MainFeatureDepsStore : MainFeatureDepsProvider {
    override var deps: MainFeatureDeps by notNull()
}

internal class MainFeatureComponentViewModel:ViewModel(){
    val mainFeatureComponent =
        DaggerMainFeatureComponent.builder().deps(MainFeatureDepsProvider.deps).build()
}
