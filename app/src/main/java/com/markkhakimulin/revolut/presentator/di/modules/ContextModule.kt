package com.markkhakimulin.revolut.presentator.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Created by Mark Khakimulin on 09.09.2019.
 * Email : mark.khakimulin@gmail.com
 */
@Module
abstract class ContextModule {

    @Binds
    abstract fun provideContext(application: Application): Context
}