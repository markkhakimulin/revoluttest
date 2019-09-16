package com.markkhakimulin.revolut.presentator.ui

import android.os.Bundle
import com.markkhakimulin.revolut.R.id
import com.markkhakimulin.revolut.R.layout
import com.markkhakimulin.revolut.presentator.base.BaseActivity
import com.markkhakimulin.revolut.presentator.ui.list.CurrencyListFragment

class MainActivity : BaseActivity() {

    override fun getLayoutRes(): Int  = layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null)
            supportFragmentManager.beginTransaction().add(id.screenContainer, CurrencyListFragment()).commit()
    }

    fun displayHomeAsUpEnabled(isEnabled: Boolean){
        supportActionBar!!.setDisplayHomeAsUpEnabled(isEnabled)
    }
}
