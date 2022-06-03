package com.example.domain.entities.rv.currency_details

import com.example.domain.entities.rv.Item

data class CurrencyChart (
    val prices: List<List<Double>>,
    val market_caps: List<List<Double>>
    ): Item()