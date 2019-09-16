package com.markkhakimulin.revolut.presentator.base

import com.markkhakimulin.revolut.BuildConfig
import com.markkhakimulin.revolut.presentator.di.components.DaggerApplicationComponent
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

/**
 * Created by Mark Khakimulin on 04.09.2019.
 * Email : mark.khakimulin@gmail.com
 */
class AndroidApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
        component.inject(this)
        return component
    }

    override fun onCreate() {
        super.onCreate()
        initializeTimber()
        initializeLeakDetection()
    }
    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
