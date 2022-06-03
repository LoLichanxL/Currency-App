package com.example.currencyapp

import android.app.Application
import android.content.Context
import com.example.currencyapp.di.AppComponent
import com.example.currencyapp.di.DaggerAppComponent
import com.example.feature_currency_detail.di.DetailsFeatureDeps
import com.example.feature_currency_detail.di.DetailsFeatureDepsStore
import com.example.feature_currency_detail.di.DetailsFeatureProvider
import com.example.feature_main.di.MainFeatureDepsStore

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        MainFeatureDepsStore.deps = appComponent
        DetailsFeatureDepsStore.deps = appComponent
    }
}

val appComponent: AppComponent by lazy {
    DaggerAppComponent.builder().build()
}