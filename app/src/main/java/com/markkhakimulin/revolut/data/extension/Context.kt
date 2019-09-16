package com.markkhakimulin.revolut.data.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by Mark Khakimulin on 04.09.2019.
 * Email : mark.khakimulin@gmail.com
 */

val Context.networkInfo: NetworkInfo? get() =
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo