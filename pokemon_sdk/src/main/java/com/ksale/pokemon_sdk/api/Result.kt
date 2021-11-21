package com.ksale.pokemon_sdk.api

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val errorCode: Int, val errorMessage: String?) : Result<Nothing>()
}
