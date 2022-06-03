package com.example.utils

sealed class State{
    class LoadingState():State()
    class SuccessfullyDownloaded<T>(val data:List<T>) : State()
    class NoItemsState : State()
    class ErrorState(val message:String):State()
}
