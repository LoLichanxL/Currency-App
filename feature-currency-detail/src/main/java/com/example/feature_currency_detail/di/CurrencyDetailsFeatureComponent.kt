package com.example.feature_currency_detail.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.example.data.repositories.currencies.CurrenciesRepositoryImpl
import com.example.data.repositories.news.NewsRepositoryImpl
import com.example.feature_currency_detail.databinding.FragmentCurrencyDetailsBinding
import com.example.feature_currency_detail.view.CurrencyDetailsFragment
import com.example.feature_currency_detail.view.CurrencyDetailsViewModelFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlin.properties.Delegates

@Component(dependencies = [DetailsFeatureDeps::class], modules = [DetailsModule::class])
internal interface CurrencyDetailsFeatureComponent {
    fun inject(fragment: CurrencyDetailsFragment)
    @Component.Builder
    interface Builder{
        fun build():CurrencyDetailsFeatureComponent
        fun deps(deps:DetailsFeatureDeps):Builder
    }
}
interface DetailsFeatureDeps{
    val currenciesRepositoryImpl: CurrenciesRepositoryImpl
    val newsRepositoryImpl:NewsRepositoryImpl
}

interface DetailsFeatureProvider{
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: DetailsFeatureDeps

    companion object : DetailsFeatureProvider by DetailsFeatureDepsStore
}

object DetailsFeatureDepsStore : DetailsFeatureProvider {
    override var deps: DetailsFeatureDeps by Delegates.notNull()
}
internal class DetailFeatureComponentViewModel:ViewModel(){
    val detailsFeatureComponent = DaggerCurrencyDetailsFeatureComponent.builder().deps(DetailsFeatureProvider.deps).build()
}
@Module
class DetailsModule{
    @Provides
    fun provideDetailsViewModelFactory(currenciesRepositoryImpl: CurrenciesRepositoryImpl, newsRepositoryImpl: NewsRepositoryImpl):CurrencyDetailsViewModelFactory = CurrencyDetailsViewModelFactory(currenciesRepositoryImpl, newsRepositoryImpl)
}