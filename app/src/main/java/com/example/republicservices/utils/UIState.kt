package com.example.republicservices.utils

sealed class UIState<out T>{
    object Loading: UIState<Nothing>()
    class Failure(val errorMessage: Exception): UIState<Nothing>()
    data class Success<T> (val response: T): UIState<T>()
    //object Empty: UIState<Nothing>()
}