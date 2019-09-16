package com.markkhakimulin.revolut.domain.response

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.markkhakimulin.revolut.data.extension.empty
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by Mark Khakimulin on 04.09.2019.
 * Email : mark.khakimulin@gmail.com
 */
class CurrencyResponse {

    @SerializedName(value = "base")
    @Expose
    var base: String = String.empty()

    @SerializedName(value = "date")
    @Expose
    var date: String = String.empty()

    @SerializedName(value = "rates")
    @Expose
    var rates: HashMap<String,Double> = HashMap()



}