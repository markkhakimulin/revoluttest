package com.markkhakimulin.revolut.data.response

/**
 * Created by Mark Khakimulin on 04.09.2019.
 * Email : mark.khakimulin@gmail.com
 */
sealed class NetResponce<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetResponce<T>()
    data class Error(val exception: Exception) : NetResponce<Nothing>()
}