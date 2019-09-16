package com.markkhakimulin.revolut.presentator.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Mark Khakimulin on 06.09.2019.
 * Email : mark.khakimulin@gmail.com
 */
abstract class BaseActivity  : DaggerAppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes() : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
    }

}