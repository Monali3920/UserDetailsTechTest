package com.test.assignment.utils

sealed class Resource<out R> {

    data class Success<out T>(val data: T) : Resource<T>()

    data class Error<T>(val message: String) : Resource<T>()

    object Loading : Resource<Nothing>()

}
