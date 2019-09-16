package com.markkhakimulin.revolut.domain

import android.content.Context
import com.markkhakimulin.revolut.R
import com.markkhakimulin.revolut.domain.response.CurrencyResponse
import com.markkhakimulin.revolut.data.response.NetResponce
import com.markkhakimulin.revolut.data.util.safeApiCall
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Mark Khakimulin on 04.09.2019.
 * Email : mark.khakimulin@gmail.com
 */

class CurrencyRepository
@Inject
constructor(private val apiService: ApiService,val context: Context) {

    suspend fun getLatestCurrency(base: String) = safeApiCall(
        call = { latest(base) },
        errorMessage = context.getString(R.string.network_responce_error)
    )

    //async function
    private suspend fun latest(base: String): NetResponce<CurrencyResponse> {
        val response = apiService.getLatest(base).await()
        if (response.isSuccessful)
            return NetResponce.Success(response.body()!!)
        return NetResponce.Error(IOException("Error occurred during loading currencies : ${response.errorBody()}"))
    }
}