package com.markkhakimulin.revolut.domain

import com.markkhakimulin.revolut.domain.response.CurrencyResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Mark Khakimulin on 04.09.2019.
 * Email : mark.khakimulin@gmail.com
 */
interface ApiService {

    companion object {
        const val LATEST : String = "latest"
    }

    @GET(LATEST)
    fun getLatest(
        @Query("base") currency: String
    ): Deferred<Response<CurrencyResponse>>

}