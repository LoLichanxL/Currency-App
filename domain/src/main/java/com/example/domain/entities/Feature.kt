package com.example.domain.entities

sealed interface Feature{
    object Compare : Feature
    object Convert : Feature
    object WatchList : Feature
}