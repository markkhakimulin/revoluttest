package com.markkhakimulin.revolut.presentator.databinding

import androidx.databinding.InverseMethod

/**
 * Created by Mark Khakimulin on 13.09.2019.
 * Email : mark.khakimulin@gmail.com
 */
object Converter {

    @JvmStatic
    @InverseMethod("doubleToString")
    fun stringToDouble(value: String): Double {
        return value.toDouble()
    }

    @JvmStatic
    fun doubleToString(value: Double): String {
        return value.toString()
    }
}
