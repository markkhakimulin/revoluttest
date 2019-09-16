package com.markkhakimulin.revolut.data.util

import com.markkhakimulin.revolut.data.response.NetResponce
import java.io.IOException
import java.lang.Exception

/**
 * Created by Mark Khakimulin on 04.09.2019.
 * Email : mark.khakimulin@gmail.com
 */

suspend fun <T : Any> safeApiCall(call: suspend () -> NetResponce<T>, errorMessage: String): NetResponce<T> = try {
    call.invoke()
} catch (e: Exception) {
    NetResponce.Error(IOException(errorMessage, e))
}
