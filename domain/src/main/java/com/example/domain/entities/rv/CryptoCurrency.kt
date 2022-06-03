package com.example.domain.entities.rv

data class CryptoCurrency(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: Double,
    val priceChange24h:Double,
    val percentChange_24h: Double,
    val logoUrl: String
) : Item()